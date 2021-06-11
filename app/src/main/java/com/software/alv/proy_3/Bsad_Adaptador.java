package com.software.alv.proy_3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by ALVARO on 27/01/2019.
 */
public abstract class Bsad_Adaptador extends BaseAdapter {

    private Context context;
    private int id_LayoutItem;
    private ArrayList<?> arls_ListaItem;

    public Bsad_Adaptador(Context context, int id_LayoutItem, ArrayList<?> arls_ListaItem){
        this.context = context;
        this.id_LayoutItem = id_LayoutItem;
        this.arls_ListaItem = arls_ListaItem;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewgroup){
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id_LayoutItem,viewgroup,false);
        }
        onEntrada(arls_ListaItem.get(posicion), view, posicion);
        return view;
    }

    @Override
    public int getCount(){
        return arls_ListaItem.size();
    }
    @Override
    public Object getItem(int posicion){
        return arls_ListaItem.get(posicion);
    }
    @Override
    public long getItemId(int posicion){
        return posicion;
    }

    public abstract void onEntrada(Object item, View view, int posicion);
}
