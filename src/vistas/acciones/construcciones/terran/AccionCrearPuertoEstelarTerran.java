package vistas.acciones.construcciones.terran;

import juego.Juego;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.PuertoEstelar;
import vistas.acciones.pendientes.AccionPendienteConstruccion;

public class AccionCrearPuertoEstelarTerran extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorTerran jugador = (JugadorTerran) Juego.getInstance().turnoDe();
		
		jugador.construir(new PuertoEstelar(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Puerto Estelar";
	}
	
}
