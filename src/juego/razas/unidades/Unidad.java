package juego.razas.unidades;

import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.Atacable;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.estrategias.EstrategiaPosicion;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;

public class Unidad implements Controlable, Entrenable, Atacable {
	
	protected int vision;
	protected int vida;
	protected int rangoDeMovimiento;
	protected boolean irradiado = false;
	protected BolsaDeCostos bolsaDeCostos;
	protected EstrategiaPosicion estrategiaDePosicion;
	protected Coordenada posicion;
	
	public static final int DANIO_RADIACION = 5;
	
	public Unidad() {
		super();
		this.vida = 0;
	}
	
	public int vida() {
		return this.vida;
	}
	
	public int suministroUsado() {
		return this.bolsaDeCostos.suministroUsado();
	}
	
	/* 
	 * ==========
	 * Ataques
	 * ==========
	 */
	
	public void recibirAtaque(BolsaDeAtaque bolsaDeAtaque) {
		this.recibirDanio(this.estrategiaDePosicion.danioRecibido(bolsaDeAtaque));
	}
	
	public void recibirDanio(int cantidad) {
		this.vida -= cantidad;
		
		if(this.estaMuerto()) {
			this.destruir();
		}
	}
	
	private void destruir() {
		Mapa mapa = Juego.getInstance().getMapa();
		
		try {
			mapa.obtenerCelda(this.posicion).desocupar(this);
		} catch (CoordenadaFueraDeRango e) {
			// TODO No debería nunca una unidad estar fuera de rango
			e.printStackTrace();
		}
	}

	public boolean estaMuerto() {
		return this.vida <= 0;
	}
	
	/* 
	 * ==========
	 * Magias
	 * ==========
	 */
	
	public void recibirEMP() {
		//No hace nada por default
	}
	
	public void irradiarse() {
		this.irradiado = true;
	}
	
	public void sufrirRadiacion() {
		if(this.irradiado) {
			Mapa mapa = Juego.getInstance().getMapa();
			Iterator<Unidad> unidadesCercanas = mapa.unidadesACiertaDistanciaDe(this.posicion, 1).iterator();
			
			while(unidadesCercanas.hasNext()) {
				Unidad victima = unidadesCercanas.next();
				
				victima.recibirDanio(DANIO_RADIACION);
			}
		}
	}
	
	
	/* 
	 * ==========
	 * Entrenamientos
	 * ==========
	 */
	

	@Override
	public void iniciarEntrenamiento() throws RecursosInsuficientes, SobrePoblacion {
		Jugador jugador = Juego.getInstance().turnoDe();
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) {	throw new RecursosInsuficientes(); }
		if (!jugador.suministrosSuficientes(this.bolsaDeCostos.suministroUsado())) { throw new SobrePoblacion(); }
		
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
	
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion, RequerimientosInvalidos {
		throw new RequerimientosInvalidos();
	}
	
	/* 
	 * ==========
	 * Posiciones
	 * ==========
	 */
	
	@Override
	public boolean ocupanMismoEspacio(Controlable controlable) { 
		return controlable.ocupanMismoEspacio(this.estrategiaDePosicion); 
	}
	
	@Override
	public boolean ocupanMismoEspacio(EstrategiaPosicion estrategiaDeOtro) { 
		return estrategiaDePosicion.ocupaMismoEspacioQue(estrategiaDeOtro); 
	}
	
	public void ubicar(Coordenada coordenada) {
		Mapa mapa = Juego.getInstance().getMapa();
		this.posicion = coordenada;
		mapa.ubicarEnCeldaDisponible(coordenada,this);	
	}
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		if(this.puedeMoverseHasta(coordFinal)) {
			this.estrategiaDePosicion.moverse(this, this.posicion, coordFinal);
		} else {
			throw new UbicacionInvalida();
		}
	}
	
	private boolean puedeMoverseHasta(Coordenada coordFinal) {
		
		if (this.posicion == null) {
			return true;
		} else {
			int distancia = Mapa.distanciaEntreCoordenadas(this.posicion, coordFinal);		
			return distancia <= this.rangoDeMovimiento;
		}
	}
}
