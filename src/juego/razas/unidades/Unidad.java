package juego.razas.unidades;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.Atacable;
import juego.interfaces.Atacante;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;
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

public abstract class Unidad implements Controlable, Entrenable, Atacable {
	
	protected int vision;
	protected int vida;
	protected int rangoDeMovimiento;
	protected BolsaDeCostos bolsaDeCostos;
	protected EstrategiaPosicion estrategiaDePosicion;
	protected Coordenada posicion;
	
	public Unidad() {
		super();
		this.vida = 0;
	}
	
	public int suministroUsado() {
		return this.bolsaDeCostos.suministroUsado();
	}
	
	public void recibirDanio(BolsaDeAtaque bolsaDeAtaque) {
		int danio = this.estrategiaDePosicion.danioRecibido(bolsaDeAtaque);
		
		this.vida -= danio;
		
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
	
	@Override
	public boolean ocupanMismoEspacio(Terrestre terrestre) { return estrategiaDePosicion.ocupaMismoEspacioQue(terrestre); }
	
	@Override
	public boolean ocupanMismoEspacio(Volador volador) { return estrategiaDePosicion.ocupaMismoEspacioQue(volador); }
	
	@Override
	public boolean ocupanMismoEspacio(Construible construible) { return estrategiaDePosicion.ocupaMismoEspacioQue(construible); }
	
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion, RequerimientosInvalidos {
		throw new RequerimientosInvalidos();
	}
	
	public void ubicar(Coordenada coordenada) {
		Mapa mapa = Juego.getInstance().getMapa();
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
