package vistas.actores.construcciones;

import java.awt.Graphics;

import juego.interfaces.Controlable;
import juego.jugadores.Jugador;
import vistas.actores.ActorControlable;
import vistas.actores.jugadores.ActorJugador;
import vistas.utilidades.AsignadorVistas;

public class ActorBase extends ActorControlable {
	
	public ActorBase() {
		this.nombre = "Construccion Base";
	}
	
	@Override
	public void dibujar(Graphics g) {
		Jugador propietario = ((Controlable)this.elemento).obtenerPropietario();
		
		ActorJugador actorDeJugador = (ActorJugador)AsignadorVistas.getInstance().obtenerRepresentacion(propietario.getClass(), propietario);
		
		actorDeJugador.dibujarBase(g, this.elemento);
	}
	
	

}
