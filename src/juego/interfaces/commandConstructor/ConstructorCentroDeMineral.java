package juego.interfaces.commandConstructor;

import mapa.Coordenada;
import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import mapa.Mapa;
import juego.razas.Raza;
import juego.razas.Terran;
import juego.razas.terran.construcciones.CentroDeMineral;


public class ConstructorCentroDeMineral implements CommandConstructor {
	
	private int costoMinerales = 50;

	@Override
	public void construir(Raza raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		raza.construir(this, coordenada);
	}
	
	public void construir(Terran raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Jugador jugador = juego.turnoDe();
		
		if ((!mapa.existeNodoDeMinerales(coordenada)) || (mapa.ubicacionOcupada(coordenada))) throw new UbicacionInvalida();
		
		jugador.consumirMinerales(this.costoMinerales);
		
		CentroDeMineral centroDeMineral = new CentroDeMineral(mapa.getNodoDeMinerales(coordenada));
		
		mapa.enConstruccion(centroDeMineral, coordenada);
	}
	
}
