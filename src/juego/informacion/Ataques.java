package juego.informacion;

import java.util.Iterator;

import juego.Juego;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

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

	//Estos metodos son practicamente iguales, habria que hacer un refactoring en el futuro
	
	public boolean estaEnRango(EstrategiaMovimiento estrategiaDeMovimiento, Coordenada ubicacionAgresor, Unidad victima) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		int distancia = 0;
		Coordenada ubicacionVictima = mapa.obtenerUbicacion(victima);
		distancia = mapa.distanciaEntreCoordenadas(ubicacionAgresor, ubicacionVictima);
		
		if (victima.colisionaCon(estrategiaDeMovimiento)) {
			return (distancia <= this.rangoTierra);
		} else {
			return (distancia <= this.rangoAire);
		}
		
	}
	
	public boolean estaEnRango(EstrategiaMovimiento estrategiaDeMovimiento, Coordenada ubicacionAgresor, Construccion victima) 
			throws CoordenadaFueraDeRango {
		
		Mapa mapa = Juego.getInstance().getMapa();
		int distancia = -1;
		
		Iterator<Celda> it = victima.obtenerRangoDeOcupacion().iterator();
		while (it.hasNext()) {
			int distanciaEntreCoordenadas = mapa.distanciaEntreCoordenadas(ubicacionAgresor, mapa.obtenerCoordenada(it.next()));
			if ((distancia == -1) || (distancia > distanciaEntreCoordenadas)) {
				distancia = distanciaEntreCoordenadas;
			}
		}
		
		if (victima.colisionaCon(estrategiaDeMovimiento)) {
			return (distancia <= this.rangoTierra);
		} else {
			return (distancia <= this.rangoAire);
		}
		
	}
	
	public void atacar(EstrategiaMovimiento movimientoAgresor, Unidad victima) {
		
		if (victima.colisionaCon(movimientoAgresor)) {
			victima.recibirAtaque(this.danioTierra);
		} else {
			victima.recibirAtaque(this.danioAire);
		}
		
	}
	
	public void atacar(EstrategiaMovimiento movimientoAgresor, Construccion victima) {
		
		if (victima.colisionaCon(movimientoAgresor)) {
			victima.recibirAtaque(this.danioTierra);
		} else {
			victima.recibirAtaque(this.danioAire);
		}
		
	}
	
}
