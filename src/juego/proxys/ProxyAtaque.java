package juego.proxys;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.NoTieneVision;
import juego.jugadores.Jugador;
import juego.razas.unidades.Ataques;
import juego.razas.unidades.UnidadAtaque;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import juego.razas.unidades.excepciones.FueraDeRangoDeAtaque;
import juego.razas.unidades.excepciones.UnidadAliada;

public class ProxyAtaque {

	protected Ataques ataques;
	
	public ProxyAtaque(Ataques ataques) {
		
		super();
		this.ataques = ataques;
		
	}
	
	private boolean esAliado(Controlable victima) {
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		return (jugadorAtacante.esAliado(victima));
		
	}
	
	private boolean atacanteTieneVision(Controlable victima) {
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		return (jugadorAtacante.tieneVision(victima));
		
	}

	public void atacarA(UnidadAtaque agresor, Controlable victima) throws AtaqueInvalido, NoTieneVision {
		
		if (this.esAliado(victima)) throw new UnidadAliada();
		
		if (!this.atacanteTieneVision(victima)) throw new NoTieneVision();
		
		if (!this.ataques.estaEnRango(agresor, victima)) throw new FueraDeRangoDeAtaque();
	
		ataques.atacar(agresor.getMovimiento(), victima);
		
	}	
	
}
