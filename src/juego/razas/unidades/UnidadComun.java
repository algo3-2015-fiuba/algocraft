package juego.razas.unidades;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.jugadores.Jugador;
import juego.magias.MisilEMP;
import juego.razas.construcciones.Construccion;

public abstract class UnidadComun extends Unidad {

	protected BolsaDeAtaque bolsaDeAtaque;
	
	/* * * * * * * 
	 *           *
	 *  Ataques  *
	 *           *
	 * * * * * * */
	
	public void atacarA(Unidad unidad) {
		
		Jugador propietario = Juego.getInstance().turnoDe();
		
		if (!propietario.esAliado(unidad)) {
			if (bolsaDeAtaque.estaEnRango(this.estrategiaDeMovimiento, this.posicion, unidad)) {
				bolsaDeAtaque.atacar(this.estrategiaDeMovimiento, unidad);
			}
		}
		
	}
	
	public void atacarA(Construccion construccion) {
		
		Jugador propietario = Juego.getInstance().turnoDe();
		
		if (!propietario.esAliado(construccion)) {
			if (bolsaDeAtaque.estaEnRango(this.estrategiaDeMovimiento, this.posicion, construccion)) {
				bolsaDeAtaque.atacar(this.estrategiaDeMovimiento, construccion);
			}
		}
		
	}
	
	public void afectadaPorMagia(MisilEMP emp) {
		this.vida.deshabilitar();
	}
	
}
