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
        juego.setNombre("Asociacion");
        juego.setDificultad("Facil");
        juego.setDescripcion("Una descripcion muuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu lllllllaaaaaaaaaarrrrrgaaaaaaaaaaaaaa");
        gameList.add(juego);

        juego = new Game();
        juego.setId(2);
        juego.setNombre("Cajas");
        juego.setDificultad("Media");
        juego.setDescripcion("Otra descripcion");
        gameList.add(juego);
    }
}
