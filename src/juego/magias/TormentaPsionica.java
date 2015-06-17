package juego.magias;

import java.util.Collection;
import java.util.Iterator;

import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public class TormentaPsionica extends Magia {

	protected Coordenada coordImpacto;
	protected boolean activa;
	protected int turnosRestantes;
	
	public TormentaPsionica(Coordenada coordImpacto) {
		
		super();
		this.costoEnergia = 100;
		this.coordImpacto = null;
		this.activa = true;
		this.turnosRestantes = 2;
		this.coordImpacto = coordImpacto;
		
	}
	
	@Override
	public void activar() {
		if(this.turnosRestantes <= 0) {
			this.lanzar();
		} else {
			this.turnosRestantes--;
		}
	}
	
	@Override
	public boolean activa() { return this.activa; }

	
	public void lanzar() {
		
		Collection<Celda> celdasEnRango = this.obtenerRadioDeImpacto(coordImpacto, 5);
		
		Iterator<Celda> it = celdasEnRango.iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}	
		
	}

	@Override
	public void afectar(Unidad unidad) {
		unidad.afectadaPorMagia(this);
	}
	
}