package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Controlable;
import juego.jugadores.Jugador;

public class MapaJugador {
	
	private Jugador propietario;
	private Mapa mapaDescubierto;
	
	public MapaJugador(Jugador propietario) {
		
		super();
		this.propietario = propietario;
		this.mapaDescubierto = new Mapa();
		
	}
	
	public void celdaDescubierta(Celda celda) {
		
		Coordenada coordenadaDeCelda = celda.getPosicion();
		if (!this.mapaDescubierto.contiene(celda)) {
			this.mapaDescubierto.agregarCelda(coordenadaDeCelda, celda);
		}
		
	}
	
	public boolean tieneVision(Controlable controlable) {
		
		for (Celda celda : this.mapaDescubierto.obtenerCeldas()) {
			if (celda.contiene(controlable)) return true;
		}
		
		return false;
	}
	
	public boolean tieneVision(Celda celda) {
		return this.mapaDescubierto.contiene(celda);
	}

	public void perdidaDeVision(Controlable controlable) {
		
		Collection<Celda> mapaPerdido = new ArrayList<Celda>();
		
		for (Celda celda : this.mapaDescubierto.obtenerCeldas()) {
			
			if (celda.observadaPor(controlable)) {
				
				celda.removerObservador(controlable);
				
				if (!celda.observadaPor(this.propietario)) mapaPerdido.add(celda);
				
			}
			
		}
		
		Iterator<Celda> it = mapaPerdido.iterator();
			
		while (it.hasNext()) {
				
			this.mapaDescubierto.removerCelda(it.next());

		}
		
	}
	
}
