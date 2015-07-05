package juego.magias;

import juego.Juego;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;

public class Alucinacion extends Magia {

	public Alucinacion(AltoTemplario mago) {
		
		super(mago);
		this.costoEnergia = 100;
		
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
	public boolean activa() { return false; }
	
}
