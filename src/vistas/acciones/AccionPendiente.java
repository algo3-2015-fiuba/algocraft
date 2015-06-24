package vistas.acciones;

import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public abstract class AccionPendiente {
	protected Unidad unidadEmisora;

	public void iniciar(Unidad unidadEmisora) {
		this.unidadEmisora = unidadEmisora;
	}

	public abstract void finalizar(Coordenada coordenada);
}
