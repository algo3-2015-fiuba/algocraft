package juego.interfaces.commandConstrucciones.recolectores;

import juego.Juego;
import juego.interfaces.CommandConstrucciones;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.protoss.construcciones.Asimilador;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;

public class ConstructorAsimilador extends CommandConstrucciones {

	private int costoMinerales;
	
	public ConstructorAsimilador() {
		super();
		this.costoMinerales = 100;
	}

	@Override
	public void iniciarConstruccion(Protoss raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, CoordenadaFueraDeRango, CeldaOcupada {
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		if (mapa.obtenerCelda(coordenada).ocupadoEnTierra()) throw new CeldaOcupada();
		Recurso recurso = mapa.getRecurso(coordenada);
		
		if (!recurso.esPosibleConstruir(this)) throw new UbicacionInvalida();
		
		jugador.recursos().consumirMinerales(this.costoMinerales);
		
		Asimilador asimilador = new Asimilador(recurso);
		
		jugador.observar(this);
		mapa.obtenerCelda(coordenada).agregarControlable(asimilador);
		this.enConstruccion = asimilador;
	}
	
	@Override
	public boolean puedeExtraer(GasVespeno recurso) { return true; }
	
}
