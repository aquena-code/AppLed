package com.software.alv.proy_3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

/**
 * Created by ALVARO on 29/01/2019.
 */

public class Class_FrmtPanel extends Fragment {

    public interface I_FrmtPanelListener{
        public void onClickPanels_srfv(int idsfv);
        public void infcCallBacksSrfvMatrix_IniciarSrfvs(String srfv);
        public void infcCallBacksMenu4_Reset();// del SurfaceView
        public void infcCallBacksMenu2_Reset();// del SurfaceView
        public void onUpDowm_ActMenuB0();//Actualiza el grafico en el menuB
    }

    public I_FrmtPanelListener infc_callbacksPanels_srfv;
    public int cant_lnyt = 1;
    public int id_res_frmt;
    public String idMenuB = "0";
    public int x_Led, y_Led;
    public ArrayList<Integer> arls_Res_lnty;
    public ArrayList<Class_SrfvMatrix> arls_srfv_Matrix = new ArrayList<Class_SrfvMatrix>();
    private int sw_inicio = 0;

    private LinearLayout lnyt_matrix1;
    private Class_DatosPantalla class_datosPantalla;



    public ArrayList<Class_SrfvMatrix> getArls_srfv_Matrix(){
        return this.arls_srfv_Matrix;
    }

    public Class_FrmtPanel(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        infc_callbacksPanels_srfv = (I_FrmtPanelListener) activity;
        //Log.v("ON RESUME", "Ha ingresado a .......onAttach de panel 1");
    }

    @Override
    public void onCreate(Bundle saveState){
        super.onCreate(saveState);

        idMenuB = getArguments().getString("ID_MENUB");
        class_datosPantalla = getArguments().getParcelable("PROP_MENUB");
        cant_lnyt = class_datosPantalla.getArls_DatosSrfv().size();
        //cant_lnyt = (Class_Datos.arls_datosPantalla.get(Integer.parseInt(idMenuB))).getArls_DatosSrfv().size();

        sw_inicio = 0;
        Log.v("FRMT-PANEL", "ONCREATE");
    }

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){
        int posx1=0, posy1=0, posx2=0, posy2=0;
        //View vw_panel = layoutInflater.inflate(R.layout.layt_frmtpanel1,viewGroup,false);
        if (sw_inicio == 0) {
            sw_inicio = 1;
            Log.v("FRMT-PANEL", "-----> ON-CREATE-VIEW-1");
            //View vw_panel = layoutInflater.inflate(id_res_frmt,viewGroup,false);
            View vw_panel = layoutInflater.inflate(class_datosPantalla.getRes_frmt(), viewGroup, false);


            for (int i = 0; i < cant_lnyt; i++) {
                x_Led = class_datosPantalla.getArls_DatosSrfv().get(i).getX_Led();
                y_Led = class_datosPantalla.getArls_DatosSrfv().get(i).getY_Led();
                posx1 = class_datosPantalla.getArls_DatosSrfv().get(i).getPosx1();
                posy1 = class_datosPantalla.getArls_DatosSrfv().get(i).getPosy1();
                posx2 = class_datosPantalla.getArls_DatosSrfv().get(i).getPosx2();
                posy2 = class_datosPantalla.getArls_DatosSrfv().get(i).getPosy2();

                //lnyt_matrix1 = (LinearLayout) vw_panel.findViewById(arls_Res_lnty.get(i));
                lnyt_matrix1 = (LinearLayout) vw_panel.findViewById(class_datosPantalla.getArls_DatosSrfv().get(i).getRes_lnyt());
                arls_srfv_Matrix.add(new Class_SrfvMatrix(this.getActivity(), infc_callbacksPanels_srfv, i, x_Led, y_Led, posx1, posy1, posx2, posy2));
                //infc_callbacksPanels_srfv.onClickPanels_srfv(arls_srfv_Matrix.get(i));
                lnyt_matrix1.addView(arls_srfv_Matrix.get(i));

            }
            Log.v("FRMT-PANEL", "<----- ON-CREATE-VIEW-1");
            return vw_panel;

        }
        else {
            Log.v("FRMT-PANEL", "-----> ON-CREATE-VIEW-2");
            Log.v("FRMT-PANEL", "<----- ON-CREATE-VIEW-2");
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }

    }

    @Override
    public void onActivityCreated (Bundle bundle){
        super.onActivityCreated(bundle);

        Log.v("FRMT-PANEL", "Ha ingresado a .......onActivityCreated de Panel 1");
        View vw_panel;
        /*
        vw_panel = getView();
        DX0 = vw_panel.getWidth();
        for (int i = 0; i<cant_lnyt; i++){
            lnyt_matrix1 = (LinearLayout) vw_panel.findViewById(arls_Res_lnty.get(i));
            arls_srfv_Matrix.add(new Srfv_Matrix(getContext(),infc_callbacksPanels_srfv,Integer.toString(i),DX0,DY0));
            infc_callbacksPanels_srfv.onClickPanels_srfv(arls_srfv_Matrix.get(i));
            lnyt_matrix1.addView(arls_srfv_Matrix.get(i));
        }
        */
        arls_srfv_Matrix.get(0).setToken(1);
        infc_callbacksPanels_srfv.onClickPanels_srfv(0); //para inicializar con el primer Srfv_Matrix
        Log.v("FRMT-PANEL", "Ha Salido de.......onActivityCreated de Panel 1");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.v("FRMT-PANEL", "------> ON PAUSE");
        for (int i = 0; i < arls_srfv_Matrix.size(); i++){
                if(arls_srfv_Matrix.get(i).getThrd_hiloLoop() != null) {
                    arls_srfv_Matrix.get(i).setSrfv_evento("41");
                    while (arls_srfv_Matrix.get(i).getThrd_hiloLoop().isAlive()) {

                    }
                }
        }
        infc_callbacksPanels_srfv.infcCallBacksMenu4_Reset();
        Log.v("FRMT-PANEL", "<------ ON PAUSE");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("FRMT-PANEL", "---> ONRESUME");
        Log.v("FRMT-PANEL", "<--- ONRESUME");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("FRMT-PANEL", "---> ONSTART");
        Log.v("FRMT-PANEL", "<--- ONSTART");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("FRMT-PANEL", "---> ON-STOP");
        Log.v("FRMT-PANEL", "<--- ON-STOP");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("FRMT-PANEL", "---> ONDESTROY");
        Log.v("FRMT-PANEL", "<--- ONDESTROY");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v("FRMT-PANEL", "---> ONATTACH");
        Log.v("FRMT-PANEL", "<--- ONATTACH");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        infc_callbacksPanels_srfv = null;
    }

}

