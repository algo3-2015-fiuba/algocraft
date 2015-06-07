package juego.razas.unidades;

import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;

public class Unidad implements Controlable {
	
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

	@Override
	public void moverse(Coordenada coordFinal) throws CeldaOcupada,
			CoordenadaFueraDeRango, ConstruccionesNoSeMueven,
			PropietarioInvalido {
		// TODO Auto-generated method stub

	}

}
