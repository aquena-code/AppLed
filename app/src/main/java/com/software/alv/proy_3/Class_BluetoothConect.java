package com.software.alv.proy_3;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;


/**
 * Created by ALVARO on 26/09/2018.
 */



public class Class_BluetoothConect extends DialogFragment {

    public interface I_ClassBluetoothConnect{
        public void bt_EndConnect(BluetoothSocket bt_socket);
    }
    private I_ClassBluetoothConnect infc_bt_click;
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothSocket btSocket=null;
    private boolean isBtConnected = false;
    private ListView ltvw_ListaBT;
    private ProgressDialog progress;
    private String address = null;
    public Context contextBT;
    //public boolean connectSuccess = true;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    public void onAttach(Activity context){

        super.onAttach(context);
        this.contextBT = context;
        infc_bt_click = (I_ClassBluetoothConnect) context;
        //Log.v("TAMAÑO>>", "ENTRO EN ONATACH");
        //Toast.makeText(getActivity().getBaseContext() ,"ADJUNTADO.",Toast.LENGTH_LONG).show();
        //Log.v("ACTIVITY MAIN", "---> ATTACH FRMT_DIALOG");
        //Log.v("ACTIVITY MAIN", "<--- ATTACH FRMT_DIALOG");
    }
    @Override   // Aqui es donde Creamos la vista Principal, y Realizamos la busqueda de dispositivos BT
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){

        View vw_dialogo = layoutInflater.inflate(R.layout.layt_listbluetooth,viewGroup,false);  //infla el Layout de Principal(DialogFragment)

        ltvw_ListaBT = (ListView) vw_dialogo.findViewById(R.id.ListView_ListadoBTH); // Castea el ListView del Layout Principal

        myBluetooth = BluetoothAdapter.getDefaultAdapter(); // obtiene el Adaptador Bluetooth del dispositivo movil

        if(myBluetooth == null){
            Toast.makeText(contextBT,"Dispositivo Bluetooth no Disponible",Toast.LENGTH_LONG).show();
            return vw_dialogo;
        }
        else{
            if(myBluetooth.isEnabled()){
                //Toast.makeText(getApplicationContext(),"BLUETOOTH ESTA ACTIVADO",Toast.LENGTH_LONG).show();
                buscarDevicesListBT();
            }
            else{
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }


        ltvw_ListaBT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info;

                info = ((TextView) view.findViewById(R.id.txv_entrada)).getText().toString();
                //info = parent.
                address = info.substring(info.length() - 17);
                //address="20:16:04:19:21:54";

                new Class_AsTsConnectBT().execute();


            }
        });

        //Log.v("TAMAÑO>>", "SALE DE ONCREATEVIEW");
        return vw_dialogo;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("ACTIVITY MAIN", "---> ONPAUSE FRMT_DIALOG");
        Log.v("ACTIVITY MAIN", "<--- ONPAUSE FRMT_DIALOG");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("ACTIVITY MAIN", "---> ONRESUME FRMT_DIALOG");
        Log.v("ACTIVITY MAIN", "<--- ONRESUME FRMT_DIALOG");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("ACTIVITY MAIN", "---> ONSTOP FRMT_DIALOG");
        Log.v("ACTIVITY MAIN", "<--- ONSTOP FRMT_DIALOG");
    }



    @Override
    public void onDetach(){
        super.onDetach();
        if (isBtConnected)    infc_bt_click.bt_EndConnect(btSocket);
        Toast.makeText(contextBT,"Destruido",Toast.LENGTH_SHORT).show();
    }

    private void buscarDevicesListBT(){

        pairedDevices = myBluetooth.getBondedDevices();

        ArrayList lista = new ArrayList();

        Bsad_Adaptador bsad_adaptador1 = new Bsad_Adaptador(getActivity().getBaseContext(),R.layout.layt_bluetooth_entrada,lista) {

            @Override
            public void onEntrada(Object item, View view, int posicion) {   //Elemento de un Item de la lista antes de Ingresarla.
                ((TextView) view.findViewById(R.id.txv_entrada)).setText((String) item);
                //Log.v("TAMAÑO>>", "INGRESA DE ONCREATEVIEW");
            }
        };

        if (pairedDevices.size()>0){    //Si ha logrado encontrar más de un dispositivo Bluetooth procede ha añadirlo a la lista.

            for(BluetoothDevice bt: pairedDevices){

                lista.add(bt.getName() + "\n" + bt.getAddress());
                //Toast.makeText(this,"::"+bt.getName()+"\n"+bt.getAddress(),Toast.LENGTH_LONG).show();

            }

            ltvw_ListaBT.setAdapter(bsad_adaptador1);
        }
        else {
           // Log.v("TAMAÑO>>", "INGRESA DE ONCREATEVIEW");
            Toast.makeText(getActivity().getBaseContext() ,"Ningún Dispositivo Bluetooth encontrado.",Toast.LENGTH_LONG).show();

        }

    }

    private class Class_AsTsConnectBT extends AsyncTask<Void,Void,Void>{
        private boolean connectSuccess = true;


        @Override
        protected void onPreExecute(){
            Log.v("TAMAÑO>>", "INGRESA DE ONCLICKKKKKKK");
            progress = ProgressDialog.show(contextBT, "Conectando....", "Por favor espere");

            Log.v("TAMAÑO>>", "INGRESA DE ONCLIK");
        }

        @Override
        protected Void doInBackground(Void... devices){
            try{
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();

                    //infc_bt_click.bt_EndConnect(btSocket);
                }
            }catch (IOException e){
                connectSuccess=false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            if(!connectSuccess){
                Toast.makeText(getActivity(),"Conexión Fallida",Toast.LENGTH_LONG).show();
                //Log.v("BLUETOOTH::","Conexion Fallida......");
                isBtConnected = false;

            }else{
                Toast.makeText(getActivity(), "Conectado...",Toast.LENGTH_SHORT).show();
                //Log.v("BLUETOOTH::","Conectado....OK");

                isBtConnected = true;

            }
            progress.dismiss();//cerrar el ProgressDialog
            dismiss(); //cerrar el DialogFragment
        }

    }


}
