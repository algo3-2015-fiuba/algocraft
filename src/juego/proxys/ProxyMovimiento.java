package juego.proxys;

import juego.Juego;
import juego.interfaces.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public class ProxyMovimiento {

	protected EstrategiaMovimiento estrategiaMovimiento;
	
	public ProxyMovimiento(EstrategiaMovimiento estrategiaMovimiento) {
		
		super();
		this.estrategiaMovimiento = estrategiaMovimiento;
		
	}
	
	public boolean destinoValido(Unidad unidad, Coordenada coordFinal) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada ubicacionUnidad = mapa.obtenerUbicacion(unidad);
		
		if (ubicacionUnidad == null) return true;
		
		try {
			
			if (!this.estrategiaMovimiento.puedeOcupar(unidad, mapa.obtenerCelda(coordFinal))) return false;
			
		} catch (UbicacionInvalida ui) { return false; }
		
		int distanciaAMover = mapa.distanciaEntreCoordenadas(ubicacionUnidad, coordFinal);
		
		return this.estrategiaMovimiento.distanciaAlcanzable(distanciaAMover);
		
	}

	public void moverse(Jugador propietario, Unidad unidad,	Coordenada coordFinal) throws UbicacionInvalida {
		
		if (!this.destinoValido(unidad, coordFinal)) throw new UbicacionInvalida();
		
		this.estrategiaMovimiento.moverse(propietario, unidad, coordFinal);
		
	}
	
}
