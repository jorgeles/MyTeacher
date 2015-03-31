package com.example.jmrosell.myteacher.Games.Game_Asociacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jmrosell.myteacher.Games.Game;
import com.example.jmrosell.myteacher.Games.GameContent;
import com.example.jmrosell.myteacher.R;
import com.example.jmrosell.myteacher.Reproductor_Asociacion;

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
            int index = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
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
                Button asociacion = (Button) rootView.findViewById(R.id.button_detail);
                asociacion.setOnClickListener(this);
            }
            ((EditText) rootView.findViewById(R.id.textViewNombre)).setText(contenido.game.getNombre());
            ((EditText) rootView.findViewById(R.id.textViewDificultad)).setText(contenido.game.getDificultad());
            ((EditText) rootView.findViewById(R.id.NumerodeElementos)).setText(String.valueOf(contenido.game.elementos.size()));
            ((EditText) rootView.findViewById(R.id.NumerodeDestinos)).setText(String.valueOf(contenido.game.destinos.size()));
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_detail) {
            Intent asociacion_item = new Intent(getActivity(), Reproductor_Asociacion.class);
            startActivity(asociacion_item);
        }
    }
}
