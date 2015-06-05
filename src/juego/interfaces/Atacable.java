package juego.interfaces;

import juego.interfaces.excepciones.YaFueDestruido;

public interface Atacable {

	public void atacadoPor(Atacable atacante) throws YaFueDestruido;
	
}
