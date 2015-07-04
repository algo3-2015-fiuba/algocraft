package juego.estrategias;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;

public abstract class MovimientoUnidad extends EstrategiaMovimiento {

	protected int rangoDeMovimiento;
	
	@Override
	public void descubrirMapa(Jugador propietario, Controlable controlable) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada posicion = mapa.obtenerUbicacion((Unidad) controlable);
		
		Collection<Celda> descubierto = mapa.obtenerRangoRadialDeCeldas(posicion, this.vision);
		Iterator<Celda> itDesc = descubierto.iterator();
		while (itDesc.hasNext()) {
			itDesc.next().agregarObservador(controlable);
		}
		
		propietario.mapaDescubierto(descubierto);
		
	}
	
	@Override
	public boolean distanciaAlcanzable(int distanciaAMover) {
		return (distanciaAMover <= this.rangoDeMovimiento);
	}
	
	@Override
	public void moverse(Jugador controlador, Controlable controlable, Coordenada coordFinal) throws UbicacionInvalida {
		
		Unidad unidad = (Unidad) controlable;

		Mapa mapa = Juego.getInstance().getMapa();

		Coordenada coordInicial = mapa.obtenerUbicacion(unidad);
				
		if (coordInicial != null) {
			
			controlador.visionPerdida(controlable);
			mapa.obtenerCelda(coordInicial).desocupar(unidad);
		
		}
		
		mapa.obtenerCelda(coordFinal).ocupar(unidad);
			
		this.descubrirMapa(controlador, controlable);
				
	}
	
	@Override
	public void desocupar(Controlable controlable) {
		
		try {
			
			Mapa mapa = Juego.getInstance().getMapa();
			Coordenada coordenada = mapa.obtenerUbicacion((Unidad)controlable);
			if (coordenada != null) mapa.obtenerCelda(coordenada).desocupar((Unidad)controlable);
			
		} catch (CoordenadaFueraDeRango cfdr) {}
		
	}
	
}
