package juego.mapa;

import juego.Juego;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class MapaJugador {
	
	private Mapa mapaDescubierto;
	
	public MapaJugador() {
		this.mapaDescubierto = new Mapa();
	}
	
	public boolean tieneVision(Unidad unidad) {
		
		for (Celda celda : this.mapaDescubierto.obtenerCeldas()) {
			
			if (celda.contiene(unidad)) return true;
			
		}
		
		return false;
 		
	}
	
	public boolean tieneVision(Construccion construccion) {
		
		for (Celda celda : this.mapaDescubierto.obtenerCeldas()) {
			
			if (celda.contiene(construccion)) return true;
			
		}
		
		return false;
		
	}
	
	public void celdaDescubierta(Celda celda) {
		
		Coordenada coordenadaDeCelda = Juego.getInstance().getMapa().obtenerCoordenada(celda);
		
		if (!this.mapaDescubierto.contiene(celda)) {
			this.mapaDescubierto.agregarCelda(coordenadaDeCelda, celda);
		}
		
	}

}
