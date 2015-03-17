package com.example.jmrosell.myteacher.juego;

/**
 * Created by jmrosell on 10/03/15.
 */
public class Juego {
    private int id;
    private String nombre;
    private String dificultad;

    public Juego (){

    }

    public Juego (int id, String nombre, String dificultad){
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
