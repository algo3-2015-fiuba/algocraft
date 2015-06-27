package juego.interfaces;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public interface Controlable {
	
	public void recibirAtaque(float danio);
	
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida;
	
	public boolean colisionaCon(Controlable controlable);
	public boolean colisionaCon(EstrategiaMovimiento em);
	
}
