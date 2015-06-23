package juego.razas.unidades;

import juego.Juego;
import juego.informacion.AtaquesUnidades;
import juego.interfaces.excepciones.NoTieneVision;
import juego.jugadores.Jugador;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;

public abstract class UnidadAtaque extends Unidad {

	protected AtaquesUnidades bolsaDeAtaque;
	
	/* * * * * * * 
	 *           *
	 *  Ataques  *
	 *           *
	 * * * * * * */
	
	public void atacarA(Unidad unidad) throws NoTieneVision {
		
		Jugador propietario = Juego.getInstance().turnoDe();
		
		if (!propietario.esAliado(unidad)) {
			if(propietario.tieneVisionDe(unidad)) {
				if (bolsaDeAtaque.estaEnRango(this.estrategiaDeMovimiento, this.posicion, unidad)) {
					bolsaDeAtaque.atacar(this.estrategiaDeMovimiento, unidad);
				}
			} else {
				throw new NoTieneVision();
			}
		}
		
	}
	
	public void atacarA(Construccion construccion) throws CoordenadaFueraDeRango {
		
		Jugador propietario = Juego.getInstance().turnoDe();
		
		if (!propietario.esAliado(construccion)) {
			if (bolsaDeAtaque.estaEnRango(this.estrategiaDeMovimiento, this.posicion, construccion)) {
				bolsaDeAtaque.atacar(this.estrategiaDeMovimiento, construccion);
			}
		}
		
	}
	
}
