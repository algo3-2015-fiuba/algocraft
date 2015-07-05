package vistas.acciones.unidades;

import vistas.acciones.pendientes.AccionPendienteUnidad;
import vistas.acciones.unidades.excepciones.NadaSeleccionado;
import juego.Juego;
import juego.interfaces.Controlable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.UnidadAtaque;

public class AccionAtacar extends AccionPendienteUnidad {

	public void finalizar(Coordenada destino) throws Exception {
		
		Celda celdaSeleccionada = null;
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		} catch (CoordenadaFueraDeRango e) {
			e.printStackTrace();
		}
		
		Controlable victima = celdaSeleccionada.seleccionRelevante();
		
		if (victima == null) throw new NadaSeleccionado();
		
		((UnidadAtaque)this.unidadEmisora).atacarA(victima);
		
	}

	@Override
	public String nombre() {
		return "Atacar";
	}
}
