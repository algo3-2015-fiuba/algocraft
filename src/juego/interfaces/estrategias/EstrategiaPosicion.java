package juego.interfaces.estrategias;

import juego.bolsas.BolsaDeAtaque;
import juego.estrategias.PosicionTerrestre;
import juego.estrategias.PosicionVolador;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public interface EstrategiaPosicion {

	void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal)
			throws UbicacionInvalida;
	
	int danioRecibido(BolsaDeAtaque bolsaDeAtaque);

	boolean ocupaMismoEspacioQue(EstrategiaPosicion estrategiaDeOtro);
	boolean ocupaMismoEspacioQue(PosicionVolador volador);
	boolean ocupaMismoEspacioQue(PosicionTerrestre terrestre);
}
