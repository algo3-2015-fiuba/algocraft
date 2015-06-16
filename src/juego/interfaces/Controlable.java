package juego.interfaces;

import juego.interfaces.estrategias.EstrategiaPosicion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public interface Controlable {

	public void moverse(Coordenada coordFinal) throws UbicacionInvalida;
	boolean ocupanMismoEspacio(Controlable controlable);
	boolean ocupanMismoEspacio(EstrategiaPosicion estrategiaDeOtro);
	
}
