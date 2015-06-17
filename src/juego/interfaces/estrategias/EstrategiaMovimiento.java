package juego.interfaces.estrategias;

import juego.estrategias.MovimientoTerrestre;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public interface EstrategiaMovimiento {

	void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal)
			throws UbicacionInvalida;
	
	boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido);
	boolean colisionaCon(MovimientoVolador volador);
	boolean colisionaCon(MovimientoTerrestre terrestre);
	
}
