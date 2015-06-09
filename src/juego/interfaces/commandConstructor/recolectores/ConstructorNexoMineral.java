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
import juego.razas.Protoss;
import juego.razas.protoss.construcciones.NexoMineral;
import juego.recursos.Mineral;
import juego.recursos.Recurso;

public class ConstructorNexoMineral extends CommandConstructor {

	private int costoMinerales;
	
	public ConstructorNexoMineral() {
		super();
		this.costoMinerales = 50;
	}
	
	@Override
	public void ejecutar(Protoss raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, CoordenadaFueraDeRango, CeldaOcupada {
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		if (mapa.obtenerCelda(coordenada).ocupadoEnTierra()) throw new CeldaOcupada();
		Recurso recurso = mapa.getRecurso(coordenada);
		
		if (!recurso.esPosibleConstruir(this)) throw new UbicacionInvalida();
		
		jugador.consumirMinerales(this.costoMinerales);
		
		NexoMineral nexoMineral = new NexoMineral(recurso);
		
		jugador.agregarConstructor(this);
		mapa.obtenerCelda(coordenada).agregarControlable(nexoMineral);
		this.enConstruccion = nexoMineral;
		
	}
	
	
	@Override
	public boolean puedeExtraer(Mineral recurso) { return true; }
	
}
