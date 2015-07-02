package vistas.acciones.construcciones.terran;

import vistas.acciones.AccionPendienteConstruccion;
import juego.Juego;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Refineria;

public class AccionCrearRefineria extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorTerran jugador = (JugadorTerran) Juego.getInstance().turnoDe();
		
		jugador.construir(new Refineria(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Refineria";
	}
}
