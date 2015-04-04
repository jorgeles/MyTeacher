package com.example.jmrosell.myteacher;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmrosell.myteacher.Games.Game_Cajas.CajasContent;

import java.util.ArrayList;


public class Juego_cajas extends ActionBarActivity {

    private Toolbar mToolbar;

    private TextView text1;
    private TextView text2;
    private TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_cajas);

        //Cargamos las palabras
        CajasContent.loadCajas();
        final ArrayList<ArrayList<String>> palabras = CajasContent.getCajasList();

        mToolbar = (Toolbar) findViewById(R.id.toolbar_cajas);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        text1 = (TextView) findViewById(R.id.text1);
        text1.setBackgroundResource(R.drawable.border_textview);
        text1.setText(palabras.get(0).get(0));
        final Drawable shape = (Drawable) text1.getBackground();

        text2 = (TextView) findViewById(R.id.text2);
        text2.setBackgroundResource(R.drawable.border_textview);
        text2.setText(palabras.get(0).get(1));
        final Drawable shape_text2 = (Drawable) text2.getBackground();

        text3 = (TextView) findViewById(R.id.text3);
        text3.setBackgroundResource(R.drawable.border_textview);
        text3.setText(palabras.get(0).get(2));
        final Drawable shape_text3 = (Drawable) text3.getBackground();

        // Dimensiones de la pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;

        // Movemos el text view
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(text1, "translationY", 0, height - 185),
                ObjectAnimator.ofFloat(text2, "translationY", 0, height - 185),
                ObjectAnimator.ofFloat(text3, "translationY", 0, height - 185)
        );
        set.setDuration(3000).start();

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                shape.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                shape_text2.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                shape_text3.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                text1.setTextColor(getResources().getColor(R.color.blanco));
                text2.setTextColor(getResources().getColor(R.color.blanco));
                text3.setTextColor(getResources().getColor(R.color.blanco));
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        text1.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.text1) && (!set.isPaused())) {
                    if(text1.getText().equals(palabras.get(0).get(3))) {
                        // Para cambiar el color al fondo del drawable
                        shape.setColorFilter(Color.parseColor("#66BB6A"), android.graphics.PorterDuff.Mode.SRC);
                    }else{
                        shape.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                    }
                    text1.setTextColor(getResources().getColor(R.color.blanco));
                    set.pause();
                }
            }
        });

        text2.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.text2) && (!set.isPaused())) {
                    if(text2.getText().equals(palabras.get(0).get(3))) {
                        // Para cambiar el color al fondo del drawable
                        shape_text2.setColorFilter(Color.parseColor("#66BB6A"), android.graphics.PorterDuff.Mode.SRC);
                    }else{
                        shape_text2.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                    }
                    text2.setTextColor(getResources().getColor(R.color.blanco));
                    set.pause();
                }
            }
        });

        text3.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.text3) && (!set.isPaused())) {
                    if(text3.getText().equals(palabras.get(0).get(3))) {
                        // Para cambiar el color al fondo del drawable
                        shape_text3.setColorFilter(Color.parseColor("#66BB6A"), android.graphics.PorterDuff.Mode.SRC);
                    }else{
                        shape_text3.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                    }
                    text3.setTextColor(getResources().getColor(R.color.blanco));
                    set.pause();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juego_cajas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
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
