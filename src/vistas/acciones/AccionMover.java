package vistas.acciones;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public class AccionMover extends AccionPendiente {

	public void finalizar(Coordenada destino) {
		try {
			this.unidadEmisora.moverse(destino);
		} catch (UbicacionInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
