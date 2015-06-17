package juego.razas.construcciones.protoss;

import java.util.Collection;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.Mineral;

public class NexoMineral extends ConstruccionRecolectora {
	
	private Mineral nodoMineral;
	
	public NexoMineral() {
		super();
		this.vida = new Escudo(new Vida(250), 250);
		this.bolsaDeCostos = new BolsaDeCostos(50,0,4,0);
	}
	
	@Override
	public boolean puedeExtraer(Mineral m) { return true; }
	
	@Override
	public void recolectar() {
		if ((this.construccionFinalizada()) && (!this.nodoMineral.estaAgotado())) {
			int extraidos = this.nodoMineral.extraer();
			this.propietario.bolsaDeRecursos().recolectarMinerales(extraidos);		
		}
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Celda celda;
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		celda = mapa.obtenerCelda(coordenada);
		
		if ((!celda.poseeRecursos()) || (!celda.puedeConstruir(this))) throw new UbicacionInvalida();
		
		if (!celda.getRecurso().puedeRecolectar(this)) throw new UbicacionInvalida();
		
		celda.ocupar(this);
		
		this.bolsaDeCostos.consumirRecursos(jugador);
	
		this.posicion = coordenada;
		this.propietario = jugador;
		
		this.nodoMineral = (Mineral) celda.getRecurso();
		
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 1, 1);
	}
	
}