package juego.razas.construcciones.protoss;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.protoss.Dragon;
import juego.razas.unidades.protoss.Zealot;
import juego.decoradores.*;

public class Acceso extends ConstruccionMilitar {

	public Acceso() {
		super();
		this.vida = new Escudo(new Vida(500), 500);
		this.bolsaDeCostos = new BolsaDeCostos(150,0,8,0);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada()) {
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
			if (this.construccionFinalizada()) ((JugadorProtoss)this.propietario).activarPuertoEstelar(true);
		}
		
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 2);
		Iterator<Celda> it = rangoDeCeldas.iterator();
		
		try {
			while (it.hasNext()) {
				Celda celda = it.next();
				if ((celda.poseeRecursos()) || (!celda.puedeConstruir(this))) throw new UbicacionInvalida();
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
	
	public void entrenar(Zealot zealot) throws RecursosInsuficientes, SobrePoblacion {
		zealot.iniciarEntrenamiento();
		this.entrenamientos.add(zealot);
	}

	public void entrenar(Dragon dragon) throws RecursosInsuficientes, SobrePoblacion {
		dragon.iniciarEntrenamiento();
		this.entrenamientos.add(dragon);		
	}

}
