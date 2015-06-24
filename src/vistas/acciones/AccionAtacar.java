package vistas.acciones;

import juego.Juego;
import juego.interfaces.excepciones.NoTieneVision;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadAtaque;

public class AccionAtacar extends AccionPendiente {

	public void finalizar(Coordenada destino) {
		
		Unidad unidad = null;
		
		Celda celdaSeleccionada = null;
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		} catch (CoordenadaFueraDeRango e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!celdaSeleccionada.getUnidades().isEmpty()) {
			unidad = celdaSeleccionada.getUnidades().iterator().next();
		}
		
		try {
			((UnidadAtaque)this.unidadEmisora).atacarA(unidad);
		} catch (NoTieneVision e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
