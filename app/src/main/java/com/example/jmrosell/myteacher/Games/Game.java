package com.example.jmrosell.myteacher.Games;

/**
 * Created by Jose on 21/03/2015.
 */
public class Game {
    private int id;
    private String nombre;
    private String dificultad;
    private String descripcion;

    public Game(){

    }

    public Game(int id, String nombre, String dificultad, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return nombre;
    }
}

