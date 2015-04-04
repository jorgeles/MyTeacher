package com.example.jmrosell.myteacher.Games.Game_Cajas;

import java.util.ArrayList;

/**
 * Created by Jose on 04/04/2015.
 */
public class CajasContent {
    private static ArrayList<ArrayList<String>> cajasList = new ArrayList<ArrayList<String>>();

    public static ArrayList<ArrayList<String>> getCajasList(){
        return cajasList;
    }

    public static void loadCajas(){
        ArrayList<String> caja;

        caja = new ArrayList<>();
        caja.add("Abaco");      //Primera opcion
        caja.add("Avaco");      //Segunda opcion
        caja.add("Habaco");     //Tercera opcion
        caja.add("Abaco");      //Opcion correcta
        cajasList.add(caja);
    }
}
