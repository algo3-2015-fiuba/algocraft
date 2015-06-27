package vistas.handlers.interfaces;

import juego.mapa.Coordenada;
import juego.razas.unidades.excepciones.AtaqueInvalido;

public interface ObservadorCelda {
	public void notificar(Coordenada coordenadaSeleccionada) throws AtaqueInvalido;
}
