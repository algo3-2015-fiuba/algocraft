package juego.razas.construcciones;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public class ConstruccionBase extends Construccion {

	public ConstruccionBase(Jugador propietario, Coordenada posicion) {
		
		super();
		this.propietario = propietario;
		this.vida = new Vida(1000);
		this.costos = new Costos(150,100,10,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(3);
		this.posicion = posicion;
		
	}
	
	@Override
	protected void morir() {
		this.propietario.baseDestruida(this);
		this.estrategiaDeMovimiento.desocupar(this);
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 1, 1);
	}

}
