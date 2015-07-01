package vistas.acciones;

import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.excepciones.AtaqueInvalido;

public abstract class AccionPendienteUnidad extends AccionPendiente {
	protected Unidad unidadEmisora;

	public void iniciar(Unidad unidadEmisora) {
		this.unidadEmisora = unidadEmisora;
	}
}
