package vistas.acciones;

import juego.mapa.Coordenada;

public class AccionMover extends AccionPendienteUnidad {

	public void finalizar(Coordenada destino) throws Exception {
		this.unidadEmisora.moverse(destino);
	}

	@Override
	public String nombre() {
		return "Mover";
	}
}
