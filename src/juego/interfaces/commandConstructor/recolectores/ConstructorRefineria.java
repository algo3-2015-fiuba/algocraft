package juego.interfaces.commandConstructor.recolectores;

import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Terran;
import juego.razas.terran.construcciones.Refineria;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;

public class ConstructorRefineria extends CommandConstructor {

	private int costoMinerales = 100;
	
	@Override
	public void ejecutar(Terran raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, CoordenadaFueraDeRango, CeldaOcupada {
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		if (mapa.obtenerCelda(coordenada).ocupadoEnTierra()) throw new CeldaOcupada();
		Recurso recurso = mapa.getRecurso(coordenada);
		
		if (!recurso.esPosibleConstruir(this)) throw new UbicacionInvalida();
		
		jugador.consumirMinerales(this.costoMinerales);
		
		Refineria refineria = new Refineria(recurso);
		
		jugador.agregarConstructor(this);
		mapa.obtenerCelda(coordenada).agregarControlable(refineria);
		this.enConstruccion = refineria;
		
	}
	
	@Override
	public boolean puedeExtraer(GasVespeno recurso) { return true; }
	
}
