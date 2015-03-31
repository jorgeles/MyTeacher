package com.example.jmrosell.myteacher.Games.Game_Asociacion;

import java.util.Hashtable;

/**
 * Created by jorgeleonfernandez on 31/03/15.
 */
public class AsociacionContent {

    Game_Asociacion game = new Game_Asociacion();

    public Hashtable<Integer,Elemento_Asociacion> elementos = game.elementos;
    public Hashtable<Integer,Destino_Asociacion> destinos = game.destinos;

    public Destino_Asociacion destino1 = new Destino_Asociacion();
    public Destino_Asociacion destino2 = new Destino_Asociacion();

    public Elemento_Asociacion elemento1 = new Elemento_Asociacion();
    public Elemento_Asociacion elemento2 = new Elemento_Asociacion();


    public AsociacionContent(){

        elemento1.id=1;
        elemento2.id=2;

        destino1.id=101;
        destino2.id=102;

        elemento1.destino=destino1;
        elemento2.destino=destino2;

        elemento1.name="3+2";
        elemento2.name="4*3";

        destino1.name="5";
        destino2.name="12";

        elementos.put(elemento1.id, elemento1);
        elementos.put(elemento2.id, elemento2);

        destinos.put(destino1.id, destino1);
        destinos.put(destino2.id, destino2);

    }



}
