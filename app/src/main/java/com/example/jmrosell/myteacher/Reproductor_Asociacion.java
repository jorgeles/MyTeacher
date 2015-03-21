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
import android.widget.RelativeLayout;
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
    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_reproductor_asociacion);

        marco = (ViewGroup) findViewById(R.id.marco);
        imagen = new TextView(this);
        imagen.setGravity(Gravity.CENTER);
        MyDragListener prueba = new MyDragListener();
        imagen.setText("Probandooooo");
        posicion = new Posicion_Pantalla();
        posicion.x = 20;
        posicion.y = (150) + 10;
        posiciones_elementos.put(1, posicion);
        imagen.setOnTouchListener(this);
        imagen.setOnDragListener(prueba);
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


    //Al tocar la pantalla...
    public boolean onTouch(View view, MotionEvent event) {
        //Recogemos las coordenadas del dedo
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        //Dependiendo de la accion recogida..
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            //Al tocar la pantalla
            case MotionEvent.ACTION_DOWN:
                //Recogemos los parametros de la imagen que hemo tocado
                RelativeLayout.LayoutParams Params =
                        (RelativeLayout.LayoutParams) view.getLayoutParams();
                xDelta = X - Params.leftMargin;
                yDelta = Y - Params.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                //Al levantar el dedo simplemento mostramos un mensaje
                break;
            case MotionEvent.ACTION_MOVE:
                //Al mover el dedo vamos actualizando
                //los margenes de la imagen para
                //crear efecto de arrastrado
                RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - xDelta;
                layoutParams.topMargin = Y - yDelta;
                //Qutamos un poco de margen para
                //que la imagen no se deforme
                //al llegar al final de la pantalla y pueda ir más allá
                //probar también el codigo omitiendo estas dos líneas
                layoutParams.rightMargin = -50;
                layoutParams.bottomMargin = -50;
                //le añadimos los nuevos
                //parametros para mover la imagen
                view.setLayoutParams(layoutParams);
                break;
        }
        //Se podría decir que 'dibujamos'
        //la posición de la imagen en el marco.
        marco.invalidate();
        return true;
    }
}

