package vistas.acciones.unidades.magias;

import vistas.acciones.pendientes.AccionPendienteUnidad;
import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.terran.NaveCiencia;

public class AccionMisilEMP extends AccionPendienteUnidad {
	
	@Override
	public void finalizar(Coordenada destino) throws Exception {
		
		Celda celdaSeleccionada = null;
		
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		} catch (CoordenadaFueraDeRango e) {
			e.printStackTrace();
		}
		
		((NaveCiencia)this.unidadEmisora).lanzarEMP(celdaSeleccionada.getPosicion());
		
	}

	@Override
	public String nombre() {
		return "Misil EMP";
	}
}
