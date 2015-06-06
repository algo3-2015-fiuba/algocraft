package juego.razas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mapa.Coordenada;
import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.Construible;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import mapa.Mapa;
import juego.razas.terran.construcciones.CentroDeMineral;

public class Terran extends Raza {
	
	@Override
	public void construir(CommandConstructor construccion, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir{
		construccion.ejecutar(this, coordenada);
	}	
}
