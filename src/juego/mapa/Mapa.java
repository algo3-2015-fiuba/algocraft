package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import juego.interfaces.excepciones.CeldaOcupada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public class Mapa {
	
	private HashMap<Coordenada,Celda> celdas;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public void agregarCelda(Coordenada coord, Celda celda) {		
		this.celdas.put(coord, celda);
	}	

	public Celda obtenerCelda(Coordenada coord) throws CoordenadaFueraDeRango {
		
		if (celdas.containsKey(coord)) {
			return celdas.get(coord); 
		} else {
			throw new CoordenadaFueraDeRango();
		}
	}
	
	public Collection<Celda> obtenerRangoDeCeldas(Coordenada coordenadaDeterminante, int rangoX, int rangoY) 
			throws CoordenadaFueraDeRango, CeldaOcupada {
			
		
		Collection<Celda> rangoDeCeldas = new ArrayList<Celda>();
			
		int x = coordenadaDeterminante.getX();
		int y = coordenadaDeterminante.getY();
		
		for (int i = 0; i < rangoY; i++) {
			for (int j = 0; j < rangoX; j++) {
				rangoDeCeldas.add(this.obtenerCelda(new Coordenada(x+j, y+i)));
			}
		}
			
		return rangoDeCeldas;
			
	}
	
}
