package com.software.alv.proy_3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ALVARO on 17/01/2019.
 */
public class Class_DatosPantalla implements Parcelable {
    public int res_frmt;
    public ArrayList<Class_DatosSubPantalla> arls_DatosSubPantalla = new ArrayList<Class_DatosSubPantalla>();

    public Class_DatosPantalla(int res_frmt, ArrayList<Class_DatosSubPantalla> arrayList){
        this.res_frmt = res_frmt;
        this.arls_DatosSubPantalla = arrayList;
    }

    public ArrayList<Class_DatosSubPantalla> getArls_DatosSrfv(){
        return arls_DatosSubPantalla;
    }

    public void setRes_frmt(int res_frmt){
        this.res_frmt = res_frmt;
    }
    public int getRes_frmt(){
        return this.res_frmt;
    }

    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(res_frmt);
        parcel.writeTypedList(arls_DatosSubPantalla);
    }

    public Class_DatosPantalla(Parcel parcel){
        this.res_frmt = parcel.readInt();
        parcel.readTypedList(this.arls_DatosSubPantalla, Class_DatosSubPantalla.CREATOR);
    }
    public static final Parcelable.Creator<Class_DatosPantalla> CREATOR = new Parcelable.Creator<Class_DatosPantalla>(){
        public Class_DatosPantalla createFromParcel(Parcel parcel){
            return new Class_DatosPantalla(parcel);
        }
        public Class_DatosPantalla[] newArray(int size){
            return new Class_DatosPantalla[size];
        }
    };
}
