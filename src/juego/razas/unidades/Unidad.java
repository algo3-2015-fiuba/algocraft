package juego.razas.unidades;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.Atacable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;

public abstract class Unidad implements Controlable, Entrenable, Atacable {
	
	protected int vision;
	protected BolsaDeCostos bolsaDeCostos;
	protected int vida;
	protected int suministro;
	protected int tiempoDeConstruccion;
	protected EstrategiaMovimiento estrategiaDeMovimiento;
	protected Coordenada posicion;
	
	public Unidad() {
		super();
		this.vida = 0;
	}
	
	public int getSuministro() { return this.suministro; }
	
	@Override
	public boolean entrenamientoFinalizado() {
		return (this.bolsaDeCostos.tiempoDeConstruccionRestante() == 0);
	}
	
	
	
	@Override
	public boolean ocupanMismoEspacio(Terrestre terrestre) { return estrategiaDeMovimiento.ocupaMismoEspacioQue(terrestre); }
	
	@Override
	public boolean ocupanMismoEspacio(Volador volador) { return estrategiaDeMovimiento.ocupaMismoEspacioQue(volador); }
	
	@Override
	public boolean ocupanMismoEspacio(Construible construible) { return estrategiaDeMovimiento.ocupaMismoEspacioQue(construible); }
	
	public void ubicar(Coordenada coordenada) {
		Mapa mapa = Juego.getInstance().getMapa();
		mapa.ubicarEnCeldaDisponible(coordenada,this);	
	}
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		this.estrategiaDeMovimiento.moverse(this, this.posicion, coordFinal);
	}
	

	
	public void iniciarEntrenamiento() throws RecursosInsuficientes, SobrePoblacion {
		
		Jugador jugador = Juego.getInstance().turnoDe();
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) {	throw new RecursosInsuficientes(); }
			
		if (!jugador.suministrosSuficientes(this.suministro)) {	throw new SobrePoblacion();	}
		
		bolsaDeCostos.consumirRecursos(jugador);
		
	}
	
}
