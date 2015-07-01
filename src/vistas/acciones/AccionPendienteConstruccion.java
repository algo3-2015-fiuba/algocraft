package vistas.acciones;

import juego.interfaces.Controlable;

public abstract class AccionPendienteConstruccion extends AccionPendiente {
	protected Controlable construccionSeleccionada;

	public void iniciar(Controlable construccionSeleccionada) {
		this.construccionSeleccionada = construccionSeleccionada;
	}
}
