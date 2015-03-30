package com.example.jmrosell.myteacher.Games;

import java.util.ArrayList;

/**
 * Created by Jose on 21/03/2015.
 */
public class GameContent {
    private static ArrayList<Game> gameList = new ArrayList<>();

    public static ArrayList getGameList(){
        return gameList;
    }

    public static void loadGames(){
        Game juego;

        juego = new Game();
        juego.setId(1);
        juego.setNombre("Puzzle");
        juego.setDificultad("Facil");
        gameList.add(juego);

        juego = new Game();
        juego.setId(2);
        juego.setNombre("Cartas");
        juego.setDificultad("Media");
        gameList.add(juego);

        juego = new Game();
        juego.setId(3);
        juego.setNombre("Unir");
        juego.setDificultad("Dificil");
        gameList.add(juego);
    }
}
