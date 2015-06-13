package juego.interfaces;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public interface Controlable {

	public void moverse(Coordenada coordFinal) throws UbicacionInvalida;
	public boolean ocupanMismoEspacio(Construible construible);
	public boolean ocupanMismoEspacio(Unidad unidad);
	
}
