package juego.razas.terran.unidades;

import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.razas.unidades.Unidad;

public class Marine extends Unidad {

	public Marine(Jugador propietario) {
		super(propietario);
	}

	@Override
	public void moverse(Coordenada coordFinal) throws CeldaOcupada,
			CoordenadaFueraDeRango, ConstruccionesNoSeMueven,
			PropietarioInvalido {
		// TODO Auto-generated method stub
	}

}
