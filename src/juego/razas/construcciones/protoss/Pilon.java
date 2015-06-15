package juego.razas.construcciones.protoss;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionHabitable;

public class Pilon extends ConstruccionHabitable {

	public Pilon() {
		super();
		this.capacidadDeHabitantes = 5;
		this.bolsaDeCostos = new BolsaDeCostos(100,0,5);
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 1);
		Iterator<Celda> it = rangoDeCeldas.iterator();
	
		try {
			while (it.hasNext()) {
				Celda celda = it.next();
				if (celda.poseeRecursos()) throw new UbicacionInvalida();
				celda.ocupar(this);
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
			this.vida += 60;
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
		} 
		
	}
	
}
