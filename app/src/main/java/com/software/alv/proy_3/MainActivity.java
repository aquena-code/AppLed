package com.software.alv.proy_3;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ALVARO on 16/01/2019.
 */

public class MainActivity extends Activity implements Class_frmtMenuA.I_frmtMenuAListener, Class_frmtMenuB.I_frmtMenuBListener, Class_FrmtPanel.I_FrmtPanelListener , Class_BluetoothConect.I_ClassBluetoothConnect, Class_frmtTextoEntrada.I_Class_frmtTextoEntrada {
    private String idMenuA = "0";
    private String idMenuB = "0";
    public  int swPlay = 0, swAnim = 0, swVel = 0;
    private int idSrfvMatrix = -1;
    private String menu_sw0 = "-1", menu_sw4 = "-1";
    private int swSrfvPanel=0,swSrfvTiempos=0;
    public int swConnectBT = 0;
    private BluetoothSocket bt_socketMain= null;
    private String mensajeBT= "";
    private int tipoPantalla = 0;
    private int idAux = 0;
    private int pos_idResc=0;

    private int id_Resc_Pant = R.mipmap.pantalla1_1;
    private int pos_idResc_Pant = 0;

    private Class_SrfvMatrix srfv_matrix = null;
    private Class_FrmtPanel frmt_panel1_aux =null;
    private Class_frmtMenuA frmt_menuA;
    private Class_frmtMenuB frmt_menuB0 ;

    private BroadcastReceiver mReceiver;

    public int getId_Resc_Pant(){
        return this.id_Resc_Pant;
    }

    public int getPos_idResc_Pant(){
        return this.pos_idResc_Pant;
    }


