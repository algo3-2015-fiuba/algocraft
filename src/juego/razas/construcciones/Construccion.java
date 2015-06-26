package juego.razas.construcciones;

import java.util.Collection;

import juego.informadores.Costos;
import juego.interfaces.Atacable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
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
	
	/* * * * * * * * * * * * * * * *
	 *                             *
	 *  Modificaciones de estado   *
	 *                             *
	 * * * * * * * * * * * * * * * */
	
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
	public boolean construccionFinalizada() {
		return this.costos.construccionFinalizada();
	}
	
	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.costos.disminuirTiempoDeConstruccion();		
		}	
	}
	
	//La construccion redefine este metodo segun el jugador que tiene permitido construirla.
	//este metodo deberia estar en cada jugador no en la construccion
	@Override
	public void construir(JugadorTerran jt, Coordenada coordenada)
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {}

	@Override
	public void construir(JugadorProtoss jp, Coordenada coordenada)
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {}
	
	
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
