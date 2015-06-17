package juego.interfaces.estrategias;

import juego.estrategias.MovimientoTerrestre;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public interface EstrategiaMovimiento {

	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal)
			throws UbicacionInvalida;
	
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido);
	public boolean colisionaCon(MovimientoVolador volador);
	public boolean colisionaCon(MovimientoTerrestre terrestre);
	
}
