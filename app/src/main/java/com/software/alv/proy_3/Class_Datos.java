package com.software.alv.proy_3;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ALVARO on 16/01/2019.
 */
public class Class_Datos {

    public static int i_final_max = 0;

    public static ArrayList<Class_ItemMenu> ARLS_MAINMENU = new ArrayList<>();
    public static ArrayList<Class_ItemMenu> ARLS_MAINMENU2 = new ArrayList<>();

    public static ArrayList<ArrayList<Class_ItemMenu>> ARLS_MENUSEC = new ArrayList<>();
    public static ArrayList<Class_ItemMenu> ARLS_MENUSEC0 = new ArrayList<>();
    public static ArrayList<Class_ItemMenu> ARLS_MENUSEC1 = new ArrayList<>();
    public static ArrayList<Class_ItemMenu> ARLS_MENUSEC2 = new ArrayList<>();
    public static ArrayList<Class_ItemMenu> ARLS_MENUSEC3 = new ArrayList<>();
    public static ArrayList<Class_ItemMenu> ARLS_MENUSEC4 = new ArrayList<>();
    public static ArrayList<Class_ItemMenu> ARLS_MENUSEC5 = new ArrayList<>();

    public static ArrayList<Class_DatosPantalla> arls_datosPantalla = new ArrayList<Class_DatosPantalla>();

    static {

        crear_arlsMainMenu();   crear_arlsMainMenu2();
        crear_ArlsMenuSec();
        crear_arlsDatosPantallaLed();
        Log.v("DATOS", "---> STATIC");

    }

    public static void crear_arlsMainMenu(){
        ingresarDatos(ARLS_MAINMENU, new Class_ItemMenu("0", R.mipmap.inicio_0));
        ingresarDatos(ARLS_MAINMENU, new Class_ItemMenu("1", R.mipmap.texto_0));
        ingresarDatos(ARLS_MAINMENU, new Class_ItemMenu("2", R.mipmap.anim_0));
        ingresarDatos(ARLS_MAINMENU, new Class_ItemMenu("3", R.mipmap.vel_0));
        ingresarDatos(ARLS_MAINMENU, new Class_ItemMenu("4", R.mipmap.sim_0));
        ingresarDatos(ARLS_MAINMENU, new Class_ItemMenu("5", R.mipmap.conx_0));
    }
    public static void crear_arlsMainMenu2(){
        ingresarDatos(ARLS_MAINMENU2, new Class_ItemMenu("0", R.mipmap.inicio_1));
        ingresarDatos(ARLS_MAINMENU2, new Class_ItemMenu("1", R.mipmap.texto_1));
        ingresarDatos(ARLS_MAINMENU2, new Class_ItemMenu("2", R.mipmap.anim_1));
        ingresarDatos(ARLS_MAINMENU2, new Class_ItemMenu("3", R.mipmap.vel_1));
        ingresarDatos(ARLS_MAINMENU2, new Class_ItemMenu("4", R.mipmap.sim_1));
        ingresarDatos(ARLS_MAINMENU2, new Class_ItemMenu("5", R.mipmap.conx_1));
    }
    public static void ingresarDatos(ArrayList<Class_ItemMenu> arls_menu, Class_ItemMenu itemMenu){
        arls_menu.add(itemMenu);
    }

    public static void crear_ArlsMenuSec(){

        ingresarDatos(ARLS_MENUSEC0, new Class_ItemMenu("0", R.mipmap.pantalla1_0));
        ingresarDatos(ARLS_MENUSEC0, new Class_ItemMenu("1", R.mipmap.pantalla2_0));
        ingresarDatos(ARLS_MENUSEC0, new Class_ItemMenu("2", R.mipmap.pantalla6_0));
        ingresarDatos(ARLS_MENUSEC0, new Class_ItemMenu("3", R.mipmap.pantalla3_0));
        ingresarDatos(ARLS_MENUSEC0, new Class_ItemMenu("4", R.mipmap.pantalla7_0));
        ingresarDatos(ARLS_MENUSEC0, new Class_ItemMenu("5", R.mipmap.pantalla4_0));
        ingresarDatos(ARLS_MENUSEC0, new Class_ItemMenu("6", R.mipmap.pantalla5_0));

        ingresarDatos(ARLS_MENUSEC1, new Class_ItemMenu("0", R.mipmap.incre_0));
        ingresarDatos(ARLS_MENUSEC1, new Class_ItemMenu("1", R.mipmap.cinco_0));
        ingresarDatos(ARLS_MENUSEC1, new Class_ItemMenu("2", R.mipmap.decre_0));
        ingresarDatos(ARLS_MENUSEC1, new Class_ItemMenu("3", R.mipmap.entrada_texto_0));

        ingresarDatos(ARLS_MENUSEC2, new Class_ItemMenu("0", R.mipmap.esta_0));
        ingresarDatos(ARLS_MENUSEC2, new Class_ItemMenu("1", R.mipmap.iz_der_0));
        ingresarDatos(ARLS_MENUSEC2, new Class_ItemMenu("2", R.mipmap.der_iz_0));
        ingresarDatos(ARLS_MENUSEC2, new Class_ItemMenu("3", R.mipmap.mix_0));
        ingresarDatos(ARLS_MENUSEC2, new Class_ItemMenu("4", R.mipmap.le_izq_der_0));

        ingresarDatos(ARLS_MENUSEC3, new Class_ItemMenu("0", R.mipmap.incre_0));
        ingresarDatos(ARLS_MENUSEC3, new Class_ItemMenu("1", R.mipmap.dos_0));
        ingresarDatos(ARLS_MENUSEC3, new Class_ItemMenu("2", R.mipmap.decre_0));



        ingresarDatos(ARLS_MENUSEC4, new Class_ItemMenu("0", R.mipmap.play_0));
        ingresarDatos(ARLS_MENUSEC4, new Class_ItemMenu("1", R.mipmap.stop_0));

        ingresarDatos(ARLS_MENUSEC5, new Class_ItemMenu("0", R.mipmap.blue_0));
        ingresarDatos(ARLS_MENUSEC5, new Class_ItemMenu("1", R.mipmap.load_0));

        ARLS_MENUSEC.add(ARLS_MENUSEC0);
        ARLS_MENUSEC.add(ARLS_MENUSEC1);
        ARLS_MENUSEC.add(ARLS_MENUSEC2);
        ARLS_MENUSEC.add(ARLS_MENUSEC3);
        ARLS_MENUSEC.add(ARLS_MENUSEC4);
        ARLS_MENUSEC.add(ARLS_MENUSEC5);
    }

