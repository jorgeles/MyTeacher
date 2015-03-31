package com.example.jmrosell.myteacher.Games.Game_Asociacion;

import com.example.jmrosell.myteacher.Games.Game;

import java.util.Hashtable;

/**
 * Created by jorgeleonfernandez on 31/03/15.
 */
public class Game_Asociacion extends Game {

    public Hashtable<Integer,Elemento_Asociacion> elementos;
    public Hashtable<Integer,Destino_Asociacion> destinos;

    public Game_Asociacion(){
        super();
        this.setNombre("Introduzca nombre de la actividad");
        elementos = new Hashtable<Integer,Elemento_Asociacion>();
        destinos = new Hashtable<Integer,Destino_Asociacion>();

    }
}
