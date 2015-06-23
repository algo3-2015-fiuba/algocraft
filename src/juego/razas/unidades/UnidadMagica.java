package juego.razas.unidades;

import java.util.ArrayList;
import java.util.Collection;

import juego.energia.Energia;
import juego.magias.Magia;
import juego.magias.MisilEMP;

public abstract class UnidadMagica extends Unidad {
	
	protected Collection<Magia> magiasActivas;
	protected Energia energia;
	
	public UnidadMagica() {
		this.magiasActivas = new ArrayList<Magia>();
	}
	
	@Override
	public void afectadaPorMagia(MisilEMP emp) {
		this.vida.deshabilitar();
		this.energia.deshabilitada();
	}
}
