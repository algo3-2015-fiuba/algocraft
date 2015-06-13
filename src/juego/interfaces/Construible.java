package juego.interfaces;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;

public interface Construible {

	public void construir(JugadorTerran jt, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos;
	public void construir(JugadorProtoss jp, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos;
	public void actualizarConstruccion();
	public boolean construccionFinalizada();
	
	public boolean puedeHospedarUnidades();
	public boolean puedeEntrenarUnidades();
	public boolean puedeExtraerRecursos();
	
}
