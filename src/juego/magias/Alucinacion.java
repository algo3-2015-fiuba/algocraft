package juego.magias;

import juego.Juego;
import juego.razas.unidades.Unidad;

public class Alucinacion extends Magia {

	public Alucinacion() {
		
		super();
		this.costoEnergia = 100;
		this.propietario = Juego.getInstance().turnoDe();
		
	}
	
	@Override
	public void afectar(Unidad unidad) {
		
		if (Juego.getInstance().turnoDe().esAliado(unidad)) {
			unidad.afectadaPorMagia(this);
		}
		
	}

	@Override
	public void activar() {
		//Se activa unicamente una sola vez al lanzarse.
	}

	@Override
	public boolean activa() {
		return false;
	}
	
}
