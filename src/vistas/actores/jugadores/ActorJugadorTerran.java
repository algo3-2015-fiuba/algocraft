package vistas.actores.jugadores;

import java.awt.Graphics;

import vistas.acciones.jugador.*;
import vistas.actores.Actor;
import vistas.actores.construcciones.terran.ActorBaseTerran;

public class ActorJugadorTerran extends ActorJugador {
	
	public ActorJugadorTerran() {
		this.nombre = "Unidad";
		this.acciones.add(new AccionCrearCentroMineral());
		this.acciones.add(new AccionCrearBarraca());
		this.acciones.add(new AccionCrearDepositoDeSuministro());
		this.acciones.add(new AccionCrearRefineria());
		this.acciones.add(new AccionCrearFabrica());
		
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
