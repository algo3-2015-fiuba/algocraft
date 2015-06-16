package juego.decorators;

import juego.interfaces.Atacable;
import juego.razas.unidades.Unidad;

public class EscudoDecorator extends Unidad {
	
	Atacable decorado;
	int escudoMaximo;
	int escudoActual;
	
	public EscudoDecorator(Atacable decorado, int escudoMaximo) {
		this.decorado = decorado;
		this.escudoMaximo = escudoMaximo;
	}

	@Override
	public void recibirDanio(int cantidad) {
		int nuevoDanio = this.atacarEscudo(cantidad);
		
		decorado.recibirDanio(nuevoDanio);
	}

	private int atacarEscudo(int danioRecibido) {
		if(escudoActual >= danioRecibido) {
			escudoActual -= danioRecibido;
			danioRecibido = 0;
		} else {
			danioRecibido -= escudoActual;
		}
		
		return danioRecibido;
	}

}
