package vistas.acciones.unidades;

import vistas.acciones.pendientes.AccionPendienteUnidad;
import juego.mapa.Coordenada;
import juego.razas.unidades.UnidadTransporte;

public class AccionDescender extends AccionPendienteUnidad {

	@Override
	public void finalizar(Coordenada destino) throws Exception {
		
		if(this.unidadEmisora != null) {
			UnidadTransporte unidadTransporte = ((UnidadTransporte) this.unidadEmisora);
			unidadTransporte.bajarPrimero(destino);
		} else {
			throw new Exception("La unidad de transporte no posee unidades para descender.");
		}
		
	}

	@Override
	public String nombre() {
		return "Descender";
	}
	
}
