package juego.bolsas;

import juego.Juego;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class BolsaDeAtaque {

	private int danioTierra, danioAire;
	private int rangoTierra, rangoAire;
	
	public BolsaDeAtaque(int danioTierra, int danioAire, int rangoTierra, int rangoAire) {
		super();
		this.danioTierra = danioTierra;
		this.danioAire = danioAire;
		this.rangoTierra = rangoTierra;
		this.rangoAire = rangoAire;
	}
	
	public int getDanioTierra() { return this.danioTierra; }
	public int getDanioAire() { return this.danioAire; }
	public int getRangoTierra() { return this.rangoTierra; }
	public int getRangoAire() { return this.rangoAire; }

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
	
	public boolean estaEnRango(EstrategiaMovimiento estrategiaDeMovimiento, Coordenada ubicacionAgresor, Construccion victima) {
		
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
