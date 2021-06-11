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
 * Created by ALVARO on 23/01/2019.
 */
public class Class_frmtMenuA extends ListFragment{

    public interface I_frmtMenuAListener{
        public void onClickMenuA(String id);
    }

    private I_frmtMenuAListener myListener;

    public Class_frmtMenuA(){}  //Constructor vacio

    @Override
    public void onAttach( Activity activity){
        super.onAttach(activity);

        try{
            myListener = (I_frmtMenuAListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+"Debe implementar la Interface");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //actImgMenuMain(position);
        myListener.onClickMenuA(Class_Datos.ARLS_MAINMENU.get(position).getId());
    }

    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        actImgMenuMain(0);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //view.setBackgroundColor(Color.rgb(5,60,100));
    }

    @Override
    public void onDetach(){
        super.onDetach();
        myListener = null;
    }

    public void actImgMenuMain(final int posi){
        setListAdapter(new Bsad_Adaptador(getActivity(), R.layout.layt_item_menu, Class_Datos.ARLS_MAINMENU) {
            @Override
            public void onEntrada(Object item, View view, int posicion) {
                //Log.v("ON RESUME", "---> LLAMADA A ONENTRADA DE MENU A");
                if (item != null) {
                    if (posicion == posi)
                        ((ImageView) view.findViewById(R.id.imvw_imagenOpcion)).setImageResource(Class_Datos.ARLS_MAINMENU2.get(posicion).getId_imagen());
                    else
                        ((ImageView) view.findViewById(R.id.imvw_imagenOpcion)).setImageResource(((Class_Datos.Class_ItemMenu) item).getId_imagen());

                }
                //view.setBackgroundColor(Color.RED);
            }
        });
    }

}
