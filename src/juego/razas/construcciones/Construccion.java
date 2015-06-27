package juego.razas.construcciones;

import java.util.Collection;
import java.util.Iterator;

import juego.informadores.Costos;
import juego.interfaces.Atacable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;


public abstract class Construccion implements Construible, Controlable {

	protected Atacable vida;
	protected Jugador propietario;
	protected Costos costos;
	protected Coordenada posicion;
	protected EstrategiaMovimiento estrategiaDeMovimiento;
	
	public Construccion() {
		
		super();
		this.propietario = null;
		
	}
	
	/* * * * * * * * * * * * * * *
	 *                           *
	 *  Informacion Basica       *
	 *                           *
	 * * * * * * * * * * * * * * */
	
	public boolean recursosSuficientes(Jugador jugador) {
		return this.costos.recursosSuficientes(jugador);
	}
	
	public void consumirRecursos(Jugador jugador) throws RecursosInsuficientes {
		this.costos.consumirRecursos(jugador);
	}
	/* * * * * * * * * * * * * * * *
	 *                             *
	 *  Modificaciones de estado   *
	 *                             *
	 * * * * * * * * * * * * * * * */
	
	public void setPropietario(Jugador jugador) {
		this.propietario = jugador;
	}
	
	@Override
	public void recibirAtaque(float danio) {
		this.vida.daniar(danio);
		if (this.vida.vidaAgotada()) {
			this.morir();
		}
	}
	
	private void morir() {
		this.propietario.fallecida(this);
		this.estrategiaDeMovimiento.desocupar(this);
	}
	
	
	/* * * * * * * * * * * * * * * * * * * * 
	 *                                     *
	 *  Espacio que ocupa una construccion * 
	 *                                     *
	 * * * * * * * * * * * * * * * * * * * */ 
	
	public abstract Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango;
	
	@Override
	public int getVision() {
		return this.estrategiaDeMovimiento.getVision();
	}
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		this.estrategiaDeMovimiento.moverse(this, coordFinal);
	}
	
	@Override
	public boolean colisionaCon(Controlable controlable) { 
		return controlable.colisionaCon(this.estrategiaDeMovimiento); 
	}
	
	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) { 
		return this.estrategiaDeMovimiento.colisionaCon(movimientoDesconocido); 
	}
	
	
	/* * * * * * * * * * * * * * * * * 
	 *                               *
	 *  Estados de una construccion *
	 *                               *
	 * * * * * * * * * * * * * * * * */
	
	@Override
	public void posicionar(Coordenada posicion) throws UbicacionInvalida {
		
		this.posicion = posicion;
		
		Collection<Celda> celdas = this.obtenerRangoDeOcupacion();		
		
		Iterator<Celda> itCeldas = celdas.iterator();
		
		try {
			while (itCeldas.hasNext()) {
				Celda celda = itCeldas.next();
				if (!celda.puedeConstruir(this)) throw new UbicacionInvalida();
				celda.ocupar(this);
			}
		} catch (UbicacionInvalida ui) {
			itCeldas = celdas.iterator();
			while (itCeldas.hasNext()) {
				itCeldas.next().desocupar(this);
			}
			throw new UbicacionInvalida();
		}
		
	}
	
	@Override
	public boolean construccionFinalizada() {
		return this.costos.construccionFinalizada();
	}
	
	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.costos.disminuirTiempoDeConstruccion();		
		}	
	}	
	
	/* * * * * * * * * * * * * * * * * * * * *
	 *                                       * 
	 * Caracteristicas de las construcciones *
	 *                                       * 
	 * * * * * * * * * * * * * * * * * * * * */
	
	//Por defecto todos son falsos, segun la construccion se activan las propiedades.
	@Override
	public boolean puedeHospedarUnidades() {
		return false;
	}
	
	@Override
	public boolean puedeEntrenarUnidades() {
		return false;
	}
	
	@Override
	public boolean puedeExtraerRecursos() {
		return false;
	}
	
}
