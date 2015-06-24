package vistas.handlers.interfaces;

import juego.mapa.Celda;

public interface ObservadorCelda {
	public void notificar(Celda celdaSeleccionada);
}
