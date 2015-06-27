package juego.razas.ataques;

import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.UnidadAtaque;

public class Ataques {

	private int danioTierra, danioAire;
	private int rangoTierra, rangoAire;
	
	public Ataques(int danioTierra, int danioAire, int rangoTierra, int rangoAire) {
		
		super();
		this.danioTierra = danioTierra;
		this.danioAire = danioAire;
		this.rangoTierra = rangoTierra;
		this.rangoAire = rangoAire;
		
	}
	
	public boolean estaEnRango(UnidadAtaque agresor, Controlable victima) {	
		
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
		
		return ((distancia <= this.rangoTierra) || (distancia <= this.rangoAire));

	}
	
	public void atacar(EstrategiaMovimiento movimientoAgresor, Controlable controlable) {
		
		if (controlable.colisionaCon(movimientoAgresor)) {
			
			controlable.recibirAtaque(this.danioTierra);
		
		} else {
			
			controlable.recibirAtaque(this.danioAire);
		}
		
	}
	
}
