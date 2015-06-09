package juego.interfaces.commandConstrucciones.militares;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.CommandConstrucciones;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.protoss.construcciones.ArchivosTemplarios;

public class ConstructorArchivosTemplarios extends CommandConstrucciones {

	private int costoMinerales;
	private int costoGasVespeno;
	
	public ConstructorArchivosTemplarios() {
		super();
		this.costoMinerales = 150;
		this.costoGasVespeno = 200;
	}
	
	@Override
	public void iniciarConstruccion(Protoss raza, Coordenada coordenada) 
			throws CoordenadaFueraDeRango, CeldaOcupada, RecursosInsuficientes {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 6);
		
		jugador.consumirMinerales(this.costoMinerales);
		jugador.consumirGasVespeno(this.costoGasVespeno);
		
		ArchivosTemplarios archivosTemplarios = new ArchivosTemplarios();
		
		jugador.agregarConstructor(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(archivosTemplarios);
		}
		
		this.enConstruccion = archivosTemplarios;
		
	}
	
}
