package juego.razas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mapa.Coordenada;
import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.Construible;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Mapa;
import juego.razas.terran.construcciones.CentroDeMineral;

public class Terran extends Raza {
	
	private Collection<Construible> enConstruccion;
	
	public Terran() {
		super();
	}
	
	@Override
	public void construir(CommandConstructor construccion, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida {
		//Double dispatch
	}

	public void construir(ConstructorCentroDeMineral construccion, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida {
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Jugador jugador = juego.turnoDe();
		int costoMinerales = 50;
		
		if ((!mapa.existeNodoDeMinerales(coordenada)) || (mapa.ubicacionOcupada(coordenada))) throw new UbicacionInvalida();
		
		jugador.consumirMinerales(costoMinerales);
		
		CentroDeMineral centroDeMineral = new CentroDeMineral(mapa.getNodoDeMinerales(coordenada));
		
		mapa.enConstruccion(centroDeMineral, coordenada);
		this.enConstruccion.add(centroDeMineral);
		
	}
	
	@Override
	public void turnoFinalizado() {
		
		super.turnoFinalizado();
		
	}
	
}
