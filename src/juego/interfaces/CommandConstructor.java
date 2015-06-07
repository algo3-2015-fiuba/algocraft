package juego.interfaces;

import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.Raza;
import juego.razas.Terran;

public abstract class CommandConstructor {
	
	protected Construible enConstruccion;
	
	public void ejecutar(Raza raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango {
		
		raza.construir(this, coordenada);
		
	}
	
	/* Por default, asumimos que no se puede ejecutar un CommandConstructor en ninguna raza.
	 * Luego hacemos un @Override para las correctas
	 */

	public void ejecutar(Terran raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango {
		
		throw new ImposibleConstruir();
		
	}
	
	public void ejecutar(Protoss raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango {
		
		throw new ImposibleConstruir();
		
	}
	
	public void actualizarConstruccion() {
		if (!enConstruccion.construccionFinalizada()) enConstruccion.actualizarConstruccion();
	}
	
	public boolean construccionFinalizada() {
		return (this.enConstruccion.construccionFinalizada());
	}
	
}
