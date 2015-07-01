package vistas.acciones;

import juego.mapa.Coordenada;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.excepciones.AtaqueInvalido;

public abstract class AccionPendiente {
	public abstract String nombre();
	
	public void iniciar(Unidad unidadEmisora) {}
	public void iniciar(Construccion construccionEmisora) {}

	public abstract void finalizar(Coordenada coordenada) throws Exception;
}
