package juego.estrategias;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.interfaces.estrategias.EstrategiaPosicion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public class PosicionVolador implements EstrategiaPosicion {

	@Override
	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal) throws UbicacionInvalida {
		Mapa mapa = Juego.getInstance().getMapa();
		
		
		if (coordInicial == null) {
			mapa.obtenerCelda(coordFinal).ocuparVolador(unidad);
		} else {
			//Esta parte esta implementada la idea pero habria que verificar que puede avanzar hasta ahi segun el supuesto.
			mapa.obtenerCelda(coordInicial).desocupar(unidad);
			mapa.obtenerCelda(coordFinal).ocuparVolador(unidad);
		}
	}
	
	@Override
	public int danioRecibido(BolsaDeAtaque bolsaDeAtaque) {
		return bolsaDeAtaque.getDanioAire();
	}

	@Override
	public boolean ocupaMismoEspacioQue(EstrategiaPosicion estrategiaDeOtro) {
		return estrategiaDeOtro.ocupaMismoEspacioQue(this);
	}

	@Override
	public boolean ocupaMismoEspacioQue(PosicionVolador volador) {
		return true;
	}

	@Override
	public boolean ocupaMismoEspacioQue(PosicionTerrestre terrestre) {
		return false;
	}

}
