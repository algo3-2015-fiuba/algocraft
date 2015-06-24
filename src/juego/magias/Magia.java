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
	
	protected Collection<Celda> obtenerRadioDeImpacto(Coordenada coordenadaDeterminante, int rango) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRadialmenteRangoDeCeldasDisponibles(coordenadaDeterminante, rango);
		
	}
	
	public boolean esPosibleLanzarla(Energia energia) {
		
		if (energia.energiaSuficiente(this.costoEnergia)) {
			energia.consumir(this.costoEnergia);
			return true;
		}
		
		return false;
	}

	public void activar() {}
	
	public abstract void afectar(Unidad unidad);

	public abstract boolean activa();
	
}
