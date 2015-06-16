package juego.interfaces;

import juego.bolsas.BolsaDeAtaque;

public interface Atacable {
	public void recibirDanio(BolsaDeAtaque ataque);
	public boolean estaMuerto();
}
