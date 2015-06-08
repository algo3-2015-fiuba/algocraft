package juego.razas.construcciones;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.PropietarioInvalido;

public abstract class EdificioRecolector implements Construible, Recolector, Controlable {

	protected Jugador propietario;
	protected float vida;
	protected Celda celdaOcupada;
	protected int tiempoDeConstruccion;

	public EdificioRecolector() {
		super();
		this.vida = 0;
		this.propietario = Juego.getInstance().turnoDe();
		this.tiempoDeConstruccion = 0;
	}
	
	public boolean esPropietario(Jugador aComprobar) {
		return (this.propietario.equals(aComprobar));
	}

	@Override
	public void ocuparCelda(Celda celda) throws CeldaOcupada {
		celda.ocuparTierra(this);
	}

	@Override
	public void moverse(Coordenada coordFinal) throws ConstruccionesNoSeMueven,
			PropietarioInvalido {	
		
		if (!this.esPropietario(Juego.getInstance().turnoDe())) { throw new PropietarioInvalido(); }
				
		throw new ConstruccionesNoSeMueven();
	}
	
	//Ningun edificio recolector puede almacenar unidades
	@Override
	public boolean puedeAlmacenarUnidades() {
		return false;
	}

}