package juego.decorators;

import juego.bolsas.BolsaDeAtaque;
import juego.interfaces.Atacable;
import juego.razas.unidades.Unidad;

public class EscudoDecorator extends Unidad {
	
	Atacable decorado;
	int escudoMaximo;
	int escudoActual;
	
	public EscudoDecorator(Unidad decorado, int escudoMaximo) {
		
		this.decorado = decorado;
		this.escudoMaximo = escudoMaximo;
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

	@Override
	public void recibirDanio(int cantidad) {
		int nuevoDanio = this.atacarEscudo(cantidad);
		
		decorado.recibirDanio(nuevoDanio);
	}
	
	@Override
	public void recibirAtaque(BolsaDeAtaque ataque) {
		decorado.recibirAtaque(ataque);
	}

	@Override
	public boolean estaMuerto() {
		return decorado.estaMuerto();
	}
	
	
	
	

}
