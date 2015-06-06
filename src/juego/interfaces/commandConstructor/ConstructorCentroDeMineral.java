package juego.interfaces.commandConstructor;

import mapa.Coordenada;
import mapa.Mapa;
import mapa.excepciones.CoordenadaFueraDeBordes;
import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.razas.Terran;
import juego.razas.terran.construcciones.CentroDeMineral;


public class ConstructorCentroDeMineral extends CommandConstructor {
	
	/*
	 * Solo se ejecuta con un Terran
	 */
	
	@Override
	public void ejecutar(Terran raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida {
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int costoMinerales = 50;
		
		try {
			if ((!mapa.existeNodoDeMinerales(coordenada)) || (mapa.obtenerCelda(coordenada).ocupadoEnTierra())) throw new UbicacionInvalida();
		} catch (CoordenadaFueraDeBordes cfdb) { throw new UbicacionInvalida(); }
		
		jugador.consumirMinerales(costoMinerales);
		
		CentroDeMineral centroDeMineral = new CentroDeMineral(mapa.getNodoDeMinerales(coordenada));
		
		mapa.enConstruccion(centroDeMineral, coordenada);
	}
	
}
