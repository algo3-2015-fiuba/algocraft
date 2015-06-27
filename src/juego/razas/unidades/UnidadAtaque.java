package juego.razas.unidades;

import juego.interfaces.Controlable;
import juego.interfaces.excepciones.NoTieneVision;
import juego.proxys.ProxyAtaque;
import juego.razas.ataques.Ataques;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import juego.razas.unidades.excepciones.YaAtacoEnEsteTurno;

public abstract class UnidadAtaque extends Unidad {

	protected ProxyAtaque proxyAtaque;
	protected Ataques ataques;
	
	/* * * * * * * 
	 *           *
	 *  Ataques  *
	 *           *
	 * * * * * * */
	
	protected boolean noAtacoEnEsteTurno() {
		return (this.proxyAtaque == null);
	}
	
	public void atacarA(Controlable victima) throws NoTieneVision, AtaqueInvalido {
		
		if (this.noAtacoEnEsteTurno()) {
			
			this.proxyAtaque = new ProxyAtaque(this.ataques);
			this.proxyAtaque.atacarA(this, victima);
		
		} else {
			throw new YaAtacoEnEsteTurno();
		}
	
	}
	
	@Override
	public void actualizar() { 
		this.proxyAtaque = null;
		this.vida.regenerar();
	}
	
}
