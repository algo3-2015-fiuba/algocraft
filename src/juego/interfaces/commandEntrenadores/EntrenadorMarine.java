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
		
		jugador.recursos().consumirMinerales(this.costoMinerales);
		
		Marine marine = new Marine(jugador);
		
		barraca.agregarEntrenamiento(this);
		
		jugador.observar(this);		
		this.enEntrenamiento = marine;
		this.posicion = coordenada;
	}
	
	public void actualizarEntrenamiento() {
		this.tiempoDeEntrenamiento--;
		
		if(this.entrenamientoFinalizado()) {
			Juego juego = Juego.getInstance();
			Mapa mapa = juego.getMapa();
			
			Celda celdaAOcupar;
			try {
				celdaAOcupar = mapa.obtenerCelda(this.posicion);
				this.enEntrenamiento.ocuparCelda(celdaAOcupar);
			} catch (CeldaOcupada e) {
				this.tiempoDeEntrenamiento++;
			} catch (CoordenadaFueraDeRango cfdr) {
				cfdr.printStackTrace(); //Ya es verificado previamente en ejecutar()
			}
		}
	}

}
