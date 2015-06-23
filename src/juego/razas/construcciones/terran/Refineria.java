package juego.razas.construcciones.terran;

import java.util.Collection;

import juego.Juego;
import juego.decoradores.Vida;
import juego.informacion.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.GasVespeno;

public class Refineria extends ConstruccionRecolectora {
	
	private GasVespeno nodoGasVespeno;

	public Refineria() {
		super();
		this.vida = new Vida(750);
		this.bolsaDeCostos = new Costos(100,0,6,0);
	}
	
	@Override
	public boolean puedeExtraer(GasVespeno gv) { return true; }

	@Override
	public void recolectar() {
		if ((this.construccionFinalizada())  && (!this.nodoGasVespeno.estaAgotado())){
			int extraidos = this.nodoGasVespeno.extraer();
			this.propietario.bolsaDeRecursos().recolectarGasVespeno(extraidos);		
		}
	}
	
	@Override
	public void construir(JugadorTerran jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Celda celda;
		
		celda = mapa.obtenerCelda(coordenada);
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		if ((!celda.poseeRecursos()) || (!celda.puedeConstruir(this))) throw new UbicacionInvalida();
		
		if (!celda.getRecurso().puedeRecolectar(this)) throw new UbicacionInvalida();
		
		celda.ocupar(this);
		
		this.bolsaDeCostos.consumirRecursos(jugador);
		
		this.posicion = coordenada;
		this.propietario = jugador;
		
		this.nodoGasVespeno = (GasVespeno) celda.getRecurso();
			
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 1, 1);
	}

}