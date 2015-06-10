package juego.interfaces.commandConstrucciones.militares;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.CommandConstrucciones;
import juego.interfaces.Construible;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.RequiereFabrica;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.Terran;
import juego.razas.protoss.construcciones.Acceso;
import juego.razas.terran.construcciones.Fabrica;

public class ConstructorPuertoEstelar extends CommandConstrucciones {

	private int costoMinerales;
	
	public ConstructorPuertoEstelar() {
		super();
		this.costoMinerales = 150;		
	}
	
	@Override
	public void iniciarConstruccion(Terran raza, Coordenada coordenada) 
			throws CoordenadaFueraDeRango, CeldaOcupada, RecursosInsuficientes, RequiereFabrica {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int costoGasVespeno = 100;
		
		if (Fabrica.getCantidadDeFabricas() == 0) throw new RequiereFabrica();
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 6);
		
		jugador.recursos().consumirMinerales(this.costoMinerales);
		jugador.recursos().consumirGasVespeno(costoGasVespeno);
		
		Construible puertoEstelar = raza.obtenerPuertoEstelar();
		
		jugador.observar(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(puertoEstelar);
		}
		
		this.enConstruccion = puertoEstelar;
		
	}
	
	@Override
	public void iniciarConstruccion(Protoss raza, Coordenada coordenada) 
			throws CoordenadaFueraDeRango, CeldaOcupada, RecursosInsuficientes, RequiereAcceso {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int costoGasVespeno = 150;
		
		if (Acceso.getCantidadDeAccesos() == 0) throw new RequiereAcceso();
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 6);
		
		jugador.recursos().consumirMinerales(this.costoMinerales);
		jugador.recursos().consumirGasVespeno(costoGasVespeno);
		
		Construible puertoEstelar = raza.obtenerPuertoEstelar();
		
		jugador.observar(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(puertoEstelar);
		}
		
		this.enConstruccion = puertoEstelar;
		
	}
	
}
