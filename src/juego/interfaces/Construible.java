package juego.interfaces;

import java.util.Collection;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public interface Construible {

	public void posicionar(Coordenada posicion) throws UbicacionInvalida;
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango;
	public void actualizarConstruccion();
	public boolean construccionFinalizada();
	
	public boolean puedeHospedarUnidades();
	public boolean puedeEntrenarUnidades();
	public boolean puedeExtraerRecursos();
	
	public Jugador obtenerPropietario();
	
}
