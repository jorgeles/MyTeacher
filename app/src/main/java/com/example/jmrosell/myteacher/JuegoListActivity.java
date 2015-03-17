package com.example.jmrosell.myteacher;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * An activity representing a list of Juegos. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link JuegoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link JuegoListFragment} and the item details
 * (if present) is a {@link JuegoDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link JuegoListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class JuegoListActivity extends FragmentActivity
        implements JuegoListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_list);

        /*
        Comprueba si estamos en una tablet en horizontal o no. Si lo estamos entra y pone
        la variable mTwoPane a true
         */
        if (findViewById(R.id.juego_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((JuegoListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.juego_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link JuegoListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        /*Esta función esta override en NavigationActivity*/
    }
}
