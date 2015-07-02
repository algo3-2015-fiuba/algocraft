package juego.estrategias;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.ataques.Ataques;

public class MovimientoVolador extends MovimientoUnidad {

	public MovimientoVolador(int rangoDeMovimiento, int vision) {
	
		super();
		this.rangoDeMovimiento = rangoDeMovimiento;
		this.vision = vision;
		
	}
	
	@Override
	public boolean estaEnRangoDeAtaque(Ataques ataques, Coordenada ubicacionAgresor, Controlable victima) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		int distanciaVictima = mapa.distancia(ubicacionAgresor, victima);
		
		if (victima.colisionaCon(this)) {
			return ataques.estaEnRangoAire(distanciaVictima);
		} else {
			return ataques.estaEnRangoTierra(distanciaVictima);
		}
		
	}
	
	@Override
	public void atacar(Ataques ataques, Controlable victima) {
		
		if (victima.colisionaCon(this)) {
			ataques.atacarPorAire(victima);
		} else {
			ataques.atacarPorTierra(victima);
		}

	}
	
	@Override
	public boolean puedeOcupar(Controlable controlable, Celda celda) {
		
		if (celda.colisiona(controlable)) return false;
		return true;
		
	}

	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return movimientoDesconocido.colisionaCon(this);
	}

	@Override
	public boolean colisionaCon(MovimientoVolador volador) {
		return true;
	}

}
