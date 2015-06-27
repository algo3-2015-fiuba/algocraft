package juego.razas.ataques;

import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
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
		return ((victima.estaEnRangoDeAtaque(agresor, this.rangoTierra)) || 
				(victima.estaEnRangoDeAtaque(agresor, this.rangoAire)));
	}
	
	public void atacar(EstrategiaMovimiento movimientoAgresor, Controlable controlable) {
		
		if (controlable.colisionaCon(movimientoAgresor)) {
			
			controlable.recibirAtaque(this.danioTierra);
		
		} else {
			
			controlable.recibirAtaque(this.danioAire);
		}
		
	}
	
}
