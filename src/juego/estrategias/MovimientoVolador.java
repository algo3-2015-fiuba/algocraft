package juego.estrategias;

import juego.Juego;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public class MovimientoVolador implements EstrategiaMovimiento {

	@Override
	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal) throws UbicacionInvalida {
		
		if (unidad.puedeMoverse(coordFinal)) {
		
			Mapa mapa = Juego.getInstance().getMapa();
			
			if (mapa.obtenerCelda(coordFinal).puedeOcuparAire(unidad)) {
				mapa.obtenerCelda(coordInicial).desocupar(unidad);
				mapa.obtenerCelda(coordFinal).ocupar(unidad);
			}
			
		}

	}

	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return movimientoDesconocido.colisionaCon(this);
	}

	@Override
	public boolean colisionaCon(MovimientoVolador volador) {
		return true;
	}

	@Override
	public boolean colisionaCon(MovimientoTerrestre terrestre) {
		return false;
	}

}
