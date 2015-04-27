package com.example.jmrosell.myteacher.Games.Game_Asociacion;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jmrosell.myteacher.GameDetailFragment;
import com.example.jmrosell.myteacher.GameListActivity;
import com.example.jmrosell.myteacher.R;

public class AsociacionDetailActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        System.out.println("Fuera");

        if (mToolbar != null) {
            System.out.println("DENTRO");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(AsociacionDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(AsociacionDetailFragment.ARG_ITEM_ID));
            AsociacionDetailFragment fragment = new AsociacionDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.game_detail_container, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asociacion_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, GameListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
