package juego.interfaces;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public interface Transportable {

	public int pesoTransporte();
	public void transporteDestruido();
	public void subirATransporte();
	public void bajarDeTransporte(Coordenada coordActual, Coordenada coordBajar) throws UbicacionInvalida;
	
}
