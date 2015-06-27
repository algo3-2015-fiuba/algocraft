package juego.mapa;

import juego.Juego;
import juego.interfaces.Controlable;

public class MapaJugador {
	
	private Mapa mapaDescubierto;
	
	public MapaJugador() {
		this.mapaDescubierto = new Mapa();
	}
	
	public void celdaDescubierta(Celda celda) {
		
		Coordenada coordenadaDeCelda = Juego.getInstance().getMapa().obtenerCoordenada(celda);
		
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

}
