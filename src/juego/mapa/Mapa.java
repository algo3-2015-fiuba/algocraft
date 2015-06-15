package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;

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
			throws CoordenadaFueraDeRango {	
		
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

	public void ubicarEnCeldaDisponible(Coordenada coordenadaDeterminante, Unidad unidad) {
		
		//Ubica a una unidad en un rango cercano a la coordenada determinante
		
		int x = coordenadaDeterminante.getX();
		int y = coordenadaDeterminante.getY();
		int i = 0, j = 0;
		Coordenada coordenadaDisponible = null;
		boolean ubicado = false;
		
		while ((j < 15) && (!ubicado)) {
			while ((i < 15) && (!ubicado)) {
				
				try {
					coordenadaDisponible = new Coordenada(x+i, y+j);
					this.obtenerCelda(coordenadaDisponible);
					ubicado = true;
				} catch (UbicacionInvalida ui) {}
				
				i++;
			}
			j++;
		}
		
		try {
			unidad.moverse(coordenadaDisponible);
		} catch (UbicacionInvalida ui) {}
		
	}
	
	public static int distanciaEntreCoordenadas(Coordenada coordInicial, Coordenada coordFinal) {
		
		int distanciaX = Math.abs(coordInicial.getX() - coordFinal.getX());
		int distanciaY = Math.abs(coordInicial.getY() - coordFinal.getX());
		
		
		return distanciaX + distanciaY;
	}
	
}
