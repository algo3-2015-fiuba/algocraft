package juego.interfaces;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.UnidadAtaque;

public interface Controlable {
	
	public void recibirAtaque(float danio);
	public boolean estaEnRangoDeAtaque(UnidadAtaque agresor, int rangoAtaque);
	
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida;
	
	public boolean colisionaCon(Controlable controlable);
	public boolean colisionaCon(EstrategiaMovimiento em);
	
}
