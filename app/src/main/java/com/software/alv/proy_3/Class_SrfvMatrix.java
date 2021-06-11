package com.software.alv.proy_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by ALVARO on 31/01/2019.
 */
public class Class_SrfvMatrix extends SurfaceView {


    private int id; //identificador del surfaceview

    private String mensaje ="Texto"; // Texto del panel
    private int srfv_textsize = 1, minSize = 1, maxSize=1; //Tamaño del texto en 1,2,3,4 del surfaceview, de Pequeño a Grande
    private String estado = "0",auxEst="0"; //Estado del Surfaceview
    private String srfv_evento = "-1", texto = "-"; // Eventos del surfaceview
    private String animacion = "21";    //Guarda la animacion del surfaceview por defecto tiene la animacion de izq a der
    private int  srfv_aceleracion =960; // velocidad de desplazamiento del mensaje, 1[px]/500 mls
    private int canvasWidth = 0;    //Guarda el ancho del canvas de surfaceview
    private int canvasHeight = 0;   //Guarda el alto del canvas de surfaceview
    private int sw1 = 0;//control para inicilizar valores de una animacion
    public int swThread = 0; //control para verificar si esta activo el Thread de animacion
    private int DX0 = 1, DY0 = 1;   //Distancia base que recorre el mensaje en el surfaceview
    private int m_totalpixeles = 0;
    private int x_led, y_led;   //Dimensiones del surfaceview en unidades led de la pantalla
    private int posx1=0, posy1=0, posx2=0, posy2=0;
    private int id_Resc_numSizeText = R.mipmap.uno_0;
    private int pos_idResc_numSizeText = 1;
    private int id_Resc_aceleracion = R.mipmap.uno_0;
    private int pos_idResc_aceleracion = 1;
    private int id_Resc_animacion = R.mipmap.iz_der_1;
    private int pos_idResc_anim = 1;
    private int id_Resc_sim = R.mipmap.stop_1;
    private int pos_idResc_sim = 1;
    private int posC = -1;
    private int token = 0;
    private int sw_inicio = 0;

    private int t_espera=0, t_espera_cont = -1, t_duracion = 0, t_final = 0;
    private int aux_sentido = 0, aux_sentido2 = 0;

    private SurfaceHolder surfaceHolder;
    private Class_ThrdHiloLoop thrd_hiloLoop;

    private float RR;

    public Class_ThrdHiloLoop getThrd_hiloLoop(){
        return this.thrd_hiloLoop;
    }
    public int getId_Resc_animacion(){
        return this.id_Resc_animacion;
    }

    public int getPos_idResc_anim(){
        return this.pos_idResc_anim;
    }
    public int getPos_idResc_numSizeText(){
        return this.pos_idResc_numSizeText;
    }
    public int getPos_idResc_aceleracion(){
        return this.pos_idResc_aceleracion;
    }
    public int getPos_idResc_sim(){
        return this.pos_idResc_sim;
    }
    public int getId_Resc_sim(){
        return this.id_Resc_sim;
    }

