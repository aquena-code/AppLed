package com.software.alv.proy_3;

import android.graphics.Canvas;

/**
 * Created by ALVARO on 02/02/2019.
 */
public class Class_ThrdHiloLoop extends Thread {
    private Class_SrfvMatrix srfv_matrix;
    //private final long FPS = 30;
    private boolean running = false;
    //private int srfv_aceleracion;
    long tempFotoGr;
    long iniTiempo;
    long sleepTiempo;

    public void setRunning(boolean bool){
        this.running = bool;
    }
    public boolean getRunning(){
        return this.running;
    }

    public Class_ThrdHiloLoop (Class_SrfvMatrix srfv_matrix, int srfv_aceleracion){
        this.srfv_matrix = srfv_matrix;
        this.tempFotoGr = srfv_aceleracion;
    }

    @Override
    public void run(){

        Canvas c = null;
        try {
            c = srfv_matrix.getHolder().lockCanvas();
            synchronized (srfv_matrix.getHolder()){
                srfv_matrix.Draw(c);
            }
        }finally {
            if (c!=null){
                srfv_matrix.getHolder().unlockCanvasAndPost(c);
            }
        }

        while(running){
            iniTiempo = System.currentTimeMillis();
            c = null;
            try {
                c = srfv_matrix.getHolder().lockCanvas();
                synchronized (srfv_matrix.getHolder()){
                    srfv_matrix.Draw(c);
                }

            }finally {
                if (c!=null){
                    srfv_matrix.getHolder().unlockCanvasAndPost(c);
                }
            }
            if (!running) break;//termina hilo por estado = 0
            sleepTiempo = tempFotoGr - (System.currentTimeMillis() - iniTiempo);
            try {

                if(sleepTiempo >0)   sleep(sleepTiempo);
                //else srfv_matrix.setMensaje("TIEMPO");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            c = srfv_matrix.getHolder().lockCanvas();
            synchronized (srfv_matrix.getHolder()){
                srfv_matrix.Draw(c);
            }
        }finally {
            if (c!=null){
                srfv_matrix.getHolder().unlockCanvasAndPost(c);
            }
        }
        //srfv_matrix.act_Srfv();
        //Log.v("FINAL THILO","....TERMINO.....");
    }
}
