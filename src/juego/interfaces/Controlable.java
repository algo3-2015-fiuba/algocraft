package juego.interfaces;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public interface Controlable {

	public void moverse(Coordenada coordFinal) throws UbicacionInvalida;
	public boolean ocupanMismoEspacio(Terrestre terrestre);
	public boolean ocupanMismoEspacio(Volador volador);
	public boolean ocupanMismoEspacio(Construible construible);
	
}
