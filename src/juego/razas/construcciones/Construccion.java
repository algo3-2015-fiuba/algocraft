package juego.razas.construcciones;

import juego.bolsas.BolsaDeCostos;
import juego.estrategias.MovimientoTerrestre;
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
import juego.mapa.Coordenada;


public abstract class Construccion implements Construible, Controlable {

	protected Atacable vida;
	protected Jugador propietario;
	protected BolsaDeCostos bolsaDeCostos;
	protected Coordenada posicion;
	protected EstrategiaMovimiento estrategiaDeMovimiento;
	
	public Construccion() {
		
		super();
		this.propietario = null;
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}
	
	/* * * * * * * * * * * * * * * * * * * * 
	 *                                     *
	 *  Espacio que ocupa una construccion * 
	 *                                     *
	 * * * * * * * * * * * * * * * * * * * */ 
	
	@Override
	public void moverse(Coordenada coordInicial, Coordenada coordFinal) {
		//Las construcciones no pueden moverse
	}
	
	@Override
	public boolean colisionaCon(Controlable controlable) { 
		return controlable.colisionaCon(this.estrategiaDeMovimiento); 
	}
	
	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) { 
		return estrategiaDeMovimiento.colisionaCon(movimientoDesconocido); 
	}
	
	
	/* * * * * * * * * * * * * * * * * 
	 *                               *
	 *  Estados de una construccion *
	 *                               *
	 * * * * * * * * * * * * * * * * */
	
	@Override
	public boolean construccionFinalizada() {
		return (this.bolsaDeCostos.tiempoDeConstruccionRestante() == 0);
	}
	
	//La construccion redefine este metodo segun el jugador que tiene permitido construirla.
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
