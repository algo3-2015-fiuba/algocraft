package juego.informadores;

import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class AtaqueUnidad {

	private int danioTierra, danioAire;
	private int rangoTierra, rangoAire;
	
	public AtaqueUnidad(int danioTierra, int danioAire, int rangoTierra, int rangoAire) {
		
		super();
		this.danioTierra = danioTierra;
		this.danioAire = danioAire;
		this.rangoTierra = rangoTierra;
		this.rangoAire = rangoAire;
		
	}
	
	public boolean estaEnRango(Unidad agresor, Unidad victima) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada ubicacionVictima = mapa.obtenerUbicacion(victima);
		Coordenada ubicacionAgresor = mapa.obtenerUbicacion(agresor);
		
		if ((ubicacionVictima == null) || (ubicacionAgresor == null)) return false;
		
		int distancia = mapa.distanciaEntreCoordenadas(ubicacionAgresor, ubicacionVictima);
		
		return (victima.colisionaCon(agresor)) ? (distancia <= this.rangoTierra) : (distancia <= this.rangoAire);
		
	}
	
	public boolean estaEnRango(Unidad agresor, Construccion victima) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada ubicacionAgresor = mapa.obtenerUbicacion(agresor);
		int distancia;
		Iterator<Celda> it;
		
		try {
			it = victima.obtenerRangoDeOcupacion().iterator();
		} catch (CoordenadaFueraDeRango cfdr) { return false; }
		
		if (it.hasNext()) {
			
			distancia = mapa.distanciaEntreCoordenadas(ubicacionAgresor, mapa.obtenerCoordenada(it.next()));
			
		} else return false;
		
		while (it.hasNext()) {
			
			int distanciaEntreCoordenadas = mapa.distanciaEntreCoordenadas(ubicacionAgresor, mapa.obtenerCoordenada(it.next()));
			
			if (distancia > distanciaEntreCoordenadas) distancia = distanciaEntreCoordenadas;
			
		}
		
		return (victima.colisionaCon(agresor)) ? (distancia <= this.rangoTierra) : (distancia <= this.rangoAire);
		
		
	}
	
	public void atacar(EstrategiaMovimiento movimientoAgresor, Controlable controlable) {
		
		if (controlable.colisionaCon(movimientoAgresor)) {
			
			controlable.recibirAtaque(this.danioTierra);
		
		} else {
			
			controlable.recibirAtaque(this.danioAire);
		}
		
	}
	
}
