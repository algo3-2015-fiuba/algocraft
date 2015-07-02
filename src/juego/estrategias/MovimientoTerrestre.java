package juego.estrategias;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.materiales.Material;
import juego.razas.ataques.Ataques;

public class MovimientoTerrestre extends MovimientoUnidad {

	public MovimientoTerrestre(int rangoDeMovimiento, int vision) {
	
		super();
		this.rangoDeMovimiento = rangoDeMovimiento;
		this.vision = vision;
		
	}
	
	@Override
	public boolean estaEnRangoDeAtaque(Ataques ataques, Coordenada ubicacionAgresor, Controlable victima) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		int distanciaVictima = mapa.distancia(ubicacionAgresor, victima);
		
		if (victima.colisionaCon(this)) {
			return ataques.estaEnRangoTierra(distanciaVictima);
		} else {
			return ataques.estaEnRangoAire(distanciaVictima);
		}
		
	}
	
	@Override
	public void atacar(Ataques ataques, Controlable victima) {
		
		if (victima.colisionaCon(this)) {
			ataques.atacarPorTierra(victima);
		} else {
			ataques.atacarPorAire(victima);
		}

	}
	
	@Override
	public boolean puedeOcupar(Controlable controlable, Celda celda) {
		
		if (!celda.getMaterial().equals(Material.tierra)) return false;
		
		if (celda.poseeBase()) return false;
		
		if (celda.poseeRecurso()) return false;
		
		if (celda.colisiona(controlable)) return false;
		
		return true;
		
	}
	
	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return (movimientoDesconocido.colisionaCon(this));
	}

	@Override
	public boolean colisionaCon(MovimientoTerrestre terrestre) {
		return true;
	}
	
	@Override
	public boolean colisionaCon(MovimientoConstruccion construccion) {
		return true;
	}
	
}
