package vistas.acciones.unidades.magias;

import java.net.URL;

import vistas.Aplicacion;
import vistas.acciones.pendientes.AccionPendienteUnidad;
import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.protoss.AltoTemplario;

public class AccionTormentaPsionica extends AccionPendienteUnidad {

	protected URL url;
	
	public AccionTormentaPsionica() {
		
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/magias/tormenta psionica.png");
		
	}
	
	public void finalizar(Coordenada destino) throws Exception {
		
		Celda celdaSeleccionada = null;
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		} catch (CoordenadaFueraDeRango e) {
			e.printStackTrace();
		}
		
		((AltoTemplario)this.unidadEmisora).lanzarTormentaPsionica(celdaSeleccionada.getPosicion());
		
	}

	@Override
	public String nombre() {
		return "Tormenta Psionica";
	}
}