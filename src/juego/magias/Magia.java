package juego.magias;

import java.util.Collection;

import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public abstract class Magia {

	protected Collection<Celda> obtenerRadioDeImpacto(Coordenada coordenadaDeterminante, int rangoX, int rangoY) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldasDisponibles(coordenadaDeterminante, rangoX, rangoY);
		
	}

	public abstract void afectar(Unidad unidad);
	
}
