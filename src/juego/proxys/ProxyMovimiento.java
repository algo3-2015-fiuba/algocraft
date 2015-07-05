package juego.proxys;

import juego.Juego;
import juego.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.excepciones.AccionInvalida;
import juego.razas.unidades.excepciones.NoSePuedenMoverUnidadesEnemigas;

public class ProxyMovimiento {

	protected EstrategiaMovimiento estrategiaMovimiento;
	
	public ProxyMovimiento(EstrategiaMovimiento estrategiaMovimiento) {
		
		super();
		this.estrategiaMovimiento = estrategiaMovimiento;
		
	}
	
	private boolean destinoValido(Unidad unidad, Coordenada coordFinal) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada ubicacionUnidad = mapa.obtenerUbicacion(unidad);
		
		try {
			
			if ((mapa.obtenerCelda(coordFinal)).colisiona(unidad)) return false;
			
			if (ubicacionUnidad == null) return true;
			
			if (!this.estrategiaMovimiento.puedeOcupar(unidad, mapa.obtenerCelda(coordFinal))) return false;
			
		} catch (UbicacionInvalida ui) { return false; }
		
		int distanciaAMover = mapa.distanciaEntreCoordenadas(ubicacionUnidad, coordFinal);
		
		return this.estrategiaMovimiento.distanciaAlcanzable(distanciaAMover);
		
	}

	public void moverse(Jugador propietario, Unidad unidad,	Coordenada coordFinal) throws UbicacionInvalida, AccionInvalida {
		
		if (!this.destinoValido(unidad, coordFinal)) throw new UbicacionInvalida();
		
		if (!Juego.getInstance().turnoDe().equals(propietario)) throw new NoSePuedenMoverUnidadesEnemigas();
		
		this.estrategiaMovimiento.moverse(propietario, unidad, coordFinal);
		
	}
	
}
