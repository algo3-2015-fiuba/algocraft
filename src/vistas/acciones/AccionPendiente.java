package vistas.acciones;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;

public abstract class AccionPendiente {
	public abstract String nombre();
	
	public abstract void iniciar(Controlable elementoSeleccionado) throws Exception;

	public abstract void finalizar(Coordenada coordenada) throws Exception;
	
	public boolean asignarComoPendiente() {
		return true;
	}
}
