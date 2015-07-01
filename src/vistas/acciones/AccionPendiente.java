package vistas.acciones;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;

public abstract class AccionPendiente {
	public abstract String nombre();
	
	public abstract void iniciar(Controlable elementoSeleccionado);

	public abstract void finalizar(Coordenada coordenada) throws Exception;
}
