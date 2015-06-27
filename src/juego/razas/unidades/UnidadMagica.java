package juego.razas.unidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.energia.Energia;
import juego.magias.Magia;
import juego.magias.MisilEMP;

public abstract class UnidadMagica extends Unidad {
	
	protected Collection<Magia> magiasActivas;
	protected Energia energia;
	
	public UnidadMagica() {
		this.magiasActivas = new ArrayList<Magia>();
		this.energia = new Energia();
	}
	
	protected void activarMagias() {
		
		Collection<Magia> magiasFinalizadas = new ArrayList<Magia>();
		
		Iterator<Magia> it = this.magiasActivas.iterator();
		
		while (it.hasNext()) {
			Magia magia = it.next();
			magia.activar();
			if (!magia.activa()) magiasFinalizadas.add(magia);
		}
		
		this.magiasActivas.removeAll(magiasFinalizadas);
	}
	
	@Override
	public void actualizar() {
		this.vida.regenerar();
		this.energia.cargar(15);
		this.activarMagias();
	}
	
	@Override
	public void afectadaPorMagia(MisilEMP emp) {
		this.vida.deshabilitar();
		this.energia.deshabilitada();
	}
}
