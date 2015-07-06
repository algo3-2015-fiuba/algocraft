package vistas.acciones.unidades;

import vistas.acciones.pendientes.AccionPendienteUnidad;
import juego.Juego;
import juego.interfaces.Transportable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;

public class AccionDescender extends AccionPendienteUnidad {

	@Override
	public void finalizar(Coordenada destino) throws Exception {
		
		Celda celdaSeleccionada = null;
		celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		
		Transportable transportable = (Transportable) celdaSeleccionada.seleccionRelevante();
		
		//Accion de descender unidad
		
	}

	@Override
	public String nombre() {
		return "Descender";
	}
	
}