    public void setToken(int t){
        this.token = t;
    }
    public int getToken(){
        return this.token;
    }
    public int getSrfv_aceleracion(){
        return this.srfv_aceleracion;
    }
    public String getAnimacion(){
        return this.animacion.substring(animacion.length()-1);
    }
    public int getSw1() {
        return sw1;
    }
    public void setSw1(int sw1) {
        this.sw1 = sw1;
    }
    public void setSrfv_textsize(int srfv_textsize){
        this.srfv_textsize = srfv_textsize;
    }
    public int getSrfv_textsize(){
        return this.srfv_textsize;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public String getEstado(){
        return this.estado;
    }
    public String getSrfv_evento() {
        return srfv_evento;
    }



    private void selectImgTamañoLetra(int srfv_textsize){
        switch (srfv_textsize) {
            case 1:
                id_Resc_numSizeText = R.mipmap.uno_0;
                break;  //numero 4
            case 2:
                id_Resc_numSizeText = R.mipmap.dos_0;
                break;  //numero 3
            case 3:
                id_Resc_numSizeText = R.mipmap.tres_0;
                break;  //numero 2
            case 4:
                id_Resc_numSizeText = R.mipmap.cuatro_0;
                break;  //numero 1
        }

    }
    private void selectImgAnim(String animacion){
        switch (animacion) {
            case "20":
                id_Resc_animacion = R.mipmap.esta_1;
                pos_idResc_anim = 0;
                break;  //estatico
            case "21":
                id_Resc_animacion = R.mipmap.iz_der_1;
                pos_idResc_anim = 1;
                break;  //izquierda a derecha
            case "22":
                id_Resc_animacion = R.mipmap.der_iz_1;
                pos_idResc_anim = 2;
                break;  //derecha a izquierda
            case "23":
                id_Resc_animacion = R.mipmap.mix_1;
                pos_idResc_anim = 3;
                break;  //lado a lado
            case "24":
                id_Resc_animacion = R.mipmap.le_izq_der_1;
                pos_idResc_anim = 4;
                break;  //lado a lado
        }
    }

    private void selectImgSim(String srfv_evento) {
        switch (srfv_evento) {
            case "40":
                id_Resc_sim = R.mipmap.play_1;
                pos_idResc_sim = 0;
                break;  //estatico
            case "41":
                id_Resc_sim = R.mipmap.stop_1;
                pos_idResc_sim = 1;
                break;  //izquierda a derecha

        }
    }
    public void setSrfv_evento(String srfv_evento) {
        int t_duracion_aux = this.t_duracion;
        int t_espera_aux = this.t_espera;



        //setThrd_hiloLoop(false);
        //thrd_hiloLoop.setRunning(false);
        //thrd_hiloLoop.interrupt();

        if (estado != "4") {

            switch (srfv_evento) {
                case "12":  //EVENTO PARA DECREMENTAR TAMAÑO DE TEXTO
                    if (srfv_textsize > minSize) {

                        srfv_textsize--;
                        paint.setTextSize(nuevo_sizeMensaje(srfv_textsize));
                        escalaX(paint, this.mensaje, srfv_textsize);
                        medio = -(paint.ascent() + paint.descent()) / 2;
                        act_tduracion(animacion);
                        //t_duracion = Math.round(((canvasWidth + paint.measureText(mensaje,0,mensaje.length()))/DX0)*srfv_aceleracion);
                        selectImgTamañoLetra(srfv_textsize);
                        infc_callbacksPanels_srfv.onUpDowm_ActMenuB0(); //Vuelve a crear el Menu B0 con el grafico actualizado
                        actualizar_Srfv(); //dibuja el texto con el nuevo tamaño
                    }
                    break;
                case "10":  //EVENTO PARA INCREMENTAR TAMAÑO DE TEXTO
                    if (srfv_textsize < maxSize) {

                        srfv_textsize++;
                        paint.setTextSize(nuevo_sizeMensaje(srfv_textsize));
                        escalaX(paint, this.mensaje, srfv_textsize);  //escalaX recalcula la escala de paint para el nuevo tamaño de letra
                        medio = -(paint.ascent() + paint.descent()) / 2;
                        act_tduracion(animacion);
                        selectImgTamañoLetra(srfv_textsize);
                        infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();    //Vuelve a crear el Menu B0 con el grafico actualizado

                        actualizar_Srfv(); //dibuja el texto con el nuevo tamaño
                    }
                    break;
                case "20":  //Evento para estado de animación estatico
                    estado = "0";

                    if (swThread == 0){
                        this.srfv_evento = srfv_evento;
                        swThread = 1;
                        srfv_aceleracion = 480; id_Resc_aceleracion = R.mipmap.dos_0;
                        //act_tduracion(srfv_evento);
                        playAnimacion(srfv_aceleracion);
                    }
                    else{
                        thrd_hiloLoop.setRunning(false);
                        swThread = 0;
                        if (!(srfv_evento == animacion)){
                            this.srfv_evento = srfv_evento;
                            srfv_aceleracion = 480; id_Resc_aceleracion = R.mipmap.dos_0;
                            swThread = 1;
                            playAnimacion(srfv_aceleracion);
                        }
                    }

                    selectImgAnim(srfv_evento);
                    infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();
                    break;
                case "21":  //Evento para estado de animacion de izq a Der
                case "22":  //Evento para estado de animacion de Der a Izq
                case "23":  //Evento para estado de animación de laDo a lado
                case "24":  //Evento para estado de animación de letra a letra izq der
                    estado = "0"; //para que se detenga la animación
                    if (swThread == 0){
                        this.srfv_evento = srfv_evento;
                        swThread = 1;
                        playAnimacion(srfv_aceleracion);
                    }
                    else{
                        thrd_hiloLoop.setRunning(false);
                        swThread = 0;
                        if (!(srfv_evento == animacion)){
                            this.srfv_evento = srfv_evento;
                            swThread = 1;
                            playAnimacion(srfv_aceleracion);
                        }
                    }
                    selectImgAnim(srfv_evento);
                    infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();
                    //Log.v("EVENTO","==== PASA POR 21 SETTT");
                    break;

                case "40":
                    if ( swThread == 0) {
                        this.srfv_evento = srfv_evento;
                        swThread = 1;
                        estado = "0";
                        playAnimacion(srfv_aceleracion);
                        selectImgSim(srfv_evento);
                        infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();
                    }

                    break;
                case "41":
                    if (swThread == 1) {
                        estado = "0";
                        thrd_hiloLoop.setRunning(false);
                        swThread = 0;
                        selectImgAnim(srfv_evento);
                        infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();
                        /*
                        this.srfv_evento = srfv_evento;
                        selectImgSim(srfv_evento);
                        infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();
                        swThread = 0;*/
                    }
                    break;
                case "30":  //Evento para incrementar la velocidad
                    if(animacion != "20") {
                        if (srfv_aceleracion > 30) {

                            srfv_aceleracion = srfv_aceleracion / 2;
                            //t_duracion = Math.round(((canvasWidth + paint.measureText(mensaje, 0, mensaje.length())) / DX0) * srfv_aceleracion);

                            switch (srfv_aceleracion) {
                                case 960:
                                    id_Resc_aceleracion = R.mipmap.uno_0;
                                    break;
                                case 480:
                                    id_Resc_aceleracion = R.mipmap.dos_0;
                                    break;
                                case 240:
                                    id_Resc_aceleracion = R.mipmap.tres_0;
                                    break;
                                case 120:
                                    id_Resc_aceleracion = R.mipmap.cuatro_0;
                                    break;
                                case 60:
                                    id_Resc_aceleracion = R.mipmap.cinco_0;
                                    break;
                                case 30:
                                    id_Resc_aceleracion = R.mipmap.seis_0;
                                    break;

                            }
                            infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();
                            //infc_callbacksPanels_srfv.infc_CallBacksSrfvTiempos_Actualizar(id);
                            //Log.v("ACELERACION","::"+srfv_aceleracion);
                            if (swThread == 1) {
                                thrd_hiloLoop.setRunning(false);
                                swThread = 0;
                            }
                            this.srfv_evento = srfv_evento;
                            //if (swThread == 0) {
                            estado = "0";
                            swThread = 1;
                            playAnimacion(srfv_aceleracion);
                            //}
                            Log.v("EVENTO", "Termina EVENTO +");
                        }
                    }
                    break;
                case "31": this.srfv_evento = srfv_evento;
                    break;
                case "32":  //Evento para Decrementar la velocidad
                    //this.srfv_evento = srfv_evento;

                    if(animacion != "20") {
                        if (srfv_aceleracion < 960) {
                            srfv_aceleracion = srfv_aceleracion * 2;
                            //t_duracion = Math.round(((canvasWidth + paint.measureText(mensaje, 0, mensaje.length())) / DX0) * srfv_aceleracion);

                            switch (srfv_aceleracion) {
                                case 960:
                                    id_Resc_aceleracion = R.mipmap.uno_0;
                                    break;
                                case 480:
                                    id_Resc_aceleracion = R.mipmap.dos_0;
                                    break;
                                case 240:
                                    id_Resc_aceleracion = R.mipmap.tres_0;
                                    break;
                                case 120:
                                    id_Resc_aceleracion = R.mipmap.cuatro_0;
                                    break;
                                case 60:
                                    id_Resc_aceleracion = R.mipmap.cinco_0;
                                    break;
                                case 30:
                                    id_Resc_aceleracion = R.mipmap.seis_0;
                                    break;
                            }
                            infc_callbacksPanels_srfv.onUpDowm_ActMenuB0();
                            if (swThread == 1) {
                                thrd_hiloLoop.setRunning(false);
                                swThread = 0;
                            }
                            this.srfv_evento = srfv_evento;
                            //if (swThread == 0) {
                            estado = "0";
                            swThread = 1;
                            playAnimacion(srfv_aceleracion);


                            Log.v("EVENTO", "Termina EVENTO -");
                        }
                    }
                    break;
            }

        }
        //this.srfv_evento = srfv_evento;
    }
    public void setId_Resc_numSizeText(int id_Resc_numSizeText){
        this.id_Resc_numSizeText = id_Resc_numSizeText;
    }
    public int getId_Resc_numSizeText(){
        return this.id_Resc_numSizeText;
    }

    public void setId_Resc_aceleracion ( int id_resc_aceleracion){
        this.id_Resc_aceleracion = id_resc_aceleracion;
    }
    public int getId_Resc_aceleracion(){
        return this.id_Resc_aceleracion;
    }
    public void setDX0(int dx0){
        this.DX0 = dx0;
    }
    public int getX_led(){
        return this.x_led;
    }
    public int getY_led(){
        return this.y_led;
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

    public void setT_espera(int t_espera){
        this.t_espera = t_espera;
        //infc_callbacksPanels_srfv.infc_CallBacksSrfvTiempos_Actualizar(id);
    }
    public int getT_espera(){
        return this.t_espera;
    }
    public void setT_final(int t_final){
        this.t_final = t_final;
    }
    public int getT_final(){
        return this.t_final;
    }

    public void setT_duracion (int t_duracion){
        this.t_duracion = t_duracion;
    }
    public int getT_duracion(){
        return this.t_duracion;
    }

    private Paint paint;//Paint del texto del panel

    private float x, y; //Coordenadas del mensaje

    private float TX_SZ;
    private float medio;
    private String ant_estado ="";

    private Toast toast;

    private Context context;

    public Class_FrmtPanel.I_FrmtPanelListener infc_callbacksPanels_srfv;

    public void setThrd_hiloLoop(boolean b) {
        this.thrd_hiloLoop.setRunning(b);
    }

    public String getMensaje(){
        return this.mensaje;
    }
    public void setMensaje(String string){

        int t_duracion_aux = t_duracion;
        int t_espera_aux = t_espera;

        escalaX(paint,string,srfv_textsize);
        //t_duracion = Math.round(((canvasWidth + paint.measureText(string,0,string.length()))/DX0)*srfv_aceleracion);
        //Log.v("DESPUES DE SCALE>>>>","::"+paint.measureText(string,0,string.length()));
        //Toast toast = Toast.makeText(context, "D:" + t_duracion + " :: "+t_duracion_aux, Toast.LENGTH_SHORT);
        //toast.show();
        this.mensaje = string;
        actualizar_Srfv();

    }



    // CONSTRUCTOR
    public Class_SrfvMatrix(final Context context, final Class_FrmtPanel.I_FrmtPanelListener infc_callbacksPanels_srfv, int id, final int x_led,final int y_led,int posx1, int posy1, int posx2, int posy2 ){
        super(context);
        this.context = context;
        this.id = id;
        this.x_led = x_led;
        this.y_led = y_led;
        this.posx1 = posx1;
        this.posy2 = posy2;
        this.posx2 = posx2;
        this.posy1 = posy1;
        this.infc_callbacksPanels_srfv = infc_callbacksPanels_srfv;

        sw_inicio = 0;

        //Inicilizamos los parametro generales del Texto del Panel
        paint = new Paint();
        paint.setColor(Color.RED);
        //paint.setTextSize(200);
        //paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/arial.ttf"));
        //paint.setTypeface(Typeface.SERIF);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/arialbd.ttf");
        paint.setTypeface(typeface);
        paint.setTextSize(100);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        TX_SZ = -paint.getTextSize()/(paint.ascent() - paint.descent());//ascent es negativo
//        id_Resc_numSizeText = R.mipmap.logo;    //Inicializamos el id del Res del grafico del numero de tamaño de texto


        surfaceHolder = getHolder();

        //Log.v("SRFV", "<---------- CONSTRUCTOR");
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (sw_inicio == 0) {
                    sw_inicio = 1;
                    Log.v("SRFV", "-------> SURFACE CREATED - INICIO");

                    Canvas canvas = surfaceHolder.lockCanvas();
                    canvasWidth = canvas.getWidth();
                    canvasHeight = canvas.getHeight();
                    DX0 = Math.round(canvas.getWidth() / x_led);  //MEJORAR HACIENDO QUE DX0 SEA FLOAT, PARA MAYOR PRESICION DE CALCULO.
                    //Log.v("DX0","::"+canvas.getWidth()+":"+x_led+"="+DX0);
                    minSize = 1;//Tamaño maximo de letra capaz de mostrar el srfv
                    maxSize = y_led / 8;//Tamaño maximo de letra capaz de mostrar el srfv
                    srfv_textsize = maxSize;//Por defecto arranca con el maximo tamaño de letra
                    selectImgTamañoLetra(srfv_textsize);
                    //Log.v("TEXTSIZE", "::" + paint.getTextSize() + "::" + (paint.ascent() - paint.descent()));
                    paint.setTextSize(nuevo_sizeMensaje(srfv_textsize)); //aplica el nuevo size del paint en funcion al tamaño de texto asignado por el usuario
                    escalaX(paint, mensaje, srfv_textsize);
                    x = 0;
                    y = canvas.getHeight() / 2;

                    t_espera_cont = -1;
                    m_totalpixeles = Math.round(((canvas.getWidth() + paint.measureText(mensaje, 0, mensaje.length())) / DX0));
                    t_duracion = m_totalpixeles * srfv_aceleracion;
                    medio = -(paint.ascent() + paint.descent()) / 2;
                    canvas.drawColor(Color.BLACK);
                    if (token == 1)
                        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, paint);

                    canvas.drawText(mensaje, 0, mensaje.length(), x, y + medio, paint);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    invocarCallBack();  //aplicamos este metodo para invocar el metodo onClickPanels_srfv del Interface

                    Log.v("SRFV", "<------- SURFACE CREATED - INICIO");
                }
                else{

                    Log.v("SRFV", "<------- SURFACE CREATED");
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Canvas canvas;
                canvas = holder.lockCanvas();
                act_Srfv2(canvas);
                holder.unlockCanvasAndPost(canvas);
                Log.v("SRFV", "<--- SURFFACECHANGED");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //thrd_hiloLoop.setRunning(false);
                //thrd_hiloLoop = null;
                //thrd_hiloLoop.setRunning(false);
            }
        });

