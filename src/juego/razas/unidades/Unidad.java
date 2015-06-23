package juego.razas.unidades;

import juego.Juego;
import juego.informacion.Costos;
import juego.interfaces.Atacable;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.magias.MisilEMP;
import juego.magias.Radiacion;
import juego.magias.TormentaPsionica;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public abstract class Unidad implements Controlable, Entrenable {
	
	protected int vision;
	protected Atacable vida;
	protected int rangoDeMovimiento;
	protected int pesoTransporte;
	protected Costos costos;
	protected EstrategiaMovimiento estrategiaDeMovimiento;
	protected Coordenada posicion;
	protected Jugador propietario;
	
	public Unidad() {
		super();
		this.posicion = null;
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
	
	public float vidaActual() {
		return this.vida.vidaActual();
	}
	
	public int vision() {
		return this.vision;
	}
	
	//Para que radiacion pueda saber donde atacar
	public Coordenada coordenadas() {
		return this.posicion;
	}
	
	public Costos costos() {
		return this.costos;
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
		this.recibirAtaque(100);
	}
	
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
		this.vida.regenerar();
	}


	/* * * * * * * * * *
	 *                 *
	 * Entrenamiento   *
	 *                 *
	 * * * * * * * * * */
	
	@Override
	public void actualizarEntrenamiento() {
		if (!entrenamientoFinalizado()) {
			this.costos.disminuirTiempoDeConstruccion();
		}
	}
	
	@Override
	public boolean entrenamientoFinalizado() {
		return (this.costos.tiempoDeConstruccionRestante() == 0);
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
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		int distanciaAMover = 0;
		
		if(this.posicion != null) {
			distanciaAMover = mapa.distanciaEntreCoordenadas(this.posicion, coordFinal);
		}
		
		if(distanciaAMover <= this.rangoDeMovimiento) {
			this.estrategiaDeMovimiento.moverse(this, this.posicion, coordFinal);
			this.posicion = coordFinal;
		} else {
			throw new UbicacionInvalida();
		}
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

	public void subirACarro() {
		try {
			this.estrategiaDeMovimiento.desocupar(this.posicion, this);
		} catch (CoordenadaFueraDeRango cfdr) {}
	}


	public void bajarDeCarro(Coordenada coordActual, Coordenada coordBajar) throws UbicacionInvalida {
		this.posicion = coordActual;
		this.estrategiaDeMovimiento.moverse(this, this.posicion, coordBajar);
	}	
	
}
