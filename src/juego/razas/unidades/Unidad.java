package juego.razas.unidades;

import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public abstract class Unidad implements Controlable {
	
	protected Jugador propietario;
	protected float vida;
	protected boolean esVolador;

	@Override
	public boolean esPropietario(Jugador jugador) {
		return (this.propietario.equals(jugador));
	}

	@Override
	public void ocuparCelda(Celda celda) throws CeldaOcupada,
			CoordenadaFueraDeRango {
		if(this.esVolador) {
			celda.ocuparAire(this);
		} else {
			celda.ocuparTierra(this);
		}
	}

}
