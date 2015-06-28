package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class Mapa {
	
	private HashMap<Coordenada,Celda> celdas;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public boolean contiene(Celda celda) {
		return (this.celdas.containsValue(celda));
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
	
	public Collection<Celda> obtenerCeldas() {
		return this.celdas.values();
	}
	
	public HashMap<Coordenada, Celda> obtenerMapa() {
		return this.celdas;
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
	
	public Collection<Celda> obtenerRangoRadialDeCeldas(Coordenada centro, int distancia) {
		ArrayList<Celda> celdasSeleccionadas = new ArrayList<Celda>();
		
		//Seleccionamos un cuadrado y despues sacamos todos los que no cumplen la distancia
		int x = centro.getX();
		int y = centro.getY();
		
		for (int i = x - distancia; i <= x + distancia; i++) {
			for (int j = y - distancia; j <= y + distancia; j++) {
				Coordenada coordActual = new Coordenada(i, j);
				
				if(this.distanciaEntreCoordenadas(centro, coordActual) <= distancia) {
					try {
						Celda celdaActual = this.obtenerCelda(coordActual);
						celdasSeleccionadas.add(celdaActual);						
					} catch (CoordenadaFueraDeRango e) { }
				}
				
			}
		}
		
		
		return celdasSeleccionadas;
	}
	
	public int distanciaEntreCoordenadas(Coordenada coordInicial, Coordenada coordFinal) {
		
		int distanciaX = Math.abs(coordInicial.getX() - coordFinal.getX());
		int distanciaY = Math.abs(coordInicial.getY() - coordFinal.getY());
		
		return (distanciaX + distanciaY);
	}

	public Coordenada obtenerUbicacion(Unidad unidad) {

		for (Coordenada coordenada : this.celdas.keySet()) {
			
			Celda celda = this.celdas.get(coordenada);
			
			if (celda.contiene(unidad)) {
				return coordenada;
			}
		}
		
		return null;
	}
	
	public Coordenada obtenerUbicacion(Construccion construccion) {
		
		for (Coordenada coordenada : this.celdas.keySet()) {
			
			Celda celda = this.celdas.get(coordenada);
			
			if (celda.contiene(construccion)) {
				return coordenada;
			}
		}
		
		return null;
	}
	
}
