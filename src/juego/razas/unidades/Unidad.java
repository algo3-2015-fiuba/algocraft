package juego.razas.unidades;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.Atacable;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public abstract class Unidad implements Controlable, Entrenable {
	
	protected int vision;
	protected Atacable vida;
	protected int rangoDeMovimiento;
	protected BolsaDeCostos bolsaDeCostos;
	protected EstrategiaMovimiento estrategiaDeMovimiento;
	protected Coordenada posicion;
	protected Jugador propietario;
	
	public Unidad() {
		super();
		this.posicion = null;
	}
	
	
	/* * * * * * * * * * * * * 
	 *                       *
	 *  Informacion basica   *
	 *                       *
	 * * * * * * * * * * * * */
	
	public int suministrosNecesarios() {
		return this.bolsaDeCostos.suministrosNecesarios();
	}
	
	
	/* * * * * * * * * * * * * * * *
	 *                             *
	 *  Modificaciones de estado   *
	 *                             *
	 * * * * * * * * * * * * * * * */
	
	public void recibirAtaque(int danio) {
		this.vida.daniar(danio);
		if (this.vida.vidaAgotada()) {
			this.morir();
		}
	}
	
	private void morir() {
		this.propietario.fallecida(this);
		try {
			this.estrategiaDeMovimiento.desocupar(this.posicion, this);
		} catch (CoordenadaFueraDeRango cfdr) {
			//No deberia suceder nunca esto.
		}
	}
	
	public void actualizar() { 
		//Por default no se actualiza al finalizar un turno, varia segun la unidad
	}
	
	
	/* * * * * * * * * *
	 *                 *
	 * Entrenamiento   *
	 *                 *
	 * * * * * * * * * */
	
	@Override
	public void iniciarEntrenamiento() throws RecursosInsuficientes, SobrePoblacion {
		
		Jugador jugador = Juego.getInstance().turnoDe();
		this.propietario = jugador;
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) {	throw new RecursosInsuficientes(); }
		if (!jugador.suministrosSuficientes(this.bolsaDeCostos.suministrosNecesarios())) { throw new SobrePoblacion(); }
		
		bolsaDeCostos.consumirRecursos(jugador);
	}
	
	@Override
	public void actualizarEntrenamiento() {
		if (!entrenamientoFinalizado()) {
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
		}
	}
	
	@Override
	public boolean entrenamientoFinalizado() {
		return (this.bolsaDeCostos.tiempoDeConstruccionRestante() == 0);
	}
	
	
	/* * * * * * * * *
	 *               *
	 * Movimientos   *
 	 *               *
	 * * * * * * * * */
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		this.estrategiaDeMovimiento.moverse(this, this.posicion, coordFinal);
		this.posicion = coordFinal;
	}
	
	@Override
	public boolean colisionaCon(Controlable controlable) { 
		return controlable.colisionaCon(this.estrategiaDeMovimiento); 
	}
	
	@Override
	public boolean colisionaCon(EstrategiaMovimiento estrategiaDeOtro) { 
		return estrategiaDeMovimiento.colisionaCon(estrategiaDeOtro); 
	}

	public boolean puedeMoverse(Coordenada coordFinal) {
		
		if (this.posicion == null) return true;
		
		Mapa mapa = Juego.getInstance().getMapa();
		int distancia = mapa.distanciaEntreCoordenadas(this.posicion, coordFinal);
		
		return (distancia <= this.rangoDeMovimiento);
		
	}
	
}
