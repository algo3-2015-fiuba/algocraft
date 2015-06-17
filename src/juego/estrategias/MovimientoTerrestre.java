package juego.estrategias;

import juego.Juego;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class MovimientoTerrestre implements EstrategiaMovimiento {

	@Override
	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal) throws UbicacionInvalida {
		
		if (unidad.puedeMoverse(coordFinal)) {
		
			Mapa mapa = Juego.getInstance().getMapa();
			
			if (mapa.obtenerCelda(coordFinal).puedeOcuparTierra(unidad)) {
				if (coordInicial != null) mapa.obtenerCelda(coordInicial).desocupar(unidad);
				mapa.obtenerCelda(coordFinal).ocupar(unidad);
			} else {
				throw new UbicacionInvalida();
			}
			
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

	@Override
	public void desocupar(Coordenada coordenada, Unidad unidad) throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		mapa.obtenerCelda(coordenada).desocupar(unidad);
	}	
	
	@Override
	public void desocupar(Coordenada coordenada, Construccion construccion) throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		mapa.obtenerCelda(coordenada).desocupar(construccion);
	}	

}
