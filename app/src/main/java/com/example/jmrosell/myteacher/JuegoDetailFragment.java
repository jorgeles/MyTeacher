package com.example.jmrosell.myteacher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.jmrosell.myteacher.dummy.DummyContent;
import com.example.jmrosell.myteacher.juego.Juego;
import com.example.jmrosell.myteacher.juego.JuegoContent;

/**
 * A fragment representing a single Juego detail screen.
 * This fragment is either contained in a {@link JuegoListActivity}
 * in two-pane mode (on tablets) or a {@link JuegoDetailActivity}
 * on handsets.
 */
public class JuegoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
   // private DummyContent.DummyItem mItem;
    private Juego juego;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public JuegoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
           // mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            //Obtener el indice del juego seleccionado
            int index = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
            juego = (Juego) JuegoContent.getJuegosList().get(index);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_juego_detail, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.juego_detail)).setText(mItem.content);
//        }

        if (juego != null){
            //Rellenar los elementos de la pantalla de detalle
            ((TextView) rootView.findViewById(R.id.textViewNombre)).setText(juego.getNombre());
            ((TextView) rootView.findViewById(R.id.textViewDificultad)).setText(juego.getDificultad());
        }

        return rootView;
    }
}
