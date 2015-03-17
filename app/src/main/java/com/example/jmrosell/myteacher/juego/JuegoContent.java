package com.example.jmrosell.myteacher.juego;

import java.util.ArrayList;

/**
 * Created by jmrosell on 10/03/15.
 */
public class JuegoContent {
    private static ArrayList<Juego> juegos = new ArrayList();

    public static ArrayList getJuegosList(){
        return juegos;
    }

    /*
     * Carga unos cuantos juegos de prueba
     */
    public static void loadGames(){
        Juego juego;

        juego = new Juego();
        juego.setId(1);
        juego.setNombre("Puzzle");
        juego.setDificultad("Facil");
        juegos.add(juego);

        juego = new Juego();
        juego.setId(2);
        juego.setNombre("Cartas");
        juego.setDificultad("Media");
        juegos.add(juego);
    }
}
