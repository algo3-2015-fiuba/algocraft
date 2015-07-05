package juego.razas.unidades;

import vistas.acciones.unidades.excepciones.NadaSeleccionado;
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
	
	public void atacarA(Controlable victima) throws NoTieneVision, AtaqueInvalido, NadaSeleccionado {
		
		if (this.noAtacoEnEsteTurno()) {
			
			try {				
				this.proxyAtaque = new ProxyAtaque(this.ataques, this.estrategiaDeMovimiento);
				this.proxyAtaque.atacarA(this, victima);
				
			} catch (NoTieneVision|AtaqueInvalido|NadaSeleccionado ntv) {
				
				this.proxyAtaque = null;
				throw ntv;
				
			}
			
		} else {
			throw new YaAtacoEnEsteTurno();
		}
	
	}
	
	@Override
	public void actualizar() { 
		this.proxyMovimiento = null;
		this.proxyAtaque = null;
		this.vida.regenerar();
	}
	
}
