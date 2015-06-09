package juego.razas;

import juego.interfaces.CommandConstrucciones;
import juego.interfaces.Construible;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.Coordenada;
import juego.razas.protoss.construcciones.PuertoEstelar;

public class Protoss extends Raza {
	
	@Override
	public void construir(CommandConstrucciones construccion, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada{
		construccion.iniciarConstruccion(this, coordenada);
	}
	
	public Construible obtenerPuertoEstelar() {
		return (new PuertoEstelar());
	}
	
}
