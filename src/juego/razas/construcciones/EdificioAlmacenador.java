package juego.razas.construcciones;

import java.util.ArrayList;
import java.util.Collection;

import juego.Juego;
import juego.interfaces.Almacenable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.interfaces.excepciones.EdificioVacio;
import juego.interfaces.excepciones.UnidadNoSeEncuentraEnEdificio;
import juego.interfaces.excepciones.UnidadYaSeEncuentraEnEdificio;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.razas.unidades.Unidad;

public abstract class EdificioAlmacenador implements Construible, Controlable, Almacenable {

	protected Jugador propietario;
	protected float vida;
	protected Celda celdaOcupada;
	protected int tiempoDeConstruccion, capacidadDePoblacion;
	protected Collection<Unidad> unidadesAlmacenadas;

	public EdificioAlmacenador() {
		super();
		this.vida = 0;
		this.propietario = Juego.getInstance().turnoDe();
		this.tiempoDeConstruccion = 0;
		this.unidadesAlmacenadas = new ArrayList<Unidad>();
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
	
	@Override
	public boolean tieneCapacidadDisponible() {
		return (this.capacidadDePoblacion > this.unidadesAlmacenadas.size());
	}
	
	@Override
	public int cantidadUnidadesAlmacenadas() {
		return this.unidadesAlmacenadas.size();
	}
	
	@Override
	public void desalojar(Unidad unidad) throws EdificioVacio, UnidadNoSeEncuentraEnEdificio {
		if (this.tieneCapacidadDisponible()) {
			if (!this.unidadesAlmacenadas.remove(unidad)) throw new UnidadNoSeEncuentraEnEdificio();
		} else {
			throw new EdificioVacio();
		}
	}
	
	@Override
	public void ocupar(Unidad unidad) throws UnidadYaSeEncuentraEnEdificio {
		if (this.tieneCapacidadDisponible()) {
			if (!this.unidadesAlmacenadas.contains(unidad)) {
				this.unidadesAlmacenadas.add(unidad);
			} else {
				throw new UnidadYaSeEncuentraEnEdificio();
			}
		}
	}
	
	@Override
	public boolean puedeAlmacenarUnidades() {
		return true;
	}
	
}
