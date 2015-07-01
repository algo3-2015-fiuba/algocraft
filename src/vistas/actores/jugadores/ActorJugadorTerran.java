package vistas.actores.jugadores;

import java.awt.Graphics;

import vistas.acciones.jugador.*;
import vistas.actores.Actor;

public class ActorJugadorTerran extends Actor {
	
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

}
