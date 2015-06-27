package juego.razas.unidades;

import juego.interfaces.excepciones.NoTieneVision;
import juego.razas.construcciones.Construccion;

public abstract class UnidadAtaque extends Unidad {

	protected AtaqueUnidad ataqueUnidad;
	
	/* * * * * * * 
	 *           *
	 *  Ataques  *
	 *           *
	 * * * * * * */
	
	public void atacarA(Unidad unidad) throws NoTieneVision {
		
		if (!this.propietario.esAliado(unidad)) {
			if (this.propietario.tieneVision(unidad)) {
				if (ataqueUnidad.estaEnRango(this, unidad)) {
					ataqueUnidad.atacar(this.estrategiaDeMovimiento, unidad);
				}
			} else {
				throw new NoTieneVision();
			}
		}
		
	}
	
	public void atacarA(Construccion construccion) throws NoTieneVision {
		
		if (!this.propietario.esAliado(construccion)) {
			if (this.propietario.tieneVision(construccion)) {
				if (ataqueUnidad.estaEnRango(this, construccion)) {
					ataqueUnidad.atacar(this.estrategiaDeMovimiento, construccion);
				}
			} else {
				throw new NoTieneVision();
			}
		}
		
	}
	
}
