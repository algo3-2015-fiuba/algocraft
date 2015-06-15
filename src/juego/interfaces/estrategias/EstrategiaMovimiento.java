package juego.interfaces.estrategias;

import juego.interfaces.Construible;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public interface EstrategiaMovimiento {

	void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal)
			throws UbicacionInvalida;
	
	boolean ocupaMismoEspacioQue(Terrestre terrestre);
	boolean ocupaMismoEspacioQue(Volador volador);
	boolean ocupaMismoEspacioQue(Construible construible);
}
