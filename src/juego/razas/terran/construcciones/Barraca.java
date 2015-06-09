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

	public Barraca() {
		super();
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 12);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 83.33;	
			this.tiempoDeConstruccion++;		
		}
	}

	public void entrenarMarine(Coordenada coordMarine) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		EntrenadorMarine entrenador = new EntrenadorMarine();
		
		entrenador.ejecutar(this, coordMarine);
	}
	
}
