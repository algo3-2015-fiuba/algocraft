package juego.interfaces;

import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public abstract class CommandEntrenadores {
	
	protected int costoMinerales;
	protected int tiempoDeEntrenamiento;
	
	public void ejecutar(Militable edificioMilitable, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		throw new ImposibleConstruir();
	}
	
	public abstract void actualizarEntrenamiento() throws CoordenadaFueraDeRango;
	
	public boolean entrenamientoFinalizado() {
		return (this.tiempoDeEntrenamiento == 0);
	}
	
}
