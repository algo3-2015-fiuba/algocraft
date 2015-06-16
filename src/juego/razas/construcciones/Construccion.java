package juego.razas.construcciones;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.estrategias.PosicionTerrestre;
import juego.interfaces.Atacable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.estrategias.EstrategiaPosicion;
import juego.interfaces.excepciones.EdificiosNoSeMueven;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public abstract class Construccion implements Construible, Atacable, Controlable {

	protected float vida;
	protected Jugador propietario;
	protected BolsaDeCostos bolsaDeCostos;
	protected Coordenada posicion;
	protected EstrategiaPosicion estrategiaDePosicion;
	
	public Construccion() {
		super();
		this.vida = 0;
		this.propietario = null;
		this.estrategiaDePosicion = new PosicionTerrestre();
	}
	
	public void recibirAtaque(BolsaDeAtaque bolsaDeAtaque) {
		//TODO 
	}
	
	public void recibirDanio(int cantidad) {
		this.vida -= cantidad;
		
		if(this.estaMuerto()) {
			this.destruir();
		}
	}
	
	private void destruir() {
		
		//TODO que borre todas las celdas
		
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
	public void moverse(Coordenada coordenada) {
		
	}
	
	@Override
	public boolean ocupanMismoEspacio(Controlable controlable) { 
		return controlable.ocupanMismoEspacio(this.estrategiaDePosicion); 
	}
	
	@Override
	public boolean ocupanMismoEspacio(EstrategiaPosicion estrategiaDeOtro) { 
		return estrategiaDePosicion.ocupaMismoEspacioQue(estrategiaDeOtro); 
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.bolsaDeCostos.tiempoDeConstruccionRestante() == 0);
	}
	
	@Override
	public void construir(JugadorTerran jt, Coordenada coordenada)
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {}

	@Override
	public void construir(JugadorProtoss jp, Coordenada coordenada)
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {}
	
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
