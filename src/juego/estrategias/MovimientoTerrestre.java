package juego.estrategias;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public class MovimientoTerrestre implements EstrategiaMovimiento {

	@Override
	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal) throws UbicacionInvalida {
		Mapa mapa = Juego.getInstance().getMapa();
		
		//Hacemos que se comporte como terrestre
		Terrestre unidadTerrestre = (Terrestre) unidad;
		
		if (coordInicial == null) {
			mapa.obtenerCelda(coordFinal).ocupar(unidadTerrestre);
		} else {
			//Esta parte esta implementada la idea pero habria que verificar que puede avanzar hasta ahi segun el supuesto.
			mapa.obtenerCelda(coordInicial).desocupar(unidad);
			mapa.obtenerCelda(coordFinal).ocupar(unidadTerrestre);
		}
	}

	@Override
	public boolean ocupaMismoEspacioQue(Terrestre terrestre) {
		return true;
	}

	@Override
	public boolean ocupaMismoEspacioQue(Volador volador) {
		return false;
	}

	@Override
	public boolean ocupaMismoEspacioQue(Construible construible) {
		return true;
	}
	
	

}
