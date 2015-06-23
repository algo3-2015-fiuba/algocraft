package juego.razas.unidades;

import juego.Juego;
import juego.informadores.AtaqueUnidad;
import juego.interfaces.excepciones.NoTieneVision;
import juego.jugadores.Jugador;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;

public abstract class UnidadAtaque extends Unidad {

	protected AtaqueUnidad ataqueUnidad;
	
	/* * * * * * * 
	 *           *
	 *  Ataques  *
	 *           *
	 * * * * * * */
	
	public void atacarA(Unidad unidad) throws NoTieneVision {
		
		Jugador propietario = Juego.getInstance().turnoDe();
		
		if (!propietario.esAliado(unidad)) {
			if(propietario.tieneVisionDe(unidad)) {
				if (ataqueUnidad.estaEnRango(this, unidad)) {
					ataqueUnidad.atacar(this.estrategiaDeMovimiento, unidad);
				}
			} else {
				throw new NoTieneVision();
			}
		}
		
	}
	
	public void atacarA(Construccion construccion) throws CoordenadaFueraDeRango {
		
		Jugador propietario = Juego.getInstance().turnoDe();
		
		if (!propietario.esAliado(construccion)) {
			if (ataqueUnidad.estaEnRango(this, construccion)) {
				ataqueUnidad.atacar(this.estrategiaDeMovimiento, construccion);
			}
		}
		
	}
	
}
