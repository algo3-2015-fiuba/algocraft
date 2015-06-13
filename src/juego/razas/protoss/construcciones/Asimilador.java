package juego.razas.protoss.construcciones;

import juego.Juego;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.GasVespeno;

public class Asimilador extends ConstruccionRecolectora {
	
	private GasVespeno nodoGasVespeno;

	public Asimilador() {
		super();
		this.tiempoDeConstruccion = 6;
		this.costoMinerales = 100;
	}
	
	@Override
	public boolean puedeExtraer(GasVespeno gv) { return true; }

	@Override
	public void recolectar() {
		if ((this.construccionFinalizada())  && (!this.nodoGasVespeno.estaAgotado())){
			int extraidos = this.nodoGasVespeno.extraer();
			this.propietario.recolectarGasVespeno(extraidos);		
		}
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Celda celda;
		
		celda = mapa.obtenerCelda(coordenada);
		
		if (celda.esPosibleConstruir(this)) {
		
			jugador.consumirMinerales(this.costoMinerales);
	
			this.propietario = jugador;
		
			this.nodoGasVespeno = (GasVespeno) celda.getRecurso();

			celda.ocupar(this);
	
		} else {
			throw new UbicacionInvalida();
		}
			
	}
	
	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 75; 
			this.tiempoDeConstruccion--;
		}
	}

}
