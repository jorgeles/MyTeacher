package com.example.jmrosell.myteacher;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmrosell.myteacher.Games.Game_Asociacion.AsociacionDetailFragment;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;


/**
 * An activity representing a list of Games. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link GameDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link GameListFragment} and the item details
 * (if present) is a {@link GameDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link GameListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class GameListActivity extends ActionBarActivity
        implements GameListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    /**
     * NavDrawer
     */
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private Toolbar mToolbar;

    /**
     * ViewGroup con los elementos del NavDrawer
     */
    private ViewGroup mDrawerItemListContainer;

    /**
     * View que corresponden con cada item del NavDrawer
     */
    private View[] mNavDrawerItemViews = null;

    /**
     * Listado en orden de los item que se han añadido al NavDrawer
     */
    private ArrayList<NAVDRAWER_ITEM> mNavDrawerItems = new ArrayList<NAVDRAWER_ITEM>();

    /**
     * Item del NavDrawer activo (por defecto entramos en la pantalla TIMELINE)
     */
    private NAVDRAWER_ITEM navDrawerItemActivo = NAVDRAWER_ITEM.Seccion1;

    /**
     * Enumerado con los items del Navdrawer
     */
    private static enum NAVDRAWER_ITEM {
        Seccion1, Seccion2
    };

    /**
     * Indica que fragmen esta activo
     */
    private NAVDRAWER_ITEM fragmentActivo;

    /**
     * Títulos de los posibles items del NavDrawer,
     * los índices deben corresponder con los de la lista superior
     */
    private static final int[] NAVDRAWER_TITLE_RES_ID = new int[]{
            R.string.title_section1,
            R.string.title_section2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tracker t = ((Analytics) this.getApplication()).getTracker(
                Analytics.TrackerName.APP_TRACKER);

        // Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("GameListActivity");

        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());

        setContentView(R.layout.activity_game_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if(mToolbar!=null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.game_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((GameListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.game_list))
                    .setActivateOnItemClick(true);
        }
        // TODO: If exposing deep links into your app, handle intents here.

        crearNavDrawer();
    }

    /**
     * Callback method from {@link GameListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if(id.equals("0")) {
                this.asociacionDetailFrag(id);

            }
            else {
                Bundle arguments = new Bundle();
                arguments.putString(GameDetailFragment.ARG_ITEM_ID, id);
                GameDetailFragment fragment = new GameDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.game_detail_container, fragment)
                        .commit();
            }

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, GameDetailActivity.class);
            detailIntent.putExtra(GameDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    /**
     * Crea el NavDrawer apropiadamente
     */
    private void crearNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null)
            return;

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navdrawer_item_ajustes, R.string.navdrawer_item_discover){
            //Hacer algo con lo eventos del navigation: Como navigation Open()
        };
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setTitle(NAVDRAWER_TITLE_RES_ID[navDrawerItemActivo.ordinal()]);
        getSupportActionBar().setTitle(R.string.app_name);

        rellenarNavDrawer();
    }

    /**
     * Rellena el NavDrawer con la lista de items correspondientes
     */
    private void rellenarNavDrawer() {
        mNavDrawerItems.clear();

        mNavDrawerItems.add(NAVDRAWER_ITEM.Seccion1);
        mNavDrawerItems.add(NAVDRAWER_ITEM.Seccion2);

        crearItemsNavDrawer();
    }

    /**
     * Crea los items del NavDrawer
     */
    private void crearItemsNavDrawer() {
        mDrawerItemListContainer = (ViewGroup) findViewById(R.id.navdrawer);
        if (mDrawerItemListContainer == null)
            return;

        mNavDrawerItemViews = new View[mNavDrawerItems.size()];
        mDrawerItemListContainer.removeAllViews();

        int i = 0;
        for (NAVDRAWER_ITEM navItem : mNavDrawerItems) {
            mNavDrawerItemViews[i] = crearNavDrawerItem(navItem, mDrawerItemListContainer);
            mDrawerItemListContainer.addView(mNavDrawerItemViews[i]);
            i++;
        }

        //formatearColorItemsNavDrawer(mDrawerItemListContainer);
    }

    /**
     * Crea un item del NavDrawer
     *
     * @param navItem   Item a crear
     * @param container Contenedor de los NavDrawer
     * @return View del item creado
     */
    private View crearNavDrawerItem(final NAVDRAWER_ITEM navItem, final ViewGroup container) {

        View view = getLayoutInflater().inflate(R.layout.navdrawer_item, container, false);

        TextView tituloView = (TextView) view.findViewById(R.id.titulo);

        tituloView.setText(getString(NAVDRAWER_TITLE_RES_ID[navItem.ordinal()]));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navDrawerItemActivo = navItem;
                getSupportActionBar().setTitle(NAVDRAWER_TITLE_RES_ID[navItem.ordinal()]);
                //formatearColorItemsNavDrawer(container);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                switch (navItem) {
                    case Seccion1:
                        //sustituirFragment(NAVDRAWER_ITEM.TIMELINE);
                        Log.v("Navdrawer", "Seleccionado Seccion1");
                        Toast.makeText(getApplicationContext(),"Ha pulsado Seccion 1",Toast.LENGTH_SHORT).show();
                        break;
                    case Seccion2:
                        //sustituirFragment(NAVDRAWER_ITEM.DISCOVER);
                        Log.v("Navdrawer", "Seleccionado Seccion2");
                        Toast.makeText(getApplicationContext(),"Ha pulsado Seccion 2",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;
    }

    /**
     * Formatea el color de todos los items del NavDrawer
     * dependiendo de si están seleccionados o no
     */
    private void formatearColorItemsNavDrawer(ViewGroup container) {
        int i = 0;
        for (NAVDRAWER_ITEM navItem : mNavDrawerItems) {
            View view = container.getChildAt(i);
            TextView tituloView = (TextView) view.findViewById(R.id.titulo);

            tituloView.setTextColor(navItem == navDrawerItemActivo ?
                    getResources().getColor(R.color.navdrawer_item_seleccionado) :
                    getResources().getColor(R.color.navdrawer_item_no_seleccionado));
        }

        i++;
    }

    /**
     * Sustituye el fragment actual por uno nuevo mediante una animación
     *
     * @param fragmentTarget Fragment al que navegar
     */
    private void sustituirFragment(NAVDRAWER_ITEM fragmentTarget) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.setCustomAnimations(R.anim.animator_fade_in, R.anim.animator_fade_out);

        switch (fragmentTarget) {
            case Seccion1:
                //ft.replace(R.id.contenedor, TimelineFragment.newInstance());
                break;

            case Seccion2:
                //ft.replace(R.id.contenedor, DiscoverFragment.newInstance());
                break;
        }

        fragmentActivo = fragmentTarget;

        ft.commit();
    }

    public void asociacionDetailFrag(String id){
        Bundle arguments = new Bundle();
        arguments.putString(AsociacionDetailFragment.ARG_ITEM_ID, id);
        AsociacionDetailFragment fragment = new AsociacionDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_detail_container, fragment)
                .commit();
    }

    public void elementDetailFrag(String id){

    }
}
