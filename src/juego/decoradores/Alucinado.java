package juego.decoradores;

import juego.interfaces.Atacable;

public class Alucinado implements Atacable {
	
	

	@Override
	public void regenerar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void daniar(int danio) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean vidaAgotada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deshabilitar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afectadoPorRadiacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public float vidaActual() {
		// TODO Auto-generated method stub
		return 0;
	}

}
