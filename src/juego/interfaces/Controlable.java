package juego.interfaces;

import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public interface Controlable {

	public void moverse(Coordenada coordInicial, Coordenada coordFinal) throws UbicacionInvalida;
	boolean colisionaCon(Controlable controlable);
	boolean colisionaCon(EstrategiaMovimiento estrategiaDeOtro);
	
}
