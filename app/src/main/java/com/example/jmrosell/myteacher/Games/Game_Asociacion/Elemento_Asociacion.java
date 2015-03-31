package com.example.jmrosell.myteacher.Games.Game_Asociacion;

import android.graphics.drawable.Drawable;

public class Elemento_Asociacion {
	public String name;
	public Destino_Asociacion destino;
	public int id;
	public Drawable imagen;
	
	public Elemento_Asociacion(){
		destino = new Destino_Asociacion();
		name="Hola";
	}
}
