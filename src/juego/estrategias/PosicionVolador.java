package juego.estrategias;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.interfaces.Construible;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;
import juego.interfaces.estrategias.EstrategiaPosicion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public class PosicionVolador implements EstrategiaPosicion {

	@Override
	public void moverse(Unidad unidad, Coordenada coordInicial, Coordenada coordFinal) throws UbicacionInvalida {
		Mapa mapa = Juego.getInstance().getMapa();
		
		//Hacemos que se comporte como voladora
		Volador unidadVoladora = (Volador) unidad;
		
		if (coordInicial == null) {
			mapa.obtenerCelda(coordFinal).ocupar(unidadVoladora);
		} else {
			//Esta parte esta implementada la idea pero habria que verificar que puede avanzar hasta ahi segun el supuesto.
			mapa.obtenerCelda(coordInicial).desocupar(unidad);
			mapa.obtenerCelda(coordFinal).ocupar(unidadVoladora);
		}
	}
	
	@Override
	public boolean ocupaMismoEspacioQue(Terrestre terrestre) {
		return false;
	}

	@Override
	public boolean ocupaMismoEspacioQue(Volador volador) {
		return true;
	}
	
	@Override
	public boolean ocupaMismoEspacioQue(Construible construible) {
		return false;
	}
	
	@Override
	public int danioRecibido(BolsaDeAtaque bolsaDeAtaque) {
		return bolsaDeAtaque.getDanioAire();
	}

}
