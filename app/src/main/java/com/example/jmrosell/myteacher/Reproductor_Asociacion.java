package com.example.jmrosell.myteacher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jmrosell.myteacher.Games.Posicion_Pantalla;

import java.util.Hashtable;


public class Reproductor_Asociacion extends ActionBarActivity implements View.OnTouchListener {

    private Posicion_Pantalla posicion;
    //Definimos el marco por el cual podemos arrastrar la imagen
    private ViewGroup marco;
    //Definimos la imagen que vasmo arrastrar
    private TextView suma1;
    private TextView suma2;
    private TextView origen1;
    //Tabla has que nos indica donde esta cada elemento en la pantalla
    public static Hashtable<Integer, Posicion_Pantalla> posiciones_elementos = new Hashtable<Integer, Posicion_Pantalla>();
    public static Hashtable<Integer, Posicion_Pantalla> posiciones_destinos = new Hashtable<Integer, Posicion_Pantalla>();
    private int xDelta;
    private int yDelta;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_asociacion);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_asociacion);

        if(mToolbar!=null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        /*Añado un elemento. En el futuro esto se cargará de memoria
        y tendremos que ir leyendolos y poniendolos en pantalla. En memoria habrá que tener
        cada elemento asociado con un destino de manera que a la hora de hacer las comprobaciones
        si el elemento está sobre su destino lo podamos hacer de una manera comoda. En memoria se le asignará
        a cada elemento y a cada destino un identificador para ponerselo tambien a su elemento en pantalla.
        Destacar que este id solo estará en la memoria principal y no en la base de datos
         */
        marco = (ViewGroup) findViewById(R.id.marco);
        suma1 = new TextView(this);
        suma1.setGravity(Gravity.CENTER);
        suma1.setText("3+2");
        suma1.setTextColor(Color.RED);
        suma1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangulo));

        posicion = new Posicion_Pantalla();
        posicion.x = 20;
        posicion.y = (150) + 10;
        posiciones_elementos.put(1, posicion);
        suma1.setOnTouchListener(this);
        suma1.setX(20);
        suma1.setY((150) + 10);
        suma1.setWidth(120);
        suma1.setHeight(120);
        int d = 1;
        //noinspection ResourceType
        suma1.setId(d);
        marco.addView(suma1);

        //Añadimos el destino aunque me he equivocado con el nombre claramente
        //hay que ponerle un id para luego poder identificarlo
        origen1 = new TextView(this);
        origen1.setGravity(Gravity.CENTER);
        origen1.setText("5");
        origen1.setTextColor(Color.RED);
        origen1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangulo));

        posicion = new Posicion_Pantalla();
        posicion.x = 1100;
        posicion.y = (150) + 10;
        posiciones_destinos.put(100, posicion);
        origen1.setOnTouchListener(this);
        origen1.setX(posicion.x);
        origen1.setY(posicion.y);
        origen1.setWidth(120);
        origen1.setHeight(120);
        d = 100;
        //noinspection ResourceType
        origen1.setId(d);
        marco.addView(origen1);


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
            NavUtils.navigateUpTo(this, new Intent(this, GameListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Al tocar la pantalla...
    public boolean onTouch(View view, MotionEvent event) {
        if(view.getId()<100) {
            //Recogemos las coordenadas del dedo
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            int destino_x = posiciones_destinos.get(100).x;
            int destino_y = posiciones_destinos.get(100).y;

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
                    //Al levantar el dedo si esta sobre el destino lo ponemos invisible
                    // si no lo llevamos a su lugar de origen
                    System.out.println(X);
                    System.out.println(Y);
                    System.out.println(destino_x);
                    System.out.println(destino_y);
                    if(X>=destino_x-10&&X<=destino_x+240&&Y>=destino_y+80&&Y<=destino_y+240){
                        view.setVisibility(View.INVISIBLE);

                    }
                    else {
                        view.setX(posiciones_elementos.get(1).x);
                        view.setY(posiciones_elementos.get(1).y);
                    }
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

        }
        //Se podría decir que 'dibujamos'
        //la posición de la imagen en el marco.
        marco.invalidate();
        return true;
    }
}

