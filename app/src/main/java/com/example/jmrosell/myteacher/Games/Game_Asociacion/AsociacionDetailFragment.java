package com.example.jmrosell.myteacher.Games.Game_Asociacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jmrosell.myteacher.Analytics;
import com.example.jmrosell.myteacher.Games.Game;
import com.example.jmrosell.myteacher.Games.GameContent;
import com.example.jmrosell.myteacher.R;
import com.example.jmrosell.myteacher.Reproductor_Asociacion;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Enumeration;

/**
 * A fragment representing a single Game detail screen.
 * This fragment is either contained in a {@link com.example.jmrosell.myteacher.GameListActivity}
 * in two-pane mode (on tablets) or a {@link com.example.jmrosell.myteacher.GameDetailActivity}
 * on handsets.
 */
public class AsociacionDetailFragment extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    int index;

    /**
     * The dummy content this fragment is presenting.
     */
//    private DummyContent.DummyItem mItem;
    private Game juego;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AsociacionDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //   mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            index = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
            juego = (Game) GameContent.getGameList().get(index);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.asociacion_game_detail, container, false);

        // Show the dummy content as text in a TextView.
        /*if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.game_detail)).setText(mItem.content);
        }*/

        AsociacionContent contenido = new AsociacionContent();

        if (juego != null) {
            if (juego.getNombre().equals("Asociacion")) {
                LinearLayout lista_Elementos = (LinearLayout) rootView.findViewById(R.id.ListaElementos);
                LinearLayout lista_destinos = (LinearLayout) rootView.findViewById(R.id.ListaDestinos);

                Button asociacion = (Button) rootView.findViewById(R.id.button_detail);
                asociacion.setOnClickListener(this);
                ((EditText) rootView.findViewById(R.id.textViewNombre)).setText(contenido.game.getNombre());
                ((EditText) rootView.findViewById(R.id.textViewDificultad)).setText(contenido.game.getDificultad());
                ((EditText) rootView.findViewById(R.id.NumerodeElementos)).setText(String.valueOf(contenido.game.elementos.size()));
                ((EditText) rootView.findViewById(R.id.NumerodeDestinos)).setText(String.valueOf(contenido.game.destinos.size()));

                Enumeration<Integer> e = contenido.elementos.keys();
                Integer clave;
                while (e.hasMoreElements()) {
                    clave = e.nextElement();
                    Elemento_Asociacion elemento = contenido.elementos.get(clave);
                    Button boton = new Button(this.getActivity());
                    boton.setText(elemento.name);
                    boton.setId(elemento.id);
                    boton.setOnClickListener(this);
                    lista_Elementos.addView(boton);
                }


                e = contenido.destinos.keys();
                while (e.hasMoreElements()) {
                    clave = e.nextElement();
                    Destino_Asociacion destino = contenido.destinos.get(clave);
                    Button boton = new Button(this.getActivity());
                    boton.setText(destino.name);
                    boton.setId(destino.id);
                    boton.setOnClickListener(this);
                    lista_destinos.addView(boton);
                }

            }

        }

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_detail) {
            Intent asociacion_item = new Intent(getActivity(), Reproductor_Asociacion.class);
            startActivity(asociacion_item);
        }
        else{

            // Get tracker.
            Tracker t = ((Analytics) getActivity().getApplication()).getTracker(
                    Analytics.TrackerName.APP_TRACKER);
            // Build and send an Event.
            t.send(new HitBuilders.EventBuilder()
                    .setCategory("Detalles Asocacion")
                    .setAction("Fragment detalles elementos y destinos")
                    .setLabel(String.valueOf(v.getId()))
                    .build());

            Bundle arguments = new Bundle();
            arguments.putString(AsociacionDetailFragment.ARG_ITEM_ID, String.valueOf(index));
            ElementDetailFragment fragment = new ElementDetailFragment();
            fragment.setArguments(arguments);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.game_detail_container, fragment)
                    .commit();

        }
    }
}