        Log.v("SRFV", "<---------- CONSTRUCTOR");
    }

    public void invocarCallBack(){
        infc_callbacksPanels_srfv.infcCallBacksSrfvMatrix_IniciarSrfvs("Panel");
    }

    public void playAnimacion(int srfv_aceleracion){
        thrd_hiloLoop = new Class_ThrdHiloLoop(this, srfv_aceleracion);
        thrd_hiloLoop.setRunning(true);
        thrd_hiloLoop.start();
    }

    public void Draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvasWidth = canvas.getWidth();    canvasHeight = canvas.getHeight();
        switch (estado){
            case "0":
                switch (srfv_evento){
                    case "20"://Estatico
                    case "21"://
                    case "22":
                    case "23"://Lado a lado
                    case "24"://letra por letra
                        estado = srfv_evento;
                        animacion = srfv_evento;
                        act_tduracion(animacion);
                        srfv_evento = "-1";
                        sw1 = 0;
                        break;
                    case "30":// + velocidad
                    case "32":// - velocidad
                        estado = srfv_evento;
                        act_tduracion(srfv_evento);
                        srfv_evento = "-1";
                        sw1 = 0;
                        break;
                    case "40":
                        estado = srfv_evento;
                        srfv_evento = "-1";
                        sw1 = 0;
                        break;

                    default:
                        sw1 = 0;
                        srfv_evento = "-1";
                        act_Srfv2(canvas);
                        swThread = 0;
                        //aux_sentido = 0;
                        posC = 0;
                        infc_callbacksPanels_srfv.infcCallBacksMenu2_Reset();//Habilita las opciones del Menu despues de ejecutar la Animacion
                        infc_callbacksPanels_srfv.infcCallBacksMenu4_Reset();//Habilita las opciones del Menu despues de ejecutar la Animacion Total
                        //thrd_hiloLoop.setRunning(false);

                        Log.v("HILO::","Estado FINALIZADO");

                        break;
                }
                break;
            case "20":
                switch (srfv_evento){
                    case "20":
                        estado = "0"; srfv_evento = "-1";
                        sw1 = 0;
                        thrd_hiloLoop.setRunning(false);
                        break;
                    case "22":
                    case "21":
                    case "23":
                        estado = "0";
                        //animacion = srfv_evento;
                        thrd_hiloLoop.setRunning(false);
                        //srfv_evento = "-1";
                        sw1 = 0;
                        break;
                    default:
                        srfv_evento = "-1";
                        estado = animacion_estatica(canvas,0,t_duracion,0,0);
                        if (estado == "0") thrd_hiloLoop.setRunning(false);
                        break;
                }
                break;
            case "21":
                switch (srfv_evento){
                    case "21":
                        estado = "0"; srfv_evento = "-1";
                        sw1 = 0;
                        thrd_hiloLoop.setRunning(false);
                        //Log.v("EVENTO","==== PASA POR 21");
                        break;
                    case "20":
                    case "22":
                    case "23":
                        estado = "0";
                        //animacion = srfv_evento;
                        thrd_hiloLoop.setRunning(false);
                        //srfv_evento = "-1";
                        sw1 = 0;
                        break;
                    default:
                        srfv_evento = "-1";
                        estado = animacion_Izq_a_Der(canvas,0,t_duracion,0,1);
                        if (estado == "0") thrd_hiloLoop.setRunning(false);
                        break;
                }
                break;

            case "22":
                switch (srfv_evento){
                    case "22":
                        estado = "0"; srfv_evento = "-1";
                        sw1 = 0;
                        thrd_hiloLoop.setRunning(false);
                        break;
                    case "20":
                    case "21":
                    case "23":
                        estado = "0";
                        //animacion = srfv_evento;
                        thrd_hiloLoop.setRunning(false);
                        //srfv_evento = "-1";
                        sw1 = 0;
                        break;
                    default:
                        srfv_evento = "-1";
                        estado = animacion_Der_a_Izq(canvas,0,t_duracion,0,1);
                        if (estado == "0") thrd_hiloLoop.setRunning(false);
                        break;
                }
                break;
            case "23"://Lado a Lado
                switch (srfv_evento){
                    case "23":
                        estado = "0"; srfv_evento = "-1";
                        sw1 = 0;
                        thrd_hiloLoop.setRunning(false);
                        break;
                    case "20":
                    case "21":
                    case "22":
                        estado = "0";
                        //animacion = srfv_evento;
                        thrd_hiloLoop.setRunning(false);
                        //srfv_evento = "-1";
                        sw1 = 0;
                        break;
                    default:
                        srfv_evento = "-1";
                        estado = animacion_Lado_Lado(canvas, 0, t_duracion, 0, 1);
                        if (estado == "0") thrd_hiloLoop.setRunning(false);
                        break;
                }
                break;

            case "24":
                switch (srfv_evento){
                    case "24":
                        estado = "0"; srfv_evento = "-1";
                        sw1 = 0;
                        posC = 0;
                        thrd_hiloLoop.setRunning(false);
                        break;
                    case "20":
                    case "22":
                    case "21":
                    case "23":
                        estado = "0";
                        //animacion = srfv_evento;
                        thrd_hiloLoop.setRunning(false);
                        srfv_evento = "-1";
                        sw1 = 0;
                        break;
                    default:
                        srfv_evento = "-1";
                        estado = animacion_Letra_Izq_Der(canvas,0,t_duracion,0,1);
                        if (estado == "0") thrd_hiloLoop.setRunning(false);
                        break;
                }
                break;

            case "40":
                switch (srfv_evento){
                    case "41":
                        estado = "0"; sw1 = 0; srfv_evento = "-1";
                        thrd_hiloLoop.setRunning(false);
                        break;
                    default:
                        srfv_evento = "-1";
                        switch (animacion){
                            case "20":
                                estado = animacion_estatica(canvas,0,t_duracion,0,1);
                                break;
                            case "21":
                                estado = animacion_Izq_a_Der(canvas, 0, t_duracion, 0, 1);

                                break;
                            case "22":
                                estado = animacion_Der_a_Izq(canvas, 0, t_duracion, 0, 1);
                                break;
                            case "23":
                                estado = animacion_Lado_Lado(canvas, 0, t_duracion, 0, 1);
                                break;
                            case "24":
                                estado = animacion_Letra_Izq_Der(canvas,0,t_duracion,0,1);
                                break;

                        }
                        if (estado == "0") thrd_hiloLoop.setRunning(false);
                        break;
                }
                break;
            case "30":
            case "32":
                switch (srfv_evento){
                    case "30":
                    case "32":
                    case "31"://para detener opcional
                        estado = "0"; sw1 = 0; srfv_evento = "-1";
                        thrd_hiloLoop.setRunning(false);
                        break;
                    default:
                        srfv_evento = "-1";
                        switch (animacion){
                            case "21":
                                estado = animacion_Izq_a_Der(canvas,0,t_duracion,0,1);
                                break;
                            case "22":
                                estado = animacion_Der_a_Izq(canvas,0,t_duracion,0,1);
                                break;
                            case "23":
                                estado = animacion_Lado_Lado(canvas,t_espera,t_duracion,t_final,1);
                                break;
                            case "24":
                                estado = animacion_Letra_Izq_Der(canvas,0,t_duracion,0,1);
                                break;
                        }
                        if (estado == "0") thrd_hiloLoop.setRunning(false);
                        break;
                }
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        infc_callbacksPanels_srfv.onClickPanels_srfv(id);

        //infc_callbacksPanels_srfv.infc_CallBacksSrfvTiempos_Actualizar(id);
        return false;
    }

    public int nuevo_sizeMensaje(int srfv_textsize){
        float nuevo;
        nuevo = (canvasHeight*srfv_textsize/maxSize)*TX_SZ;// devuelve el nuevo size del texto para el paint. que es una fraccion de la altura del srfv
        //Log.v("TAMAÑO>>", "::" + srfv_textsize + ":" + nuevo);
        return (int) nuevo;
    }

    public String animacion_estatica(Canvas canvas, int tmp_espera, int tmp_duracion,int tmp_final, int ciclico){
        if (sw1 == 0){
            x = (canvas.getWidth()/2) - (paint.measureText(mensaje,0,mensaje.length())/2);
            y = canvas.getHeight()/2 + medio;
            sw1 = 1;
            t_espera_cont = 0;  //incializamos el contador
        }


        if (t_espera_cont >= tmp_espera & t_espera_cont <tmp_duracion+tmp_espera){
            canvas.drawText(mensaje,x,y,paint);
            //Log.v("CONTADOR", ":::" + contador);
            t_espera_cont = t_espera_cont + srfv_aceleracion;
            return estado;
        }
        else{
            if (ciclico == 0) {
                if (t_espera_cont >= tmp_final) {
                    sw1 = 0;
                    t_final = 0; //actualizamos el tiempo final

                    //Log.v("SRFV_evento>>>","::´´´´"+srfv_evento);
                    //thrd_hiloLoop.setRunning(false);
                    return "0";
                } else {
                    t_espera_cont = t_espera_cont + srfv_aceleracion;
                    return estado;
                }
            }
            if (ciclico == 1){
                sw1 = 0;
                canvas.drawText(mensaje,x,y,paint);
                return estado;
            }
            return "0";
        }

    }

    public String animacion_Izq_a_Der(Canvas canvas, int tmp_espera, int tmp_duracion,int tmp_final, int ciclico){
        if (sw1 == 0){
            x = canvas.getWidth();
            sw1 = 1;
            t_espera_cont = 0;  //incializamos el contador
        }


        if (t_espera_cont >= tmp_espera & t_espera_cont <tmp_duracion+tmp_espera){
            x = x - DX0;
            y = canvas.getHeight()/2 + medio;
            canvas.drawText(mensaje,x,y,paint);
            t_espera_cont=t_espera_cont + srfv_aceleracion;
            return estado;
        }
        else{
            if (ciclico == 0) {
                if (t_espera_cont >= tmp_final) {
                    sw1 = 0;
                    t_final = 0;

                    //Log.v("SRFV_evento>>>","::´´´´"+srfv_evento);
                    //thrd_hiloLoop.setRunning(false);
                    return "0";
                } else {
                    t_espera_cont = t_espera_cont + srfv_aceleracion;
                    return estado;
                }
            }
            if (ciclico == 1){
                sw1 = 0;
                return estado;
            }
            return "0";
        }
    }

    public String animacion_Der_a_Izq(Canvas canvas, int tmp_espera, int tmp_duracion, int tmp_final, int ciclico){
        if(sw1 == 0){
            x = -paint.measureText(mensaje,0,mensaje.length());
            sw1=1;
            t_espera_cont = 0;
        }

        if(t_espera_cont >= tmp_espera & t_espera_cont <tmp_duracion + tmp_espera){
            x = x + DX0;
            y = canvas.getHeight()/2 + medio;
            canvas.drawText(mensaje,x,y,paint);
            t_espera_cont = t_espera_cont + srfv_aceleracion;
            return estado;
        }
        else{
            if (ciclico == 0) {
                if (t_espera_cont >= tmp_final) {
                    sw1 = 0;
                    t_final = 0;
                    return "0";
                } else {
                    t_espera_cont = t_espera_cont + srfv_aceleracion;
                    return estado;
                }
            }
            if(ciclico == 1){
                sw1 = 0;
                return estado;
            }
            return "0";
        }

    }

    public String animacion_Lado_Lado(Canvas canvas, int tmp_espera, int tmp_duracion, int tmp_final, int ciclico){

        if (Math.round((paint.measureText(mensaje,0,mensaje.length()))/DX0) <= x_led) {
            //Log.v("SRFV","---> A LADO LADO 1 ::"+aux_sentido);
            if (sw1 == 0) {
                x = canvas.getWidth() - paint.measureText(mensaje, 0, mensaje.length());
                y = canvas.getHeight() / 2 + medio;
                aux_sentido = -1;
                sw1 = 1;
                t_espera_cont = 0;
            }
            //Log.v("SRFV","---> A LADO LADO 1 ::"+aux_sentido);
            if (t_espera_cont >= tmp_espera & t_espera_cont < tmp_duracion + tmp_espera) {
                if (x <= 0) {
                    aux_sentido = 1;
                    //x = -DX0;
                }
                if (x >= canvasWidth - paint.measureText(mensaje, 0, mensaje.length())) {
                    aux_sentido = -1;
                    x = canvas.getWidth() - paint.measureText(mensaje, 0, mensaje.length());
                    //Log.v("SRFV","---> A LADO LADO 1 ::"+aux_sentido);
                }
                x = x + DX0 * aux_sentido;
                y = canvas.getHeight() / 2 + medio;
                canvas.drawText(mensaje, x, y, paint);
                t_espera_cont = t_espera_cont + srfv_aceleracion;
                return estado;
            } else {
                if (ciclico == 0) {
                    if (t_espera_cont >= tmp_final) {
                        sw1 = 0;
                        t_final = 0;
                        aux_sentido = 0;
                        return "0";
                    } else {
                        t_espera_cont = t_espera_cont + srfv_aceleracion;
                        return estado;
                    }
                }
                if (ciclico == 1) {
                    if (x >= canvasWidth - paint.measureText(mensaje, 0, mensaje.length())) {
                        aux_sentido = -1;
                        x = canvas.getWidth() - paint.measureText(mensaje, 0, mensaje.length());
                        //Log.v("SRFV","---> A LADO LADO CC ::"+aux_sentido);
                    }
                    x = x + DX0 * aux_sentido;
                    y = canvas.getHeight() / 2 + medio;
                    canvas.drawText(mensaje, x, y, paint);
                    t_espera_cont = 0;
                    t_espera_cont = t_espera_cont + srfv_aceleracion;
                    return estado;
                }
                return "0";
            }

        }
        else{
            //Log.v("SRFV","---> A LADO LADO 2");
            if (aux_sentido2 == 0){
                auxEst = animacion_Izq_a_Der(canvas,0,t_duracion,0,0);
                if (auxEst == "0"){
                    aux_sentido2 = 1;
                    sw1 = 0;
                }
                return estado;
            }
            else{
                auxEst = animacion_Der_a_Izq(canvas,0,t_duracion,0,0);

                if (auxEst == "0"){
                    aux_sentido2 = 0;
                    sw1 = 0;
                    if (ciclico == 0) return "0";
                    else return estado;
                }
                else return estado;
            }
        }

    }

    public String animacion_Letra_Izq_Der(Canvas canvas, int tmp_espera, int tmp_duracion,int tmp_final, int ciclico){

        if (sw1 == 0){
            x = canvas.getWidth();
            sw1 = 1;
            t_espera_cont = 0;  //incializamos el contador

            texto = cortarTexto();
        }


        if (t_espera_cont >= tmp_espera & t_espera_cont <tmp_duracion+tmp_espera+1000){
            if (t_espera_cont < tmp_duracion + tmp_espera)
                x = x - DX0;
            y = canvas.getHeight()/2 + medio;
            canvas.drawText(texto,x,y,paint);
            t_espera_cont=t_espera_cont + srfv_aceleracion;
            return estado;
        }
        else{
            if (ciclico == 0) {
                if (t_espera_cont >= tmp_final) {
                    sw1 = 0;
                    t_final = 0;

                    //Log.v("SRFV_evento>>>","::´´´´"+srfv_evento);
                    //thrd_hiloLoop.setRunning(false);
                    return "0";
                } else {
                    t_espera_cont = t_espera_cont + srfv_aceleracion;
                    return estado;
                }
            }
            if (ciclico == 1){

                sw1 = 0;
                return estado;
            }
            return "0";
        }
    }

    public String cortarTexto(){
        if (posC >= mensaje.length()-1)  posC = 0;
        texto = "";
        if(mensaje.charAt(posC) != ' ') texto = texto + mensaje.charAt(posC);
        posC++;

        while ( posC < mensaje.length()){
            if((mensaje.charAt(posC -1) != ' ')&(mensaje.charAt(posC) == ' ')){ break;     }
            else{

                if (mensaje.charAt(posC) != ' ') texto = texto + mensaje.charAt(posC);
                posC++;
            }
        }

        if (texto == "")  return cortarTexto();
        return texto;
    }


    public void escalaX(Paint paint,String mensaje,int srfv_textsize){

        int lengthTxt = mensaje.length();
        int longTotal = 0;
        int contLine = 0;
        //int srfv_textsize = 1;
        String raw_Ltr;
        String strg_Letra[]=null;
        ArrayList<?> arls_TamañoLetra;

        if(srfv_textsize == 1){
            longTotal = 5*mensaje.length();
        }
        else {
            InputStream is = this.getResources().openRawResource(R.raw.file_letras);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            if (is != null) {
                try {
                    while ((raw_Ltr = br.readLine()) != null) {
                        contLine++;
                        if (contLine == srfv_textsize) {//seleccionamos el abecedario según el tamaño de la letra
                            raw_Ltr = raw_Ltr.replace(" ", "");
                            strg_Letra = raw_Ltr.split(",");
                        }
                    }

               /* raw_Ltr = br.readLine();
                if (raw_Ltr != null){
                    raw_Ltr = raw_Ltr.replace(" ","");//reemplaza el espacio vacio con uno sin espacio del String raw_Ltr
                    strg_Letra = raw_Ltr.split(",");//separa cada elemento utilizando "," como separador y convierte el string en un Array de String
                }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < lengthTxt; i++) {
                assert strg_Letra != null;
                if ((int)mensaje.charAt(i)<=127 & (int)mensaje.charAt(i)>=32)
                    longTotal = longTotal + Integer.decode(strg_Letra[6 + (int) mensaje.charAt(i) - 32]);//decodifica el valor en hexadecimal guardado como string en su valor entero
            }
        }

        longTotal = longTotal + mensaje.length();//sumamos la cantidad de espacios por caracter de 1px
        paint.setTextScaleX(1);
        //Log.v("ANTES:::", ">>>> " + DX0 * longTotal + "::" + paint.measureText(mensaje, 0, mensaje.length()));
        //Log.v("LONG TOTAL", ":: " + DX0 + ":" + longTotal + "="+DX0*longTotal);
        RR = (DX0*longTotal)/paint.measureText(mensaje, 0, mensaje.length());
        paint.setTextScaleX(RR);//esta funcion solo escala de la fuente original y no de la modificada anteriormente
        //Log.v("DESPUES:::", ">>>> " + "::" + RR + "::" + DX0 * longTotal + "::" + paint.measureText(mensaje, 0, mensaje.length()));
        //return RR;
    }

    public void actualizar_Srfv(){
        Canvas canvas;
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        if (token == 1)
            canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, paint);
        x=0;
        y = canvas.getHeight()/2;
        canvas.drawText(mensaje, 0, mensaje.length(), x, y + medio, paint);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }
    public void act_Srfv2(Canvas canvas){
        //Canvas canvas;
        //canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        if (token == 1)
            canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, paint);
        x=0;
        y = canvas.getHeight()/2;
        canvas.drawText(mensaje, 0, mensaje.length(), x, y + medio, paint);
        //surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void act_tduracion(String animacion){
        switch (animacion){
            case "20"://Estatico
                m_totalpixeles = 6;
                t_duracion = m_totalpixeles * srfv_aceleracion;
                break;
            case "21"://
            case "22":
                m_totalpixeles = Math.round(((canvasWidth + paint.measureText(mensaje, 0, mensaje.length())) / DX0));
                t_duracion = m_totalpixeles*srfv_aceleracion;
                break;
            case "23"://Lado a lado
                if (Math.round((paint.measureText(mensaje,0,mensaje.length()))/DX0) <= x_led) {
                    m_totalpixeles = Math.round((canvasWidth - paint.measureText(mensaje, 0, mensaje.length())) / DX0)*2;
                }
                else{
                    m_totalpixeles = Math.round(((canvasWidth + paint.measureText(mensaje, 0, mensaje.length())) / DX0));
                    aux_sentido2 = 0;
                }
                t_duracion = m_totalpixeles*srfv_aceleracion;
                break;
            case "24"://Letra por letra, izq a der
                m_totalpixeles = Math.round(canvasWidth  / DX0);
                t_duracion = m_totalpixeles*srfv_aceleracion;
                posC = 0;
                break;
            case "30":// + velocidad
            case "32":// - velocidad
                //t_duracion = Math.round(((canvasWidth + paint.measureText(mensaje, 0, mensaje.length())) / DX0) * srfv_aceleracion);
                t_duracion = m_totalpixeles*srfv_aceleracion;
                posC = 0;
                break;
            case "40":
                break;
            default:
                break;
        }
    }

}
