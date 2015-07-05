package vistas.acciones.unidades.magias;

import java.net.URL;

import vistas.Aplicacion;
import vistas.acciones.pendientes.AccionPendienteUnidad;
import vistas.acciones.unidades.excepciones.NadaSeleccionado;
import juego.Juego;
import juego.interfaces.Controlable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.terran.NaveCiencia;

public class AccionRadiacion extends AccionPendienteUnidad {

	protected URL url;
	
	public AccionRadiacion() {
		
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/magias/radiacion.png");
		
	}
	
	public void finalizar(Coordenada destino) throws Exception {
		
		Celda celdaSeleccionada = null;
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		} catch (CoordenadaFueraDeRango e) {
			e.printStackTrace();
		}
		
		Controlable victima = celdaSeleccionada.seleccionRelevante();
		
		if (victima == null) throw new NadaSeleccionado();
		
		((NaveCiencia)this.unidadEmisora).lanzarRadiacion((Unidad) victima);
		
	}

	@Override
	public String nombre() {
		return "Radiacion";
	}
}