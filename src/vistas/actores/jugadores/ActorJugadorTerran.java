package vistas.actores.jugadores;

import java.awt.Graphics;

import vistas.actores.Actor;
import vistas.actores.construcciones.terran.ActorBaseTerran;

public class ActorJugadorTerran extends ActorJugador {
	
	public ActorJugadorTerran() {
		this.nombre = "Unidad";
	}

	@Override
	public void dibujar(Graphics g) {
		//Nunca deberia dibujarse
		//Quizas mostrar un icono en la parte superior
	}

	@Override
	public void dibujarBase(Graphics g, Object base) {
		Actor actorBaseTerran = new ActorBaseTerran();
		actorBaseTerran.asignarEstado(base);
		actorBaseTerran.dibujar(g);
	}

}
