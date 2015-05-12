package com.example.jmrosell.myteacher.Games.Game_Cajas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jmrosell.myteacher.Juego_cajas;
import com.example.jmrosell.myteacher.R;

public class JuegoCajasFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View myFragmentView = inflater.inflate(R.layout.fragment_cajas, container, false);

        final EditText tiradas = (EditText) myFragmentView.findViewById(R.id.view_Intentos);
        final EditText vidas = (EditText) myFragmentView.findViewById(R.id.view_vidas);
        final Spinner tiempo = (Spinner) myFragmentView.findViewById(R.id.spinner_dificultad);

        Button cajas = (Button) myFragmentView.findViewById(R.id.btn_cajas);
        cajas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent cajas_item = new Intent(getActivity(), Juego_cajas.class);
                cajas_item.putExtra("Tiempo",tiempo.getSelectedItemPosition());
                if(vidas.getText().toString().isEmpty())
                    cajas_item.putExtra("Vidas",3);
                else
                    cajas_item.putExtra("Vidas",Integer.parseInt(vidas.getText().toString()));

                if(tiradas.getText().toString().isEmpty())
                    cajas_item.putExtra("Tiradas",5);
                else
                    cajas_item.putExtra("Tiradas",Integer.parseInt(tiradas.getText().toString()));
                startActivity(cajas_item);
            }
        });
        return myFragmentView;
    }
}
