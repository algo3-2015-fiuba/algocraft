package juego.magias;

import java.util.Collection;

import juego.Juego;
import juego.energia.Energia;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public abstract class Magia {

	protected int costoEnergia;
	
	protected Collection<Celda> obtenerRadioDeImpacto(Coordenada coordenadaDeterminante, int rangoX, int rangoY) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldasDisponibles(coordenadaDeterminante, rangoX, rangoY);
		
	}
	
	public boolean esPosibleLanzarla(Energia energia) {
		
		if (energia.energiaSuficiente(this.costoEnergia)) {
			energia.consumir(this.costoEnergia);
			return true;
		}
		
		return false;
	}

	public abstract void lanzar(Coordenada coordImpacto);
	
	public abstract void afectar(Unidad unidad);
	
}
