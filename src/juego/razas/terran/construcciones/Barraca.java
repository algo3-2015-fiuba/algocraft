package juego.razas.terran.construcciones;

import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;

/*
 * TODO
 * Crear superclase para edificios para no repetir estos metodos
 * Crear crearMarine(), que acepte una coordenada y que haga un CommandEntrenarUnidad
 */

public class Barraca implements Controlable, Construible {

	@Override
	public boolean esPropietario(Jugador jugador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ocuparCelda(Celda celda) throws CeldaOcupada,
			CoordenadaFueraDeRango {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverse(Coordenada coordFinal) throws CeldaOcupada,
			CoordenadaFueraDeRango, ConstruccionesNoSeMueven,
			PropietarioInvalido {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean construccionFinalizada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actualizarConstruccion() {
		// TODO Auto-generated method stub
		
	}

}
