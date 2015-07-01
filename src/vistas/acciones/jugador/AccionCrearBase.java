package vistas.acciones.jugador;

import juego.Juego;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionBase;
import vistas.acciones.AccionPendienteConstruccion;

public class AccionCrearBase extends AccionPendienteConstruccion {

		public void finalizar(Coordenada destino) throws Exception {
			
			JugadorTerran jugador = (JugadorTerran) Juego.getInstance().turnoDe();
			jugador.construir(new ConstruccionBase(jugador, destino));
			
		}

		@Override
		public String nombre() {
			return "Crear Base";
		}
	
}
