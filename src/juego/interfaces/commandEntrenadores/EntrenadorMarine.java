package juego.interfaces.commandEntrenadores;

import juego.Juego;
import juego.interfaces.CommandEntrenadores;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.terran.construcciones.Barraca;
import juego.razas.terran.unidades.Marine;

public class EntrenadorMarine extends CommandEntrenadores {
	
	private Marine enEntrenamiento;
	private Coordenada posicion;
	
	public EntrenadorMarine(){
		this.costoMinerales = 50;
		this.tiempoDeEntrenamiento = 3;
	}

	public void ejecutar(Barraca barraca, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		
		jugador.consumirMinerales(this.costoMinerales);
		
		Marine marine = new Marine(jugador);
		
		barraca.agregarEntrenamiento(this);
		
		this.enEntrenamiento = marine;
		this.posicion = coordenada;
	}
	
	public void actualizarEntrenamiento() throws CoordenadaFueraDeRango {
		this.tiempoDeEntrenamiento--;
		
		if(this.entrenamientoFinalizado()) {
			Juego juego = Juego.getInstance();
			Mapa mapa = juego.getMapa();
			
			Celda celdaAOcupar = mapa.obtenerCelda(posicion);
			
			try {
				this.enEntrenamiento.ocuparCelda(celdaAOcupar);
			} catch (CeldaOcupada e) {
				this.tiempoDeEntrenamiento++;
			}
		}
	}

}
