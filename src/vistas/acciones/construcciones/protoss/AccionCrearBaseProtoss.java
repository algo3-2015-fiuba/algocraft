package vistas.acciones.construcciones.protoss;

import vistas.acciones.pendientes.AccionPendienteConstruccion;
import juego.Juego;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.BaseProtoss;

public class AccionCrearBaseProtoss extends AccionPendienteConstruccion {

	@Override
	public String nombre() {
		return "Crear Base";
	}
	
	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorProtoss jugador = (JugadorProtoss) Juego.getInstance().turnoDe();
		
		jugador.construir(new BaseProtoss(jugador, destino));
		
	}
	
}
