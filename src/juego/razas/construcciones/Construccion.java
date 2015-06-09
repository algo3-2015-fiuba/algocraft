package juego.razas.construcciones;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;

public abstract class Construccion implements Construible, Controlable {

	protected Jugador propietario;
	protected float vida;
	protected int tiempoDeConstruccion;
	
	public Construccion() {
		
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
	
	//Por defecto todos son falsos, segun la construccion se activan las propiedades.
	@Override
	public boolean puedeAlmacenarUnidades() {
		return false;
	}
	
	@Override
	public boolean puedeCrearUnidades() {
		return false;
	}
	
	@Override
	public boolean puedeExtraer(Mineral recurso) {
		return false;
	}
	
	@Override
	public boolean puedeExtraer(GasVespeno gv) {
		return false;
	}
	
}
