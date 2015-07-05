package vistas.acciones.unidades.magias;

import java.net.URL;

import vistas.Aplicacion;
import vistas.acciones.pendientes.AccionPendienteUnidad;
import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.terran.NaveCiencia;

public class AccionMisilEMP extends AccionPendienteUnidad {

	protected URL url;
	
	public AccionMisilEMP() {
		
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/magias/misil emp.png");
		
	}
	
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
