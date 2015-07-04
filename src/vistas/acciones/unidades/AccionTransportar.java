package vistas.acciones.unidades;

import vistas.acciones.pendientes.AccionPendienteUnidad;
import juego.Juego;
import juego.interfaces.Transportable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.UnidadTransporte;

public class AccionTransportar extends AccionPendienteUnidad {

	public void finalizar(Coordenada destino) throws Exception {
		
		Celda celdaSeleccionada = null;
		celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		
		Transportable transportable = (Transportable) celdaSeleccionada.seleccionRelevante();
		
		((UnidadTransporte)this.unidadEmisora).transportar(transportable);
		
	}

	@Override
	public String nombre() {
		return "Transportar";
	}
	
}