    public static void crear_arlsDatosPantallaLed(){
        arls_datosPantalla.add(new Class_DatosPantalla(R.layout.layt_frmtpantalla1, new ArrayList<Class_DatosSubPantalla>()));
        arls_datosPantalla.get(0).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 32, R.id.lnyt_P1_srfv1, 0, 0, 63, 31));

        arls_datosPantalla.add(new Class_DatosPantalla(R.layout.layt_frmtpantalla2, new ArrayList<Class_DatosSubPantalla>()));
        arls_datosPantalla.get(1).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 16, R.id.lnyt_P2_srfv1, 0, 0, 63, 15));
        arls_datosPantalla.get(1).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 16, R.id.lnyt_P2_srfv2, 0, 16, 63, 31));

        arls_datosPantalla.add(new Class_DatosPantalla(R.layout.layt_frmtpantalla6, new ArrayList<Class_DatosSubPantalla>()));
        arls_datosPantalla.get(2).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 16, R.id.lnyt_P6_srfv1, 0, 0, 63, 15));
        arls_datosPantalla.get(2).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P6_srfv2, 0, 16, 63, 23));
        arls_datosPantalla.get(2).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P6_srfv3, 0, 24, 63, 31));


        arls_datosPantalla.add(new Class_DatosPantalla(R.layout.layt_frmtpantalla3, new ArrayList<Class_DatosSubPantalla>()));
        arls_datosPantalla.get(3).getArls_DatosSrfv().add(new Class_DatosSubPantalla(16, 16, R.id.lnyt_P3_srfv1, 0, 0, 15, 15));
        arls_datosPantalla.get(3).getArls_DatosSrfv().add(new Class_DatosSubPantalla(48, 16, R.id.lnyt_P3_srfv2, 16, 0, 63, 15));
        arls_datosPantalla.get(3).getArls_DatosSrfv().add(new Class_DatosSubPantalla(16, 16, R.id.lnyt_P3_srfv3, 0, 16, 15, 31));
        arls_datosPantalla.get(3).getArls_DatosSrfv().add(new Class_DatosSubPantalla(48, 16, R.id.lnyt_P3_srfv4, 16, 16, 63, 31));

        arls_datosPantalla.add(new Class_DatosPantalla(R.layout.layt_frmtpantalla7, new ArrayList<Class_DatosSubPantalla>()));
        arls_datosPantalla.get(4).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P7_srfv1, 0, 0, 63, 7));
        arls_datosPantalla.get(4).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P7_srfv2, 0, 8, 63, 15));
        arls_datosPantalla.get(4).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 16, R.id.lnyt_P7_srfv3, 0, 16, 63, 31));

        arls_datosPantalla.add(new Class_DatosPantalla(R.layout.layt_frmtpantalla4, new ArrayList<Class_DatosSubPantalla>()));
        arls_datosPantalla.get(5).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P4_srfv1, 0, 0, 63, 7));
        arls_datosPantalla.get(5).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P4_srfv2, 0, 8, 63, 15));
        arls_datosPantalla.get(5).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P4_srfv3, 0, 16, 63, 23));
        arls_datosPantalla.get(5).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 8, R.id.lnyt_P4_srfv4, 0, 24, 63, 31));

        arls_datosPantalla.add(new Class_DatosPantalla(R.layout.layt_frmtpantalla5, new ArrayList<Class_DatosSubPantalla>()));
        arls_datosPantalla.get(6).getArls_DatosSrfv().add(new Class_DatosSubPantalla(64, 16, R.id.lnyt_P5_srfv1, 0, 0, 63, 15));//1
        arls_datosPantalla.get(6).getArls_DatosSrfv().add(new Class_DatosSubPantalla(32, 8, R.id.lnyt_P5_srfv2, 0, 16, 31, 23));//2
        arls_datosPantalla.get(6).getArls_DatosSrfv().add(new Class_DatosSubPantalla(32, 8, R.id.lnyt_P5_srfv3, 32, 16, 63, 23));//3
        arls_datosPantalla.get(6).getArls_DatosSrfv().add(new Class_DatosSubPantalla(32, 8, R.id.lnyt_P5_srfv4, 0, 24, 31, 31));//4
        arls_datosPantalla.get(6).getArls_DatosSrfv().add(new Class_DatosSubPantalla(32, 8, R.id.lnyt_P5_srfv5, 32, 24, 63, 31));//5

    }

    public static class Class_ItemMenu {

        public String id;
        public int id_imagen;

        public Class_ItemMenu(String id,int id_imagen){
            this.id_imagen = id_imagen;
            this.id = id;
        }

        public int getId_imagen(){  return this.id_imagen;}
        public void setId_imagen(int id_imagen) {   this.id_imagen=id_imagen;    }

        public String getId(){  return id; }
    }
}
