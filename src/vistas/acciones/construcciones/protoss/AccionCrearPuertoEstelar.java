package vistas.acciones.construcciones.protoss;

import juego.Juego;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.PuertoEstelar;
import vistas.acciones.AccionPendienteConstruccion;

public class AccionCrearPuertoEstelar extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorProtoss jugador = (JugadorProtoss) Juego.getInstance().turnoDe();
		
		jugador.construir(new PuertoEstelar(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Puerto Estelar";
	}
	
}
