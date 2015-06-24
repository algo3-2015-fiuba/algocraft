package vistas.handlers.interfaces;

import juego.mapa.Coordenada;

public interface ObservadorCelda {
	public void notificar(Coordenada coordenadaSeleccionada);
}
