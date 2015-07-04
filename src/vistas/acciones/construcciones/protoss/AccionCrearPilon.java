package vistas.acciones.construcciones.protoss;

import juego.Juego;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.Pilon;
import vistas.acciones.pendientes.AccionPendienteConstruccion;

public class AccionCrearPilon extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorProtoss jugador = (JugadorProtoss) Juego.getInstance().turnoDe();
		
		jugador.construir(new Pilon(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Pilon";
	}
	
}