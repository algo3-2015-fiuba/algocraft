package juego.razas.construcciones.terran;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.informadores.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereBarraca;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.terran.Golliat;

public class Fabrica extends ConstruccionMilitar {

	public Fabrica() {
		super();
		this.vida = new Vida(1250);
		this.costos = new Costos(200,100,12,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(6);
	}
	
	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.costos.disminuirTiempoDeConstruccion();
			if (this.construccionFinalizada()) {
				((JugadorTerran)this.propietario).activarPuertoEstelar(true);
			}
		}
	}

	@Override
	public void construir(JugadorTerran jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		Mapa mapa = Juego.getInstance().getMapa();
				
		if (!this.costos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		if (!jugador.fabricaHabilitada()) throw new RequiereBarraca();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 3);
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
	
		this.costos.consumirRecursos(jugador);
		
		this.posicion = coordenada;
		this.propietario = jugador;
			
	}
	
	public void entrenar(Golliat golliat) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(golliat);
		}
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 2, 3);
	}
	
}
