package vistas.actores.jugadores;

import java.awt.Graphics;

import vistas.actores.Actor;

public abstract class ActorJugador extends Actor {
	
	public ActorJugador() {
		this.nombre = "Unidad";
		
	}
	
	
	public abstract void dibujarBase(Graphics g, Object base);


	@Override
	public void dibujar(Graphics g) {
		//Nunca deberia dibujarse
		//Quizas mostrar un icono en la parte superior
	}

}
