package com.software.alv.proy_3;

import android.app.Activity;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by ALVARO on 27/01/2019.
 */
public class Class_frmtMenuB extends ListFragment {
    public interface I_frmtMenuBListener{
        public void onClickFrmtMenuB(String idMenuB);
    }

    private I_frmtMenuBListener myListener;
    private int id_Resc_numSizeText = -1;
    public String idMenuA = "0";

    public Class_frmtMenuB(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        myListener = (I_frmtMenuBListener) activity;
    }

    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        //idMenuA = getArguments().getString("ID");
        //id_Resc_numSizeText = getArguments().getInt("ID_Resc");

       /* if(id == null) {
            id = "0";
        }*/
        //Log.v("LISTFRAMENT B", " ---> ONCREATE");
        //actImgMenuSec("0", -2, 0);
        //Log.v("LISTFRAMENT B", " <---ONCREATE");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view.setBackgroundColor(Color.BLACK);
        //Log.v("VISTA", "Ha ingresado ON VIEW BBBB");
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id){
        //myListener.onClickFrmtMenuB(Class_Datos.ARLS_MAINMENU.get(position).getId());
        myListener.onClickFrmtMenuB(String.valueOf(position));
    }

    public void actImgMenuSec(String idA, final int posidB, final int idResc){
        idMenuA = idA;
        id_Resc_numSizeText = idResc;
        setListAdapter(new Bsad_Adaptador(getActivity(), R.layout.layt_item_submenu, Class_Datos.ARLS_MENUSEC.get(Integer.parseInt(idMenuA))) {
            @Override
            public void onEntrada(Object item, View view, int posicion) {
                //Log.v("FRAGMENT B", "Ha ingresado ONENTRADA " + posidB);
                if (posicion == posidB)
                    ((ImageView) view.findViewById(R.id.imvw_imagenOpcionB)).setImageResource(id_Resc_numSizeText);
                else
                    ((ImageView) view.findViewById(R.id.imvw_imagenOpcionB)).setImageResource(((Class_Datos.Class_ItemMenu) item).getId_imagen());

                /*
                if ((idMenuA == "1"||idMenuA == "3") & posicion == 1){
                    ((ImageView) view.findViewById(R.id.imvw_imagenOpcionB)).setImageResource(id_Resc_numSizeText);
                }
                else
                    ((ImageView) view.findViewById(R.id.imvw_imagenOpcionB)).setImageResource(((Class_Datos.Class_ItemMenu) item).getId_imagen());

            */
            }
        });
    }


}
