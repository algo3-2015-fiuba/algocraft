package vistas.acciones;

import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import juego.recursos.Recurso;

public abstract class AccionPendienteRecurso extends AccionPendiente {
	protected Recurso recursoSeleccionado;

	public void iniciar(Recurso recursoSeleccionado) {
		this.recursoSeleccionado = recursoSeleccionado;
	}
}
