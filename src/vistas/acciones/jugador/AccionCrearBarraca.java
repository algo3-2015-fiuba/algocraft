package vistas.acciones.jugador;

import vistas.acciones.AccionPendienteConstruccion;
import juego.Juego;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.CentroDeMineral;

public class AccionCrearBarraca extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorTerran jugador = (JugadorTerran) Juego.getInstance().turnoDe();
		
		jugador.construir(new Barraca(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Barraca";
	}
}
