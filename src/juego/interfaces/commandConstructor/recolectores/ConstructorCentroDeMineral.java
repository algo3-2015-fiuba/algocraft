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
import juego.razas.terran.construcciones.CentroDeMineral;
import juego.recursos.Mineral;
import juego.recursos.Recurso;


public class ConstructorCentroDeMineral extends CommandConstructor {
	
	@Override
	public void ejecutar(Terran raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, CoordenadaFueraDeRango, CeldaOcupada {
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int costoMinerales = 50;
		
		if (mapa.obtenerCelda(coordenada).ocupadoEnTierra()) throw new CeldaOcupada();
		Recurso recurso = mapa.getRecurso(coordenada);
		
		if (!recurso.esPosibleConstruir(this)) throw new UbicacionInvalida();
		
		jugador.consumirMinerales(costoMinerales);
		
		CentroDeMineral centroDeMineral = new CentroDeMineral(recurso);
		
		jugador.agregarConstructor(this);
		mapa.obtenerCelda(coordenada).agregarControlable(centroDeMineral);
		this.enConstruccion = centroDeMineral;
		
	}
	
	
	@Override
	public boolean esPosibleExtraer(Mineral recurso) { return true; }
	
}
