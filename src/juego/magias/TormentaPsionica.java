package juego.magias;

import java.util.Collection;
import java.util.Iterator;

import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public class TormentaPsionica extends Magia {

	protected Coordenada coordImpacto;
	protected boolean activa;
	
	public TormentaPsionica() {
		
		super();
		this.costoEnergia = 100;
		this.coordImpacto = null;
		this.activa = true;
		
	}
	
	@Override
	public void activar() {
		this.lanzar(coordImpacto);
		this.activa = false;
	}
	
	@Override
	public boolean activa() { return this.activa; }

	@Override
	public void lanzar(Coordenada coordImpacto) {
		
		Collection<Celda> celdasEnRango = this.obtenerRadioDeImpacto(coordImpacto, 5, 5);
		
		Iterator<Celda> it = celdasEnRango.iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}	
		
		this.coordImpacto = coordImpacto;
		
	}

	@Override
	public void afectar(Unidad unidad) {
		unidad.recibirAtaque(100);
	}
	
}
