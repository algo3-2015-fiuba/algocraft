package juego.razas.construcciones;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public class ConstruccionBase extends Construccion {

	public ConstruccionBase(Jugador propietario, Coordenada posicion) throws UbicacionInvalida {
		
		super();
		this.propietario = propietario;
		this.vida = new Vida(1000);
		this.costos = new Costos(150,100,10,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(10);
		this.posicion = posicion;
		
	}
	
	public Coordenada getPosicion() {
		return this.posicion;
	}
	
	@Override
	protected void morir() {
		this.propietario.baseDestruida(this);
		this.estrategiaDeMovimiento.desocupar(this);
	}
	
	public void inicializarMapa() {
		this.estrategiaDeMovimiento.descubrirMapa(this.propietario, this);
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 1, 1);
	}

}
