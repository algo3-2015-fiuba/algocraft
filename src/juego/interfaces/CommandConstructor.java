package juego.interfaces;

import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.Raza;
import juego.razas.Terran;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;

public abstract class CommandConstructor {
	
	protected Construible enConstruccion;
	
	public void ejecutar(Raza raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		raza.construir(this, coordenada);
		
	}
	
	/* Por default, asumimos que no se puede ejecutar un CommandConstructor en ninguna raza.
	 * Luego hacemos un @Override para las construcciones habilitadas segun la raza.
	 */

	public void ejecutar(Terran raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		throw new ImposibleConstruir();
		
	}
	
	public void ejecutar(Protoss raza, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		throw new ImposibleConstruir();
		
	}
	
	public void actualizarConstruccion() {
		if (!this.enConstruccion.construccionFinalizada()) this.enConstruccion.actualizarConstruccion();
	}
	
	public boolean construccionFinalizada() {
		return (this.enConstruccion.construccionFinalizada());
	}
	
	//Por default consideramos que no se pueden extraer recursos	
	public boolean puedeExtraer(Mineral recurso) {
		return false;
	}
	
	public boolean puedeExtraer(GasVespeno recurso) {
		return false;
	}
	
}
