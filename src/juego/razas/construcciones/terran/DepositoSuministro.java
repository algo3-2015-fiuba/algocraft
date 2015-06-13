package juego.razas.construcciones.terran;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
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
		this.tiempoDeConstruccion = 6;
		this.costoMinerales = 100;
	}
	
	@Override
	public void construir(JugadorTerran jugador, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		if (!jugador.bolsaDeRecursos().mineralesSuficientes(this.costoMinerales)) throw new RecursosInsuficientes();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 1);
		Iterator<Celda> it = rangoDeCeldas.iterator();
	
		while (it.hasNext()) {
			if (!it.next().esPosibleConstruir(this)) throw new UbicacionInvalida();
		}
	
		jugador.bolsaDeRecursos().consumirMinerales(this.costoMinerales);
		
		it = rangoDeCeldas.iterator();
			
		while (it.hasNext()) {
			it.next().ocupar(this);
		}
		
		this.propietario = jugador;
			
	}

	@Override
	public void actualizarConstruccion() {
		
		if (!this.construccionFinalizada()) {
			this.vida += 83.33;
			this.tiempoDeConstruccion--;
		} 
		
	}
	
}
