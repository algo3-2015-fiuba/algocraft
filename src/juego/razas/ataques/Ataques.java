package juego.razas.ataques;

import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.mapa.Coordenada;

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
	
	
	public boolean estaEnRango(EstrategiaMovimiento tacticaAgresor, Coordenada ubicacionAgresor, Controlable victima) {
		return tacticaAgresor.estaEnRangoDeAtaque(this, ubicacionAgresor, victima);
	} 

	public boolean estaEnRangoTierra(int distanciaVictima) {	
		return (this.rangoTierra >= distanciaVictima);
	}
	
	public boolean estaEnRangoAire(int ubicacionVictima) {
		return (this.rangoAire >= ubicacionVictima);		
	}
	
	
	public void atacar(EstrategiaMovimiento tacticaAgresor, Controlable victima) {
		tacticaAgresor.atacar(this, victima);
	}
	
	public void atacarPorTierra(Controlable victima) {
		victima.recibirAtaque(this.danioTierra);
	}
	
	public void atacarPorAire(Controlable victima) {
		victima.recibirAtaque(this.danioAire);
	}
	
}
