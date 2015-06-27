package juego.razas.unidades;

import juego.interfaces.Controlable;
import juego.interfaces.excepciones.NoTieneVision;
import juego.proxys.ProxyAtaque;
import juego.razas.unidades.excepciones.AtaqueInvalido;

public abstract class UnidadAtaque extends Unidad {

	protected Ataques ataques;
	
	/* * * * * * * 
	 *           *
	 *  Ataques  *
	 *           *
	 * * * * * * */
	
	public void atacarA(Controlable victima) throws NoTieneVision, AtaqueInvalido {
		
		ProxyAtaque proxyAtaque = new ProxyAtaque(this.ataques);
		proxyAtaque.atacarA(this, victima);
		
	}
	
}
