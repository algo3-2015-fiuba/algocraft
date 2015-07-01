package vistas.acciones;

import juego.Juego;
import juego.interfaces.excepciones.NoTieneVision;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.terran.CentroDeMineral;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadAtaque;
import juego.razas.unidades.excepciones.AtaqueInvalido;

public class AccionCrearCentroMineral extends AccionPendienteRecurso {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorTerran jugador = (JugadorTerran)Juego.getInstance().turnoDe();
		
		jugador.construir(new CentroDeMineral(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Centro de Minerales";
	}
}
