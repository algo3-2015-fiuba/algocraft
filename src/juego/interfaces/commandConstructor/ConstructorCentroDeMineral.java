package juego.interfaces.commandConstructor;

import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Terran;
import juego.razas.terran.construcciones.CentroDeMineral;


public class ConstructorCentroDeMineral extends CommandConstructor {
	
	@Override
	public void ejecutar(Terran raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, CoordenadaFueraDeRango {
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int costoMinerales = 50;
		
		if ((!mapa.existeNodoDeMinerales(coordenada)) || (mapa.obtenerCelda(coordenada).ocupadoEnTierra())) throw new UbicacionInvalida();

		
		jugador.consumirMinerales(costoMinerales);
		
		CentroDeMineral centroDeMineral = new CentroDeMineral(mapa.getNodoDeMinerales(coordenada));
		
		jugador.agregarConstructor(this);
		this.enConstruccion = centroDeMineral;
		//Falta agregar el centro de mineral al mapa
	}
	
}
