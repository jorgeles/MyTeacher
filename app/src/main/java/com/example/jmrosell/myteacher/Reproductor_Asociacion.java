package com.example.jmrosell.myteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jmrosell.myteacher.juego.MyDragListener;
import com.example.jmrosell.myteacher.juego.Posicion_Pantalla;

import java.util.Hashtable;


public class Reproductor_Asociacion extends ActionBarActivity implements View.OnTouchListener {

    private Posicion_Pantalla posicion;
    //Definimos el marco por el cual podemos arrastrar la imagen
    private ViewGroup marco;
    //Definimos la imagen que vasmo arrastrar
    private TextView imagen;
    //Tabla has que nos indica donde esta cada elemento en la pantalla
    public static Hashtable<Integer, Posicion_Pantalla> posiciones_elementos = new Hashtable<Integer, Posicion_Pantalla>();
    public static Hashtable<Integer, Posicion_Pantalla> posiciones_destinos = new Hashtable<Integer, Posicion_Pantalla>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_reproductor_asociacion);

        marco = (ViewGroup) findViewById(R.id.marco);
        imagen = new TextView(this);
        imagen.setGravity(Gravity.CENTER);
        MyDragListener prueba = new MyDragListener();
        imagen.setOnDragListener(prueba);
        imagen.setText("Probandooooo");
        posicion = new Posicion_Pantalla();
        posicion.x = 20;
        posicion.y = (150) + 10;
        posiciones_elementos.put(1, posicion);
        imagen.setOnTouchListener(this);
        imagen.setX(20);
        imagen.setY((150) + 10);
        imagen.setWidth(120);
        imagen.setHeight(120);
        int d = 2;
        //noinspection ResourceType
        imagen.setId(d);
        marco.addView(imagen);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reproductor__asociacion, menu);
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
            NavUtils.navigateUpTo(this, new Intent(this, NavigationActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        System.out.println(motionEvent.getAction());
        return false;
    }
}
