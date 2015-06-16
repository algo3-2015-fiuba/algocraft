package juego.razas.construcciones;

import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.estrategias.PosicionTerrestre;
import juego.interfaces.Atacable;
import juego.interfaces.Construible;
import juego.interfaces.estrategias.EstrategiaPosicion;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;

public abstract class Construccion implements Construible, Atacable {

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
	
	public void recibirDanio(BolsaDeAtaque bolsaDeAtaque) {
		//TODO 
	}
	
	public boolean estaMuerto() {
		return this.vida <= 0;
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
