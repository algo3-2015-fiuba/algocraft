package juego.razas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
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
		this.enConstruccion = new ArrayList<Construible>();
	}

	@Override
	public void construir(ConstructorCentroDeMineral construccion, int x, int y) throws RecursosInsuficientes, UbicacionInvalida {
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Jugador jugador = juego.turnoDe();
		int costoMinerales = 50;
		
		if ((!mapa.existeNodoDeMinerales(x,y)) || (mapa.ubicacionOcupada(x,y))) throw new UbicacionInvalida();
		
		jugador.gastarMinerales(costoMinerales);
		
		CentroDeMineral centroDeMineral = new CentroDeMineral(mapa.getNodoDeMinerales(x,y));
		
		mapa.enConstruccion(centroDeMineral, x, y);
		this.enConstruccion.add(centroDeMineral);
		
	}
	
	@Override
	public void turnoFinalizado() {
		
		ArrayList<Construible> construccionesFinalizadas = new ArrayList<Construible>();
		
		Iterator<Construible> it = this.enConstruccion.iterator();
		
		while (it.hasNext()) {
			
			Construible construccion = it.next();
			construccion.actualizarConstruccion();
			if (construccion.construccionFinalizada()) construccionesFinalizadas.add(construccion);
			
		}
		
		this.enConstruccion.removeAll(construccionesFinalizadas);
		
	}
	
}
