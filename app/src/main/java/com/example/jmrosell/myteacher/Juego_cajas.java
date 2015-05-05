package com.example.jmrosell.myteacher;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmrosell.myteacher.Games.Game_Cajas.CajasContent;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.Random;


public class Juego_cajas extends ActionBarActivity {

    private Toolbar mToolbar;

    private RelativeLayout layout;
    private TextView TxtViewVidas;
    private TextView TxtViewTiradas;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private Drawable shape;
    private Drawable shape_text2;
    private Drawable shape_text3;

    private int textColor;
    private AnimatorSet anim;
    private boolean click = false;
    private int vidas;
    private int tiradas;
    private int segundos;
    private int palabra_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tracker t = ((Analytics) this.getApplication()).getTracker(
                Analytics.TrackerName.APP_TRACKER);

        Bundle bundle = getIntent().getExtras();
        vidas = bundle.getInt("Vidas");
        tiradas = bundle.getInt("Tiradas");
        if(bundle.getInt("Tiempo")==0){ //Facil
            segundos = 10;
        }else if(bundle.getInt("Tiempo")==1){ //Medio
            segundos = 7;
        }else{ //Dificil
            segundos = 4;
        }

        // Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("Juego Cajas");

        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());

        setContentView(R.layout.activity_juego_cajas);

        //Cargamos las palabras
        CajasContent.loadCajas();
        final ArrayList<ArrayList<String>> palabras = CajasContent.getCajasList();

        final Random r = new Random();
        palabra_id = r.nextInt(palabras.size());

        mToolbar = (Toolbar) findViewById(R.id.toolbar_cajas);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        layout = (RelativeLayout) findViewById(R.id.contenedor_cajas);
        TxtViewVidas = (TextView) findViewById(R.id.vidas);
        TxtViewTiradas = (TextView) findViewById(R.id.tiradas);
        text1 = (TextView) findViewById(R.id.text1);
        text1.setBackgroundResource(R.drawable.border_textview);
        text1.setText(palabras.get(palabra_id).get(0));
        shape = (Drawable) text1.getBackground();

        text2 = (TextView) findViewById(R.id.text2);
        text2.setBackgroundResource(R.drawable.border_textview);
        text2.setText(palabras.get(palabra_id).get(1));
        shape_text2 = (Drawable) text2.getBackground();

        text3 = (TextView) findViewById(R.id.text3);
        text3.setBackgroundResource(R.drawable.border_textview);
        text3.setText(palabras.get(palabra_id).get(2));
        shape_text3 = (Drawable) text3.getBackground();

        textColor = text3.getCurrentTextColor();
        TxtViewVidas.setText(""+vidas);
        TxtViewTiradas.setText(""+tiradas);

        // Dimensiones de la pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final float height = metrics.heightPixels;

        anim = new AnimatorSet();
        anim.play(ObjectAnimator.ofFloat(layout, "translationY", 0, height - 180));
        anim.setDuration(segundos*1000);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation){
                super.onAnimationStart(animation);
                shape.setColorFilter(null);
                shape_text2.setColorFilter(null);
                shape_text3.setColorFilter(null);

                text1.setTextColor(textColor);
                text2.setTextColor(textColor);
                text3.setTextColor(textColor);
                click = false;

                //cambiamos la palabra
                int i1 = r.nextInt(palabras.size());
                while(i1==palabra_id){
                    i1 = r.nextInt(palabras.size());
                }
                palabra_id = i1;
                text1.setText(palabras.get(palabra_id).get(0));
                text2.setText(palabras.get(palabra_id).get(1));
                text3.setText(palabras.get(palabra_id).get(2));

                //anim.play(ObjectAnimator.ofFloat(layout, "translationY", 0, height - 180));
                //anim.setDuration(5000);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(!click) {
                    shape.setColorFilter(Color.parseColor("#FA5858"), PorterDuff.Mode.SRC);
                    shape_text2.setColorFilter(Color.parseColor("#FA5858"), PorterDuff.Mode.SRC);
                    shape_text3.setColorFilter(Color.parseColor("#FA5858"), PorterDuff.Mode.SRC);
                    text1.setTextColor(getResources().getColor(R.color.blanco));
                    text2.setTextColor(getResources().getColor(R.color.blanco));
                    text3.setTextColor(getResources().getColor(R.color.blanco));
                    vidas--;
                    tiradas--;
                }

                //Actualizamos las vidas por si hubiese perdido alguna y las tiradas
                TxtViewVidas.setText(""+vidas);
                TxtViewTiradas.setText(""+tiradas);
                if(vidas>0 && tiradas>0) {
                    // Execute some code after 2 seconds have passed
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            anim.start();
                        }
                    }, 2000);
                }else if(vidas==0){
                    TxtViewVidas.setText(""+vidas);
                    Toast.makeText(Juego_cajas.this,"You lost",Toast.LENGTH_SHORT).show();
                }else if(tiradas==0){
                    TxtViewTiradas.setText(""+tiradas);
                    Toast.makeText(Juego_cajas.this,"Bravo",Toast.LENGTH_SHORT).show();
                }
            }
        });
        anim.start();

        text1.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text1.getText().equals(palabras.get(palabra_id).get(3))) { //Acierto
                    // Para cambiar el color al fondo del drawable
                    shape.setColorFilter(Color.parseColor("#66BB6A"), android.graphics.PorterDuff.Mode.SRC);
                }else{ //Fallo
                    vidas--;
                    shape.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                }
                tiradas--;
                text1.setTextColor(getResources().getColor(R.color.blanco));
                click = true;
                anim.end();
            }
        });
        text2.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text2.getText().equals(palabras.get(palabra_id).get(3))) { //Acierto
                    // Para cambiar el color al fondo del drawable
                    shape_text2.setColorFilter(Color.parseColor("#66BB6A"), android.graphics.PorterDuff.Mode.SRC);
                }else{ //Fallo
                    vidas--;
                    shape_text2.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                }
                tiradas--;
                text2.setTextColor(getResources().getColor(R.color.blanco));
                click = true;
                anim.end();
            }
        });
        text3.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text3.getText().equals(palabras.get(palabra_id).get(3))) { //Acierto
                    // Para cambiar el color al fondo del drawable
                    shape_text3.setColorFilter(Color.parseColor("#66BB6A"), android.graphics.PorterDuff.Mode.SRC);
                }else{ //Fallo
                    vidas--;
                    shape_text3.setColorFilter(Color.parseColor("#FA5858"), android.graphics.PorterDuff.Mode.SRC);
                }
                tiradas--;
                text3.setTextColor(getResources().getColor(R.color.blanco));
                click = true;
                anim.end();
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
