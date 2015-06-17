package juego.estrategias;

import juego.Juego;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public class MovimientoTerrestre implements EstrategiaMovimiento {

	@Override
	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal) throws UbicacionInvalida {
		Mapa mapa = Juego.getInstance().getMapa();
		
		if (coordInicial == null) {
			mapa.obtenerCelda(coordFinal).ocuparTierra(unidad);
		} else {
			//Esta parte esta implementada la idea pero habria que verificar que puede avanzar hasta ahi segun el supuesto.
			mapa.obtenerCelda(coordInicial).desocupar(unidad);
			mapa.obtenerCelda(coordFinal).ocuparTierra(unidad);
		}
	}

	@Override
	public boolean colisionaCon(MovimientoTerrestre terrestre) {
		return true;
	}
	
	@Override
	public boolean colisionaCon(MovimientoVolador volador) {
		return false;
	}
	

	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return movimientoDesconocido.colisionaCon(this);
	}	

}
