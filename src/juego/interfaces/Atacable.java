package juego.interfaces;

import juego.bolsas.BolsaDeAtaque;

public interface Atacable {
	public void recibirAtaque(BolsaDeAtaque ataque);
	public boolean estaMuerto();
	public void recibirDanio(int nuevoDanio);
}
