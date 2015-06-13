package juego.razas.protoss.construcciones;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;

public class Acceso extends ConstruccionMilitar {

	public Acceso() {
		super();
		this.tiempoDeConstruccion = 8;
		this.costoMinerales = 150;
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada()) {
			this.vida += 62.5;
			this.tiempoDeConstruccion--;
			if (this.construccionFinalizada()) ((JugadorProtoss)this.propietario).activarPuertoEstelar(true);
		}
		
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		if (!jugador.mineralesSuficientes(this.costoMinerales)) throw new RecursosInsuficientes();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 2);
		Iterator<Celda> it = rangoDeCeldas.iterator();
		
		while (it.hasNext()) {
			if (!it.next().esPosibleConstruir(this)) throw new UbicacionInvalida();
		}
	
		jugador.consumirMinerales(this.costoMinerales);
			
		it = rangoDeCeldas.iterator();
			
		while (it.hasNext()) {
			it.next().ocupar(this);
		}
		
		this.propietario = jugador;
			
	}

}