    @Override
    protected void onCreate(Bundle savedInstancedState){

        super.onCreate(savedInstancedState);
        Log.v("ACTIVITY MAIN", "---> ONCREATE");

        setContentView(R.layout.layt_activitymain);

        frmt_menuA = new Class_frmtMenuA();
        getFragmentManager().beginTransaction().replace(R.id.frly_MenuA, frmt_menuA).commit();

        //frmt_menuB0.setArguments(bundle);
        frmt_menuB0 = new Class_frmtMenuB();
        getFragmentManager().beginTransaction().replace(R.id.frly_MenuB, frmt_menuB0).commit();

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    Log.v("ACTIVITY=","---ENCONTRADO---");//Dispositivo Encontrado
                }
                else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                    Log.v("ACTIVITY=","---CONECTADO---");//Dispositivo esta ahora conectado
                }
                else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    Log.v("ACTIVITY=","---DESCUBIERTO FINAL---"); //Busqueda realizada
                }
                else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
                    Log.v("ACTIVITY=","---DESCONECTADO PETICION---"); //Requerimiento de Desconexion
                }
                else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    Log.v("ACTIVITY=", "---DESCONECTADO---");

                    btDesconectado();//Toast.makeText(,"DESCONECTADO",Toast.LENGTH_SHORT).show(); //Dispositivo Desconcentado
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);
        Log.v("ACTIVITY MAIN", "---> ONCREATE");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("ACTIVITY MAIN", "---> ONSTART");
        if (idMenuA == "0" & idMenuB == "0") {
            onClickMenuA(idMenuA);
            onClickFrmtMenuB(idMenuB);
            //Log.v("ACTIVITY MAIN", "---> ONSTART 1");
        }

        Log.v("ACTIVITY MAIN", "<--- ONSTART");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("ACTIVITY MAIN", "---> ONRESUME");
        Log.v("ACTIVITY MAIN", "<--- ONRESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("ACTIVITY MAIN", "---> ONPAUSE");
        Log.v("ACTIVITY MAIN", "<--- ONPAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("ACTIVITY MAIN", "---> ONSTOP");
        Log.v("ACTIVITY MAIN", "<--- ONSTOP");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("ACTIVITY MAIN", "---> ONRESTART");
        Log.v("ACTIVITY MAIN", "<--- ONRESTART");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClickMenuA(String id){
        idMenuA = id;

        //Log.v("Variable swplay>",":::"+swPlay);

        if (swPlay == 1){
            idMenuA = "4";
        }
        if (swAnim == 1) {
            //idMenuA = "2";
            this.srfv_matrix.setSrfv_evento(srfv_matrix.getEstado());
            swAnim = 0;
        }
        if (swVel == 1){
            this.srfv_matrix.setSrfv_evento("31");
            swVel = 0;
        }

        /*Bundle bundle = new Bundle();
        bundle.putString("ID", idMenuA);*/

        switch (idMenuA) {
            case "0":
                //bundle.putInt("ID_Resc", srfv_matrix.getId_Resc_numSizeText());
                selectImgPantalla(tipoPantalla);
                //getFragmentManager().beginTransaction().remove(frmt_texto).commit();
                break;
            case "1":
                //bundle.putInt("ID_Resc", srfv_matrix.getId_Resc_numSizeText());
                idAux = srfv_matrix.getId_Resc_numSizeText();
                pos_idResc = srfv_matrix.getPos_idResc_numSizeText();
                //getFragmentManager().beginTransaction().replace(R.id.lnyt_MenuC, frmt_texto).commit();//Muestra el fragment en el MenuC para ingresar texto

                break;
            case "3":
                //bundle.putInt("ID_Resc", srfv_matrix.getId_Resc_aceleracion());
                idAux = srfv_matrix.getId_Resc_aceleracion();
                pos_idResc=srfv_matrix.getPos_idResc_aceleracion();
                //getFragmentManager().beginTransaction().remove(frmt_texto).commit();
                break;
            case "2":
                idAux = srfv_matrix.getId_Resc_animacion();
                pos_idResc = srfv_matrix.getPos_idResc_anim();
                //getFragmentManager().beginTransaction().remove(frmt_texto).commit();
                break;
            case "4":
                idAux = srfv_matrix.getId_Resc_sim();
                pos_idResc = srfv_matrix.getPos_idResc_sim();
                //getFragmentManager().beginTransaction().remove(frmt_texto).commit();
                break;
            case "5":
                selectImgBluetooh(swConnectBT);
                //getFragmentManager().beginTransaction().remove(frmt_texto).commit();
                break;
            default:
                idAux = 0;
                pos_idResc = -1;
                //getFragmentManager().beginTransaction().remove(frmt_texto).commit();
        }

        //Log.v("ON RESUME", "---> Menu secundario A");
        if (swPlay != 1 ) {
            frmt_menuA.actImgMenuMain(Integer.parseInt(idMenuA));
            frmt_menuB0.actImgMenuSec(idMenuA, pos_idResc, idAux);
        }
        //Log.v("ON RESUME", "<--- Menu secundario A");

    }

    @Override
    public void onClickFrmtMenuB(String idMenuB){
        this.idMenuB = idMenuB;
        Bundle bundle = new Bundle();

        //Log.v("MENUS>>>", ":::" + idMenuA + ":" + idMenuB);

        if (!menu_sw0.equals(idMenuA + idMenuB) & idMenuA == "0" ) {

            menu_sw0 = idMenuA + idMenuB;
            tipoPantalla = Integer.parseInt(idMenuB)+ 1;
            bundle.putString("ID_MENUB", idMenuB);
            bundle.putParcelable("PROP_MENUB", Class_Datos.arls_datosPantalla.get(Integer.parseInt(idMenuB)));

            Class_FrmtPanel frmt_panel1 = new Class_FrmtPanel();
            frmt_panel1.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frly_Panel, frmt_panel1).commit();
            frmt_panel1_aux = frmt_panel1;
            selectImgPantalla(tipoPantalla);
            frmt_menuB0.actImgMenuSec(idMenuA, pos_idResc, idAux);
            idSrfvMatrix = -1;

            //Log.v("ON RESUME", "Ha ingresado a .......onClickMenuB");
        }

        if ( (idMenuA == "1") ){//& (idMenuB == "0" || idMenuB == "2")){   //La opcion de Incr de tamaño de texto fue pulsado
            if (idMenuB == "2") srfv_matrix.setSrfv_evento("12");
            if (idMenuB == "0") srfv_matrix.setSrfv_evento("10");
            if (idMenuB == "3"){
                Class_frmtTextoEntrada frmt_Entrada = new Class_frmtTextoEntrada();
                bundle.putString("Mensaje",srfv_matrix.getMensaje());
                frmt_Entrada.setArguments(bundle);
                frmt_Entrada.show(getFragmentManager(),"frmt_Entrada");
            }


        }
        if (idMenuA == "2" & idMenuB == "0"){   //la opcion estatico fue pulsado
            srfv_matrix.setSrfv_evento("20");
            swAnim = 1;
        }
        if (idMenuA == "2" & idMenuB == "1"){   //la opcion de a Izq a Der fue pulsado
            srfv_matrix.setSrfv_evento("21");
            swAnim = 1;
        }
        if (idMenuA == "2" & idMenuB == "2"){
            srfv_matrix.setSrfv_evento("22");
            swAnim = 1;
        }
        if (idMenuA == "2" & idMenuB == "3"){   //opcion lado a lado
            srfv_matrix.setSrfv_evento("23");
            swAnim = 1;
        }
        if (idMenuA == "2" & idMenuB == "4"){   //izq a der, letra por letra
            srfv_matrix.setSrfv_evento("24");
            swAnim = 1;
        }

        if (idMenuA == "3" &(idMenuB == "0" || idMenuB == "2")){    //La opcion para incr o decre la velocidad fue pulsado
            if (idMenuB == "0") srfv_matrix.setSrfv_evento("30");
            if (idMenuB == "2")  srfv_matrix.setSrfv_evento("32");
            swVel = 1;
        }

        if ( !(menu_sw4.equals(idMenuA+idMenuB))&(idMenuA == "4" & idMenuB == "0")){    //Animación Total
            menu_sw4 = idMenuA + idMenuB;
            int aux = 0;
            int i_max =0;
            for (int i = 0; i<frmt_panel1_aux.arls_srfv_Matrix.size();i++ ){
                //arry_tiempos[i][0] = frmt_panel1_aux.arls_srfv_Matrix.get(i).getT_espera();
                //arry_tiempos[i][1] = frmt_panel1_aux.arls_srfv_Matrix.get(i).getT_duracion();
                if ((frmt_panel1_aux.arls_srfv_Matrix.get(i).getT_espera()+frmt_panel1_aux.arls_srfv_Matrix.get(i).getT_duracion()) > aux) {//aqui averiguamos el srfv con mas duración
                    aux = frmt_panel1_aux.arls_srfv_Matrix.get(i).getT_espera() + frmt_panel1_aux.arls_srfv_Matrix.get(i).getT_duracion();
                    i_max = i;
                }
            }
//            Class_ListaMenus.t_final_max = aux;
//            toast = Toast.makeText(this, "::"+menu_sw4,Toast.LENGTH_SHORT);
//            toast.show();

            for (int i = 0;i < frmt_panel1_aux.arls_srfv_Matrix.size(); i++){
                if (i==i_max)
                    frmt_panel1_aux.arls_srfv_Matrix.get(i).setT_final(aux);//actualizamos el tiempo final a 0 porque es el que tiene el maximo tiempo de duracion
                else
                    frmt_panel1_aux.arls_srfv_Matrix.get(i).setT_final(aux);//actualizamos el tiempo final restante par que espere a que al ultimo srfv_matrix culmine

                frmt_panel1_aux.arls_srfv_Matrix.get(i).setSrfv_evento("40");//ponemos en estado de animacion total a todos los srfv_Matrix
            }

            //frmt_tiempos_aux.getSrfv_Tiempos().iniPlayAllTiempos(arry_tiempos, aux, frmt_panel1_aux.arls_srfv_Matrix.size());
            swPlay = 1;
        }

        if ( !(menu_sw4.equals(idMenuA + idMenuB)) & (idMenuA == "4" & idMenuB == "1")){
            menu_sw4 = idMenuA + idMenuB;
            //frmt_tiempos_aux.getSrfv_Tiempos().setContMaxTiempo(Class_ListaMenus.t_final_max+1);//finaliza a Srfv_Tiempos
            for (int i = 0;i<frmt_panel1_aux.arls_srfv_Matrix.size();i++) {
                frmt_panel1_aux.getArls_srfv_Matrix().get(i).setSrfv_evento("41");//finaliza todos los srfv_Matrix
            }
            swPlay = 0;
        }

        if(idMenuA == "5" & idMenuB == "0"){// Conexion con el BT de la Pantalla
            //Toast.makeText(this,"TODO OK", Toast.LENGTH_SHORT).show();

            if(swConnectBT == 0) {
                Class_BluetoothConect frmt_BT = new Class_BluetoothConect();
                frmt_BT.show(getFragmentManager(),"frmt_BT");
                //swConnectBT = 1;

            }
            else{
                try{
                    bt_socketMain.close();
                    swConnectBT = 0;
                    selectImgBluetooh(swConnectBT);
                    frmt_menuB0.actImgMenuSec(idMenuA, pos_idResc, idAux);
                    Toast.makeText(this,"Se ha Desconectado de la Pantalla Led", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(this,"Ocurrió un error al Desconectarse....", Toast.LENGTH_SHORT).show();
                    //Log.v("ERROR", e.toString());
                    //Log.v("swCONECTADO",Integer.toString(swConnectBT));
                }
            }
        }

        if(idMenuA == "5" & idMenuB == "1"){

            if(swConnectBT == 1){
                try {
                    //bt_socketMain.getOutputStream().write("11121015123456789ABCDEF".toString().getBytes());
                    //bt_socketMain.getOutputStream().write("1221200530000000006301512345".toString().getBytes());
                    //bt_socketMain.getOutputStream().write("122120052000000000630151234522011050000016063031BA987654321".toString().getBytes());

                    //bt_socketMain.getOutputStream().write("11214005150000000031031VENTA24011050032000063031BA987654321".toString().getBytes());

                    //bt_socketMain.getOutputStream().write("11214004150000000031031LL4524004500032000063031LL45".toString().getBytes());
                    //bt_socketMain.getOutputStream().write("11111004050000008063023LL45".toString().getBytes());
                    //bt_socketMain.getOutputStream().write("11114004500000000063031LL45".toString().getBytes());
                    //bt_socketMain.getOutputStream().write("11122005050008008055023VENTA".toString().getBytes());
                    //

                    mensajeBT = formarMSG();
                    //bt_socketMain.getOutputStream().write(mensajeBT.getBytes());
                    Log.v("MENSAJEBT",mensajeBT);
                    while (mensajeBT.length()>=60){
                        bt_socketMain.getOutputStream().write((mensajeBT.substring(0,60)).getBytes());
                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mensajeBT = mensajeBT.substring(60);
                        Log.v("MENSAJEBT",mensajeBT);
                    }
                    if(mensajeBT.length()>0){
                        bt_socketMain.getOutputStream().write(mensajeBT.getBytes());
                        Log.v("MENSAJEBT",mensajeBT);
                    }
                    //Log.v("MENSAJEBT",mensajeBT);


                    //bt_socketMain.getOutputStream().write("as12".toString().getBytes());
                    Toast.makeText(this, "El Mensaje ha sido enviado.", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(this, "Mesaje no enviado..",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Dispositivo BlueTooth no conectado..", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onClickPanels_srfv(int idsrfv){
        int idAux = 0;
        int pos_idResc = -1;

        Log.v("MAIN ACTIVITY", "---> CALLBACK CLICK SRFV");
        switch (idMenuA) {
            case "0":
                if (idSrfvMatrix != idsrfv) {
                    if (idSrfvMatrix == -1) {
                        this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                        this.idSrfvMatrix = idsrfv;
                        this.srfv_matrix.setToken(1);
                        //Log.v("MAIN ACTIVITY", "---> IDMENU ::" + idSrfvMatrix + " - " + idsrfv);
                        //Log.v("MAIN ACTIVITY", "CASE  -----<>-----");
                    }
                    else{
                        this.srfv_matrix.setToken(0);

                        this.srfv_matrix.actualizar_Srfv();
                        this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                        this.idSrfvMatrix = idsrfv;
                        this.srfv_matrix.setToken(1);
                        this.srfv_matrix.actualizar_Srfv();
                    }
                }
                break;
            case "1":
                if (idSrfvMatrix != idsrfv){
                    this.srfv_matrix.setToken(0);
                    //Log.v("MAIN ACTIVITY", "---> IDMENU 111111111::" + idSrfvMatrix + " - " + idsrfv);
                    this.srfv_matrix.actualizar_Srfv();
                    this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                    this.idSrfvMatrix = idsrfv;
                    this.srfv_matrix.setToken(1);
                    this.srfv_matrix.actualizar_Srfv();


                    idAux = srfv_matrix.getId_Resc_numSizeText();
                    pos_idResc = srfv_matrix.getPos_idResc_numSizeText();
                    frmt_menuB0.actImgMenuSec(idMenuA,pos_idResc, idAux);
                }
                break;
            case "3":
                if (idSrfvMatrix != idsrfv) this.srfv_matrix.setToken(0);

                if (swVel == 1){
                    this.srfv_matrix.setSrfv_evento("31");//detenemos el hilo de animación
                    swVel = 0;
                }
                else{
                    this.srfv_matrix.actualizar_Srfv();
                }

                if (idSrfvMatrix != idsrfv) {
                    this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                    this.idSrfvMatrix = idsrfv;
                    this.srfv_matrix.setToken(1);
                    this.srfv_matrix.actualizar_Srfv();

                    idAux = srfv_matrix.getId_Resc_aceleracion();
                    pos_idResc=srfv_matrix.getPos_idResc_aceleracion();
                    frmt_menuB0.actImgMenuSec(idMenuA,pos_idResc, idAux);
                }
                break;
            case "2":
                if (idSrfvMatrix != idsrfv)
                    this.srfv_matrix.setToken(0);

                if (swAnim == 1){
                    this.srfv_matrix.setSrfv_evento(srfv_matrix.getEstado());//detenemos el hilo de animación
                    swAnim = 0;
                }
                else{
                    this.srfv_matrix.actualizar_Srfv();
                }
                if (idSrfvMatrix != idsrfv) {
                    this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                    this.idSrfvMatrix = idsrfv;
                    this.srfv_matrix.setToken(1);
                    this.srfv_matrix.actualizar_Srfv();

                    idAux = srfv_matrix.getId_Resc_animacion();
                    pos_idResc = srfv_matrix.getPos_idResc_anim();
                    frmt_menuB0.actImgMenuSec(idMenuA,pos_idResc, idAux);
                }
                break;
            case "4":
                if (swPlay != 1){
                    if (idSrfvMatrix != idsrfv){
                        this.srfv_matrix.setToken(0);
                        //Log.v("MAIN ACTIVITY", "---> IDMENU 111111111::" + idSrfvMatrix + " - " + idsrfv);
                        this.srfv_matrix.actualizar_Srfv();
                        this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                        this.idSrfvMatrix = idsrfv;
                        this.srfv_matrix.setToken(1);
                        this.srfv_matrix.actualizar_Srfv();


                        //idAux = srfv_matrix.getId_Resc_numSizeText();
                        //pos_idResc = srfv_matrix.getPos_idResc_numSizeText();
                        //frmt_menuB0.actImgMenuSec(idMenuA,pos_idResc, idAux);
                    }
                }
                break;
            case "5":
                if (idSrfvMatrix != idsrfv){
                    this.srfv_matrix.setToken(0);
                    //Log.v("MAIN ACTIVITY", "---> IDMENU 111111111::" + idSrfvMatrix + " - " + idsrfv);
                    this.srfv_matrix.actualizar_Srfv();
                    this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                    this.idSrfvMatrix = idsrfv;
                    this.srfv_matrix.setToken(1);
                    this.srfv_matrix.actualizar_Srfv();

                    //frmt_menuB0.actImgMenuSec(idMenuA,pos_idResc, idAux);
                }
                break;
            default:
                this.srfv_matrix = frmt_panel1_aux.arls_srfv_Matrix.get(idsrfv);
                this.idSrfvMatrix = idsrfv;
                this.srfv_matrix.setToken(1);
                //Log.v("MAIN ACTIVITY", "DEAFAULT -----<>");
                break;
                /*idAux = 0;
                pos_idResc = -1;
                frmt_menuB0.actImgMenuSec(idMenuA,pos_idResc,idAux);*/
        }
        Log.v("MAIN ACTIVITY","<--- CALLBACK CLICK SRFV");
    }

    @Override
    public void onUpDowm_ActMenuB0(){
        int idAux = 0;
        int pos_idResc = -1;
        //onClickMenuA(idMenuA);
        //if (idMenuA == "1")     idAux = srfv_matrix.getId_Resc_numSizeText();
        //if (idMenuA == "3")     idAux = srfv_matrix.getId_Resc_aceleracion();
        //if (idMenuA == "2")
        Log.v("MAIN ACTIVITY", "---> CALLBACK CLICK ACT MENU B");

        switch (idMenuA) {
            case "1":
                idAux = srfv_matrix.getId_Resc_numSizeText();
                pos_idResc = srfv_matrix.getPos_idResc_numSizeText();
                break;
            case "3":
                idAux = srfv_matrix.getId_Resc_aceleracion();
                pos_idResc=srfv_matrix.getPos_idResc_aceleracion();
                break;
            case "2":
                idAux = srfv_matrix.getId_Resc_animacion();
                pos_idResc = srfv_matrix.getPos_idResc_anim();
                break;
            case "4":
                idAux = srfv_matrix.getId_Resc_sim();
                pos_idResc = srfv_matrix.getPos_idResc_sim();
                break;
            default:
                //idAux = 0;
                //pos_idResc = -1;
        }
        frmt_menuB0.actImgMenuSec(idMenuA, pos_idResc, idAux);
        Log.v("MAIN ACTIVITY", "<--- CALLBACK CLICK ACT MENU B");
}

    @Override
    public void infcCallBacksSrfvMatrix_IniciarSrfvs(String srfv){ //verifica que se hayan creado los surfaviews para actualizar los Tiempos
        if (srfv == "Panel")    swSrfvPanel = 1;
        if (srfv == "Tiempos")  swSrfvTiempos = 1;
        /*if (swSrfvPanel == 1 & swSrfvTiempos == 1){
            infc_CallBacksSrfvTiempos_Actualizar(idSrfvMatrix);
        }*/
        //Log.v("INICIO", "---> CALLBACK  ");
    }

    @Override
    public void infcCallBacksMenu4_Reset() {
        menu_sw4 = "-1";
        this.swPlay = 0;
        this.swAnim = 0;
        this.swVel = 0;
        //infc_CallBacksSrfvTiempos_Actualizar(idSrfvMatrix);
        //Log.v("Variable swPlay0000000", ":::" + swPlay + ":" + idMenuA);
        //onClickMenuA("0");

    }

    @Override
    public void infcCallBacksMenu2_Reset(){
        this.swAnim = 0;
    }

    @Override
    public void bt_EndConnect(BluetoothSocket bt_socket){
        bt_socketMain = bt_socket;
        swConnectBT = 1 ; //Conexion Exitosa con un dispositio Bluetooth
        selectImgBluetooh(swConnectBT);
        frmt_menuB0.actImgMenuSec(idMenuA, pos_idResc, idAux);

        //frmt_menuB0.actImgMenuSec(idMenuA, 0);
    }

    @Override
    public void onClick_AceptarEntrada(String mensaje){
        String msg = "";
        if (mensaje.length()<150) {
            for (int i = 0;i < mensaje.length(); i++) {
                if ((int) mensaje.charAt(i) <= 127 & (int) mensaje.charAt(i) >= 32)
                    msg = msg + mensaje.charAt(i);
            }
            srfv_matrix.setMensaje(msg);
        }
        else
            Toast.makeText(this,"Ingrese menos de 150 caracteres...." + mensaje.length(),Toast.LENGTH_SHORT).show();
        //toast.show();
    }

    public String formarMSG(){
        int sizeN = 0 ;
        int j = 0, i =0;
        int aux = 0;
        String mensajeBT = "";

        //mensajeBT = "1" + tipoPantalla + frmt_panel1_aux.arls_srfv_Matrix.size();
        mensajeBT = "1" + "1" + frmt_panel1_aux.arls_srfv_Matrix.size();
        for (i = 0; i < frmt_panel1_aux.arls_srfv_Matrix.size(); i++){
            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getAnimacion();
            //Log.v("ANIMACION",":::" + frmt_panel1_aux.arls_srfv_Matrix.get(i).getAnimacion());

            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getSrfv_textsize();
            sizeN = Integer.toString(frmt_panel1_aux.arls_srfv_Matrix.get(i).getMensaje().length()).length();
            for (j = 1; j<= 3-sizeN; j++){
                mensajeBT = mensajeBT + "0";
            }
            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getMensaje().length();

            //concatenamos la velocidad de la animación
            aux = frmt_panel1_aux.arls_srfv_Matrix.get(i).getSrfv_aceleracion()/10;
            if (aux >= 10) mensajeBT = mensajeBT + "0" + aux;
            else mensajeBT = mensajeBT + "00" + aux;


            sizeN = Integer.toString(frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosx1()).length();
            for(j = 1;j<=3-sizeN;j++){
                mensajeBT = mensajeBT + "0";
            }
            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosx1();

            sizeN = Integer.toString(frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosy1()).length();
            for(j = 1;j<=3-sizeN;j++){
                mensajeBT = mensajeBT + "0";
            }
            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosy1();

            sizeN = Integer.toString(frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosx2()).length();
            for(j = 1;j<=3-sizeN;j++){
                mensajeBT = mensajeBT + "0";
            }
            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosx2();

            sizeN = Integer.toString(frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosy2()).length();
            for(j = 1;j<=3-sizeN;j++){
                mensajeBT = mensajeBT + "0";
            }
            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getPosy2();

            mensajeBT = mensajeBT + frmt_panel1_aux.arls_srfv_Matrix.get(i).getMensaje();

        }
        return mensajeBT;
    }
    private void selectImgPantalla(int pantalla){
        switch (pantalla) {
            case 1:
                idAux = R.mipmap.pantalla1_1;
                pos_idResc= 0;
                break;  //pantalla 1
            case 2:
                idAux = R.mipmap.pantalla2_1;
                pos_idResc= 1;
                break;  //pantalla 2
            case 3:
                idAux = R.mipmap.pantalla6_1;
                pos_idResc= 2;
                break;  //pantalla 3
            case 4:
                idAux = R.mipmap.pantalla3_1;
                pos_idResc= 3;
                break;  //pantalla 3
            case 5:
                idAux = R.mipmap.pantalla7_1;
                pos_idResc= 4;
                break;  //pantalla 3
            case 6:
                idAux = R.mipmap.pantalla4_1;
                pos_idResc= 5;
                break;  //pantalla 3
            case 7:
                idAux = R.mipmap.pantalla5_1;
                pos_idResc= 6;
                break;  //pantalla 3
        }
    }
    private void selectImgBluetooh(int opc){
        switch (opc) {
            case 0:
                idAux = 0;
                pos_idResc= -1;
                break;  //
            case 1:
                idAux = R.mipmap.blue_1;
                pos_idResc= 0;
                break;  //pantalla 2
        }
    }

    private void btDesconectado(){
        if (swConnectBT == 1){
            try{
                bt_socketMain.close();
                swConnectBT = 0;
                selectImgBluetooh(swConnectBT);
                frmt_menuB0.actImgMenuSec(idMenuA, pos_idResc, idAux);
                Toast.makeText(this,"Se ha Desconectado de la Pantalla Led", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(this,"Ocurrió un error al Desconectarse....", Toast.LENGTH_SHORT).show();
                //Log.v("ERROR", e.toString());
                //Log.v("swCONECTADO",Integer.toString(swConnectBT));
            }

        }
        //Toast.makeText(this.getApplication(),"DESCONECTADO",Toast.LENGTH_SHORT).show();
    }
}
