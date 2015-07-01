package juego.interfaces;

import java.awt.Color;
import java.util.Collection;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.excepciones.AccionInvalida;

public interface Controlable {
	
	public float vidaActual();
	
	public void recibirAtaque(float danio);
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango;
	
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida, AccionInvalida;
	
	public boolean colisionaCon(Controlable controlable);
	public boolean colisionaCon(EstrategiaMovimiento em);

	public Jugador obtenerPropietario();
	
	
	
}
