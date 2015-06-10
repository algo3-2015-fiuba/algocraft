package juego.interfaces;

import juego.interfaces.excepciones.YaFueDestruido;

public interface Atacable {

	public void recibirAtaqueTerrestre(int cantidad) throws YaFueDestruido;
	public void recibirAtaqueAereo(int cantidad) throws YaFueDestruido;
	
}
