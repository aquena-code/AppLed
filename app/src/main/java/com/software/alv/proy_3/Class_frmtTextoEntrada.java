package com.software.alv.proy_3;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ALVARO on 28/05/2019.
 */
public class Class_frmtTextoEntrada extends DialogFragment {

    public interface I_Class_frmtTextoEntrada{
        public void onClick_AceptarEntrada(String mensaje);

    }

    public EditText edtx_mensaje;
    private Button btn_Aceptar;
    private Button btn_Cancelar;
    private String mensaje = "";
    private I_Class_frmtTextoEntrada i_TextoEntrada;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        i_TextoEntrada = (I_Class_frmtTextoEntrada) activity;


    }

    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vw_mensaje = inflater.inflate(R.layout.layt_texto_entrada,container,false);
        edtx_mensaje = (EditText) vw_mensaje.findViewById(R.id.edtx_entrada_mensaje);
        btn_Aceptar = (Button) vw_mensaje.findViewById(R.id.btn_aceptar);
        btn_Cancelar = (Button) vw_mensaje.findViewById(R.id.btn_cancelar);
        edtx_mensaje.setText(mensaje);
        btn_Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtx_mensaje.getText().toString().length()<=150) {
                    if (edtx_mensaje.getText().toString().split(" ").length <= 0 || edtx_mensaje.getText().toString().isEmpty()) {
                        edtx_mensaje.setText("");
                        Toast.makeText(getActivity(), "Ingrese al menos un caracter...", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        i_TextoEntrada.onClick_AceptarEntrada(edtx_mensaje.getText().toString());
                        dismiss();
                    }
                }
                else
                    Toast.makeText(getActivity(),"Ingrese un Texto menor a 150 caracteres...",Toast.LENGTH_SHORT).show();

            }
        });

        btn_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return vw_mensaje;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mensaje = getArguments().getString("Mensaje");

    }


}
