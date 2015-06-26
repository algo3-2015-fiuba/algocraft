package juego.magias;

import java.util.Collection;

import juego.Juego;
import juego.energia.Energia;
import juego.interfaces.excepciones.EnergiaInsuficiente;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public abstract class Magia {

	protected int costoEnergia;
	protected Jugador propietario;
	
	protected Collection<Celda> obtenerRadioDeImpacto(Coordenada coordenadaDeterminante, int rango) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoRadialDeCeldas(coordenadaDeterminante, rango);
		
	}
	
	public boolean energiaSuficiente(Energia energia) {
		return (energia.energiaSuficiente(this.costoEnergia));
	}
	
	public void consumir(Energia energia) throws EnergiaInsuficiente {
		energia.consumir(this.costoEnergia);
	}

	public abstract void activar();
	
	public abstract void afectar(Unidad unidad);

	public abstract boolean activa();
	
}
