package juego.razas.unidades;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.Atacable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;

public abstract class Unidad implements Controlable, Entrenable, Atacable {
	
	protected int vision;
	protected BolsaDeCostos bolsaDeCostos;
	protected int vida;
	protected int suministro;
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
	
	//Por defecto no ocupan el mismo espacio, segun la unidad se especifican sus caracteristicas.
	@Override
	public boolean ocupanMismoEspacio(Terrestre terrestre) { return (this instanceof Terrestre); }
	
	@Override
	public boolean ocupanMismoEspacio(Volador volador) { return (this instanceof Volador); }
	
	@Override
	public boolean ocupanMismoEspacio(Construible construible) { return (this instanceof Terrestre); }
	
	public void ubicar(Coordenada coordenada) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		mapa.ubicarEnCeldaDisponible(coordenada,this);	
		
	}
	
}
