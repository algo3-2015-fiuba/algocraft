package vistas.acciones.construcciones.protoss;

import juego.Juego;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.Asimilador;
import vistas.acciones.AccionPendienteConstruccion;

public class AccionCrearAsimilador extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorProtoss jugador = (JugadorProtoss) Juego.getInstance().turnoDe();
		
		jugador.construir(new Asimilador(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Asimilador";
	}
	
}
