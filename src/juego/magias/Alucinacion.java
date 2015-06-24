package juego.magias;

import juego.Juego;
import juego.razas.unidades.Unidad;

public class Alucinacion extends Magia {

	private boolean activa;
	
	public Alucinacion() {
		this.activa = true;
	}
	
	@Override
	public void afectar(Unidad unidad) {
		
		if (Juego.getInstance().turnoDe().esAliado(unidad)) {
			unidad.afectadaPorMagia(this);
		}
		
	}
	
	public void deshabilitar() { this.activa = false; }

	@Override
	public boolean activa() {
		return this.activa;
	}

	@Override
	public void activar() {
		//Se activa unicamente una sola vez al lanzarse.
	}

}
