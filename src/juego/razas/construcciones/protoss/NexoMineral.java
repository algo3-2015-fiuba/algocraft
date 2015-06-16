package juego.razas.construcciones.protoss;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.Mineral;

public class NexoMineral extends ConstruccionRecolectora {
	
	private Mineral nodoMineral;
	
	public NexoMineral() {
		super();
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
		
		if (!celda.poseeRecursos()) throw new UbicacionInvalida();
		
		if (!celda.getRecurso().puedeRecolectar(this)) throw new UbicacionInvalida();
		
		celda.ocuparConstruccion(this);
		
		this.bolsaDeCostos.consumirRecursos(jugador);
	
		this.posicion = coordenada;
		this.propietario = jugador;
		
		this.nodoMineral = (Mineral) celda.getRecurso();
		
	}
	
	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 62.5; 
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
		}
	}
	
}