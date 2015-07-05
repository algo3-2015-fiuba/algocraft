package juego.proxys;

import juego.Juego;
import juego.estrategias.EstrategiaMovimiento;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.NoTieneVision;
import juego.jugadores.Jugador;
import juego.mapa.Mapa;
import juego.razas.ataques.Ataques;
import juego.razas.unidades.UnidadAtaque;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import juego.razas.unidades.excepciones.FueraDeRangoDeAtaque;
import juego.razas.unidades.excepciones.NoSePuedeOrdenarAtacarAUnidadEnemiga;
import juego.razas.unidades.excepciones.NoSePuedenAtacarUnidadesAliadas;

public class ProxyAtaque {

	protected Ataques ataques;
	protected EstrategiaMovimiento tacticaMovimiento;
	
	public ProxyAtaque(Ataques ataques, EstrategiaMovimiento tacticaMovimiento) {
		
		super();
		this.ataques = ataques;
		this.tacticaMovimiento = tacticaMovimiento;
		
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
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		if (!this.esAliado(agresor)) throw new NoSePuedeOrdenarAtacarAUnidadEnemiga();
		
		if (this.esAliado(victima)) throw new NoSePuedenAtacarUnidadesAliadas();
		
		if (!this.ataques.estaEnRango(this.tacticaMovimiento, mapa.obtenerUbicacion(agresor), victima)) throw new FueraDeRangoDeAtaque();
	
		if (!this.atacanteTieneVision(victima)) throw new NoTieneVision();
		
		this.ataques.atacar(this.tacticaMovimiento, victima);
		
	}	
	
}
