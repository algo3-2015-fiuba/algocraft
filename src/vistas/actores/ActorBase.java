package vistas.actores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import juego.interfaces.Controlable;
import juego.jugadores.Jugador;
import vistas.Aplicacion;
import vistas.actores.jugadores.ActorJugador;
import vistas.mapa.VistaCelda;
import vistas.utilidades.AsignadorVistas;

public class ActorBase extends ActorControlable {
	
	public ActorBase() {
		this.nombre = "Unidad";
	}
	
	@Override
	public void dibujar(Graphics g) {
		Jugador propietario = ((Controlable)this.elemento).obtenerPropietario();
		
		ActorJugador actorDeJugador = (ActorJugador)AsignadorVistas.getInstance().obtenerRepresentacion(propietario.getClass(), propietario);
		
		actorDeJugador.dibujarBase(g, this.elemento);
	}
	
	

}