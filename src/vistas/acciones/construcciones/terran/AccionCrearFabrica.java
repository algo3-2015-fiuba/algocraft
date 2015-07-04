package vistas.acciones.construcciones.terran;

import vistas.acciones.pendientes.AccionPendienteConstruccion;
import juego.Juego;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Fabrica;

public class AccionCrearFabrica extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorTerran jugador = (JugadorTerran) Juego.getInstance().turnoDe();
		
		jugador.construir(new Fabrica(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Fabrica";
	}
}
