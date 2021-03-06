package com.example.jmrosell.myteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jmrosell.myteacher.Games.Game;
import com.example.jmrosell.myteacher.Games.GameContent;
import com.example.jmrosell.myteacher.Games.Game_Cajas.JuegoCajasFragment;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * A fragment representing a single Game detail screen.
 * This fragment is either contained in a {@link GameListActivity}
 * in two-pane mode (on tablets) or a {@link GameDetailActivity}
 * on handsets.
 */
public class GameDetailFragment extends Fragment {
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
    public GameDetailFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        // Show the dummy content as text in a TextView.
        /*if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.game_detail)).setText(mItem.content);
        }*/

        if (juego != null) {
            switch (juego.getId()) {
                case 1:
                    Button asociacion = (Button) rootView.findViewById(R.id.button_detail);
                    asociacion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent asociacion_item = new Intent(getActivity(), Reproductor_Asociacion.class);

                            // Get tracker.
                            Tracker t = ((Analytics) getActivity().getApplication()).getTracker(
                                    Analytics.TrackerName.APP_TRACKER);
                            // Build and send an Event.
                            t.send(new HitBuilders.EventBuilder()
                                    .setCategory("Reproducir")
                                    .setAction("Asociacion")
                                    .setLabel(String.valueOf(view.getId()))
                                    .build());

                            startActivity(asociacion_item);
                        }
                    });
                    break;
                case 2:
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    //add a fragment
                    JuegoCajasFragment cajas = new JuegoCajasFragment();
                    fragmentTransaction.add(R.id.detail_game, cajas);
                    fragmentTransaction.commit();
                    break;
            }
            //((TextView) rootView.findViewById(R.id.textViewNombre)).setText(juego.getNombre());
            //((TextView) rootView.findViewById(R.id.textViewDificultad)).setText(juego.getDificultad());
        }

        return rootView;
    }
}
