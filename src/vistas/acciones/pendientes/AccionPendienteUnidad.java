package vistas.acciones.pendientes;

import juego.interfaces.Controlable;
import juego.razas.unidades.Unidad;

public abstract class AccionPendienteUnidad extends AccionPendiente {
	protected Unidad unidadEmisora;

	public void iniciar(Controlable unidadEmisora) {
		this.unidadEmisora = (Unidad) unidadEmisora;
	}
}
