package juego.interfaces;

import juego.estrategias.MovimientoConstruccion;
import juego.estrategias.MovimientoTerrestre;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;

public interface EstrategiaMovimiento {

	public boolean visionSuficiente(Coordenada posicion, Coordenada coordFinal);
	public void descubrirMapa(Jugador propietario, Controlable controlable);
	
	public void moverse(Jugador controlador, Controlable controlable, Coordenada coordFinal) throws UbicacionInvalida;
	public void desocupar(Controlable controlable);
	
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido);
	public boolean colisionaCon(MovimientoVolador volador);
	public boolean colisionaCon(MovimientoTerrestre terrestre);
	public boolean colisionaCon(MovimientoConstruccion construccion);

}
