package juego.razas.unidades;

import juego.bolsas.BolsaDeAtaque;
import juego.interfaces.Atacable;
import juego.interfaces.Atacante;

public abstract class UnidadComun extends Unidad implements Atacante {

	protected BolsaDeAtaque bolsaDeAtaque;
	

	public void atacar(Atacable victima) {
		victima.recibirAtaque(bolsaDeAtaque);
	}
	
}
