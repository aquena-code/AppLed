package com.software.alv.proy_3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ALVARO on 17/01/2019.
 */
public class Class_DatosSubPantalla implements Parcelable{
    private int x_Led;
    private int y_Led;
    private int posx1=0, posy1=0, posx2=0, posy2=0;
    private int res_lnyt;

    public Class_DatosSubPantalla(int x_Led, int y_Led, int res_lnyt, int posx1, int posy1, int posx2, int posy2) {
        this.x_Led = x_Led;
        this.y_Led = y_Led;
        this.posx1 = posx1;
        this.posy2 = posy2;
        this.posx2 = posx2;
        this.posy1 = posy1;
        this.res_lnyt = res_lnyt;
    }

    public void setX_Led(int x_Led) {
        this.x_Led = x_Led;
    }

    public int getX_Led() {
        return this.x_Led;
    }

    public int getY_Led() {
        return this.y_Led;
    }

    public void setY_Led(int y_Led) {
        this.y_Led = y_Led;
    }

    public int getRes_lnyt() {
        return this.res_lnyt;
    }

    public void setRes_lnyt(int res_lnyt) {
        this.res_lnyt = res_lnyt;
    }

    public void setPosx1(int posx1){
        this.posx1 = posx1;
    }
    public int getPosx1(){
        return this.posx1;
    }
    public void setPosy1(int posy1){
        this.posy1 = posy1;
    }
    public int getPosy1(){
        return this.posy1;
    }
    public void setPosx2(int posx2){
        this.posx2 = posx2;
    }
    public int getPosx2(){
        return this.posx2;
    }
    public void setPosy2(int posy2){
        this.posy2 = posy2;
    }
    public int getPosy2(){
        return this.posy2;
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(x_Led);
        parcel.writeInt(y_Led);
        parcel.writeInt(posx1);
        parcel.writeInt(posx2);
        parcel.writeInt(posy1);
        parcel.writeInt(posy2);
        parcel.writeInt(res_lnyt);
    }

    public Class_DatosSubPantalla(Parcel parcel) {
        this.x_Led = parcel.readInt();
        this.y_Led = parcel.readInt();
        this.posx1 = parcel.readInt();
        this.posy1 = parcel.readInt();
        this.posx2 = parcel.readInt();
        this.posy2 = parcel.readInt();
        this.res_lnyt = parcel.readInt();
    }

    public static final Parcelable.Creator<Class_DatosSubPantalla> CREATOR = new Parcelable.Creator<Class_DatosSubPantalla>() {
        public Class_DatosSubPantalla createFromParcel(Parcel parcel) {
            return new Class_DatosSubPantalla(parcel);
        }

        public Class_DatosSubPantalla[] newArray(int size) {
            return new Class_DatosSubPantalla[size];
        }
    };
}
