package vistas.acciones.unidades.magias;

import vistas.acciones.pendientes.AccionPendienteUnidad;
import vistas.acciones.unidades.excepciones.NadaSeleccionado;
import juego.Juego;
import juego.interfaces.Controlable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;

public class AccionAlucinacion extends AccionPendienteUnidad {
	
	@Override
	public void finalizar(Coordenada destino) throws Exception {
		
		Celda celdaSeleccionada = null;
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		} catch (CoordenadaFueraDeRango e) {
			e.printStackTrace();
		}
		
		Controlable victima = celdaSeleccionada.seleccionRelevante();
		
		if (victima == null) throw new NadaSeleccionado();
		
		((AltoTemplario)this.unidadEmisora).lanzarAlucinacion((Unidad) victima);
		
	}

	@Override
	public String nombre() {
		return "Alucinar";
	}
}