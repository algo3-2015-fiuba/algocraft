package juego.razas.construcciones.terran;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.decoradores.Vida;
import juego.informacion.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.terran.Marine;

public class Barraca extends ConstruccionMilitar {

	public Barraca() {
		super();
		this.vida = new Vida(1000);
		this.bolsaDeCostos = new Costos(150,0,12,0);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
			if (this.construccionFinalizada()) {
				((JugadorTerran)this.propietario).activarFabrica(true);
			}
		}
	}

	@Override
	public void construir(JugadorTerran jugador, Coordenada coordenada) 
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
	
	public void entrenar(Marine marine) throws RecursosInsuficientes, SobrePoblacion {
		marine.iniciarEntrenamiento();
		this.entrenamientos.add(marine);
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 2, 2);
	}
	
}
