package juego.estrategias;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class MovimientoConstruccion implements EstrategiaMovimiento {

	@Override
	public void moverse(Unidad unidad, Coordenada coordInicial,
			Coordenada coordFinal) throws UbicacionInvalida {
		//Las construcciones no se mueven
	}

	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return (movimientoDesconocido.colisionaCon(this));
	}

	@Override
	public boolean colisionaCon(MovimientoVolador volador) {
		return false;
	}

	@Override
	public boolean colisionaCon(MovimientoTerrestre terrestre) {
		return true;
	}
	
	@Override
	public boolean colisionaCon(MovimientoConstruccion construccion) {
		return true;
	}

	@Override
	public void desocupar(Coordenada coordenada, Unidad unidad) throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		mapa.obtenerCelda(coordenada).desocupar(unidad);
	}	
	
	@Override
	public void desocupar(Coordenada posicion, Construccion construccion) throws CoordenadaFueraDeRango {
		
		Collection<Celda> celdasOcupadas = construccion.obtenerRangoDeOcupacion();
		
		Iterator<Celda> it = celdasOcupadas.iterator();
		
		while (it.hasNext()) {
			(it.next()).desocupar(construccion);
		}
		
	}

}
