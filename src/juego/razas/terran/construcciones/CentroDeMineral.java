package juego.razas.terran.construcciones;

import juego.Juego;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.Mineral;

public class CentroDeMineral extends ConstruccionRecolectora {
	
	private Mineral nodoMineral;
	
	public CentroDeMineral() {
		super();
		this.tiempoDeConstruccion = 4;
		this.costoMinerales = 50;
	}
	
	@Override
	public boolean puedeExtraer(Mineral m) { return true; }
	
	@Override
	public void recolectar() {
		if ((this.construccionFinalizada()) && (!this.nodoMineral.estaAgotado())) {
			int extraidos = this.nodoMineral.extraer();
			this.propietario.recolectarMinerales(extraidos);		
		}
	}
	
	@Override
	public void construir(JugadorTerran jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Celda celda;
		
		if (!jugador.mineralesSuficientes(this.costoMinerales)) throw new RecursosInsuficientes();
		
		celda = mapa.obtenerCelda(coordenada);
		
		if (celda.esPosibleConstruir(this)) {
		
			jugador.consumirMinerales(this.costoMinerales);
	
			this.propietario = jugador;
		
			this.nodoMineral = (Mineral) celda.getRecurso();
			
			celda.ocupar(this);
	
		} else {
			throw new UbicacionInvalida();
		}
			
	}
	
	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 125; 
			this.tiempoDeConstruccion--;
		}
	}
	
}
