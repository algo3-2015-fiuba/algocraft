package vistas.acciones;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadAtaque;

public class AccionAtacar extends AccionPendienteUnidad {

	public void finalizar(Coordenada destino) throws Exception {
		
		Unidad unidad = null;
		
		Celda celdaSeleccionada = null;
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(destino);
		} catch (CoordenadaFueraDeRango e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Controlable victima = celdaSeleccionada.seleccionRelevante();
		
		((UnidadAtaque)this.unidadEmisora).atacarA(victima);
		
	}

	@Override
	public String nombre() {
		return "Atacar";
	}
}
