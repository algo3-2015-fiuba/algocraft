package vistas.acciones.construcciones.protoss;

import juego.Juego;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.ArchivoTemplario;
import vistas.acciones.pendientes.AccionPendienteConstruccion;

public class AccionCrearArchivoTemplario extends AccionPendienteConstruccion {

	public void finalizar(Coordenada destino) throws Exception {
		
		JugadorProtoss jugador = (JugadorProtoss) Juego.getInstance().turnoDe();
		
		jugador.construir(new ArchivoTemplario(), destino);
	}

	@Override
	public String nombre() {
		return "Crear Archivo Templario";
	}
}
