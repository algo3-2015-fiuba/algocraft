package juego.razas.unidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.informadores.Costos;
import juego.interfaces.Atacable;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.magias.Alucinacion;
import juego.magias.MisilEMP;
import juego.magias.Radiacion;
import juego.magias.TormentaPsionica;
import juego.mapa.Coordenada;

public abstract class Unidad implements Controlable, Entrenable {
	
	protected Atacable vida;
	protected Costos costos;
	protected EstrategiaMovimiento estrategiaDeMovimiento;
	protected Coordenada posicion;
	protected Jugador propietario;
	protected Collection<UnidadAlucinada> alucinaciones;
	protected int pesoTransporte;
	
	public Unidad() {
		
		super();
		this.posicion = null;
		this.alucinaciones = new ArrayList<UnidadAlucinada>();
		this.propietario = Juego.getInstance().turnoDe();
		this.pesoTransporte = 0;
		
	}
	
	
	/* * * * * * * * * * * * * 
	 *                       *
	 *  Informacion basica   *
	 *                       *
	 * * * * * * * * * * * * */
	
	public int suministrosNecesarios() {
		return this.costos.suministrosNecesarios();
	}
	
	public int pesoTransporte() {
		return this.pesoTransporte;
	}
	
	public boolean recursosSuficientes(Jugador jugador) {
		return this.costos.recursosSuficientes(jugador);
	}
	
	public float vidaActual() {
		return this.vida.vidaActual();
	}	
	
	/* * * * * * * * * * * * * * * *
	 *                             *
	 *  Modificaciones de estado   *
	 *                             *
	 * * * * * * * * * * * * * * * */
	
	public void afectadaPorMagia(MisilEMP emp) {
		this.vida.deshabilitar();
	}
	
	public void afectadaPorMagia(Radiacion radiacion) {
		
		this.vida.afectadoPorRadiacion();
		
		if (this.vida.vidaAgotada()) {
			this.morir();
			radiacion.fallecido(this);
		}	
		
	}
	
	public void afectadaPorMagia(TormentaPsionica tormenta) {
		this.recibirAtaque(tormenta.getDanio());
	}
	
	public void afectadaPorMagia(Alucinacion alucinacion) {
		
		for (int i = 0; i < 2; i++) {
			UnidadAlucinada alucinada = new UnidadAlucinada(this);
			this.alucinaciones.add(alucinada);
			this.propietario.asignarUnidad(alucinada);
		}
		
	}	
	
	@Override
	public void recibirAtaque(float danio) {
		this.vida.daniar(danio);
		if (this.vida.vidaAgotada()) {
			this.morir();
		}
	}
	
	protected void morir() {
		this.propietario.fallecida(this);
		this.estrategiaDeMovimiento.desocupar(this);
		Iterator<UnidadAlucinada> it = this.alucinaciones.iterator();
		while (it.hasNext()) {
			it.next().originalMuerto();
		}
	}
	
	public void actualizar() { 
		this.vida.regenerar();
	}


	/* * * * * * * * * *
	 *                 *
	 * Entrenamiento   *
	 *                 *
	 * * * * * * * * * */
	
	@Override
	public void actualizarEntrenamiento() {
		if (!this.entrenamientoFinalizado()) {
			this.costos.disminuirTiempoDeConstruccion();
		}
	}
	
	@Override
	public boolean entrenamientoFinalizado() {
		return (this.costos.construccionFinalizada());
	}
	
	public void asignarPropietario(Jugador jugador) {
		this.propietario = jugador;
	}
	
	
	/* * * * * * * * *
	 *               *
	 * Movimientos   *
 	 *               *
	 * * * * * * * * */
	
	@Override
	public int getVision() {
		return this.estrategiaDeMovimiento.getVision();
	}
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		
		this.estrategiaDeMovimiento.moverse(this, coordFinal);
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

	public void subirACarro() {
		this.estrategiaDeMovimiento.desocupar(this);
	}


	public void bajarDeCarro(Coordenada coordActual, Coordenada coordBajar) throws UbicacionInvalida {
		this.estrategiaDeMovimiento.moverse(this, coordBajar);
		this.posicion = coordActual;
	}
	
}
