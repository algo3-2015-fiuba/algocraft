package juego.razas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mapa.Coordenada;
import juego.interfaces.CommandConstructor;
import juego.interfaces.Construible;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;

public abstract class Raza {

	public abstract void construir(CommandConstructor constructor, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir;	

}