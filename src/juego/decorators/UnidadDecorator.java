package juego.decorators;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.Unidad;

public class UnidadDecorator extends Unidad {

	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		// TODO Auto-generated method stub

	}

	@Override
	public void entrenador(ConstruccionMilitar cm)
			throws RecursosInsuficientes, SobrePoblacion {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarEntrenamiento() {
		// TODO Auto-generated method stub

	}

}
