package juego.razas.construcciones.terran;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionHabitable;

public class DepositoSuministro extends ConstruccionHabitable {

	public DepositoSuministro() {
		super();
		this.capacidadDeHabitantes = 5;
		this.bolsaDeCostos = new BolsaDeCostos(100,0,6,0);
	}
	
	@Override
	public void construir(JugadorTerran jugador, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 1);
		Iterator<Celda> it = rangoDeCeldas.iterator();
	
		try {
			while (it.hasNext()) {
				Celda celda = it.next();
				if (celda.poseeRecursos()) throw new UbicacionInvalida();
				celda.ocuparConstruccion(this);
			}
		} catch (UbicacionInvalida ui) {
			it = rangoDeCeldas.iterator();
			while (it.hasNext()) {
				it.next().desocupar(this);
			}
			throw new UbicacionInvalida();
		}
	
		this.bolsaDeCostos.consumirRecursos(jugador);
		
		this.posicion = coordenada;
		this.propietario = jugador;
			
	}

	@Override
	public void actualizarConstruccion() {
		
		if (!this.construccionFinalizada()) {
			this.vida += 83.33;
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
		} 
		
	}
	
}
