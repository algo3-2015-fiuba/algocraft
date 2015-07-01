package vistas.actores.jugadores;

import java.awt.Graphics;

import vistas.acciones.jugador.*;
import vistas.actores.Actor;
import vistas.actores.construcciones.protoss.ActorBaseProtoss;
import vistas.actores.construcciones.terran.ActorBaseTerran;

public class ActorJugadorProtoss extends ActorJugador {
	
	public ActorJugadorProtoss() {
		this.nombre = "Unidad";
		
	}

	@Override
	public void dibujar(Graphics g) {
		//Nunca deberia dibujarse
		//Quizas mostrar un icono en la parte superior
	}

	@Override
	public void dibujarBase(Graphics g, Object base) {
		Actor actorBaseProtoss = new ActorBaseProtoss();
		actorBaseProtoss.asignarEstado(base);
		actorBaseProtoss.dibujar(g);
	}

}
