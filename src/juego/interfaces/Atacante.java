package juego.interfaces;

import juego.interfaces.excepciones.YaFueDestruido;

public interface Atacante {
	public void atacar(Atacable destino) throws YaFueDestruido;
}
