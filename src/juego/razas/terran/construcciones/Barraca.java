package juego.razas.terran.construcciones;

import juego.interfaces.commandEntrenadores.EntrenadorMarine;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;

public class Barraca extends ConstruccionMilitar {

	private static int cantidadDeBarracas = 0;
	
	public Barraca() {
		super();
	}
	
	public static int getCantidadDeBarracas() { return cantidadDeBarracas; }
	
	public static void reiniciar() { cantidadDeBarracas = 0; }
	
	//Este metodo deberia eliminarse cuando se implemente el metodo remover
	public static void disminuirCantidadDeBarracas() { if (cantidadDeBarracas > 0) cantidadDeBarracas--; }
	
 	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 12);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 83.33;	
			this.tiempoDeConstruccion++;	
			if (this.construccionFinalizada()) cantidadDeBarracas++;
		}
	}

	public void entrenarMarine(Coordenada coordMarine) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		EntrenadorMarine entrenador = new EntrenadorMarine();
		
		entrenador.ejecutar(this, coordMarine);
	}
	
}
