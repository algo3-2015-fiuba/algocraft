package juego.interfaces.estrategias;

import juego.estrategias.MovimientoConstruccion;
import juego.estrategias.MovimientoTerrestre;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public interface EstrategiaMovimiento {

	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal)
			throws UbicacionInvalida;
	
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido);
	public boolean colisionaCon(MovimientoVolador volador);
	public boolean colisionaCon(MovimientoTerrestre terrestre);
	public boolean colisionaCon(MovimientoConstruccion construccion);
	
	public void desocupar(Coordenada c, Unidad unidad) throws CoordenadaFueraDeRango;
	public void desocupar(Coordenada posicion, Construccion construccion) throws CoordenadaFueraDeRango;
	
}
