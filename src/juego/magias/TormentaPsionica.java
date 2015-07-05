package juego.magias;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;

public class TormentaPsionica extends Magia {

	private Coordenada coordImpacto;
	private int turnosActiva;
	private int danio;
	
	public TormentaPsionica(AltoTemplario mago) {
		
		super(mago);
		this.costoEnergia = 100;
		this.coordImpacto = null;
		this.turnosActiva = 2;
		this.danio = 100;
		
	}
	
	@Override
	public void activar() {
		if (this.activa()) {
			
			Mapa mapa = Juego.getInstance().getMapa();
			
			Collection<Celda> celdasEnRango = mapa.obtenerRangoRadialDeCeldas(coordImpacto, 5);
			
			Iterator<Celda> it = celdasEnRango.iterator();
			
			while (it.hasNext()) {
				it.next().afectadaPorMagia(this);
			}	
			
			this.turnosActiva--;
		}
	}
	
	@Override
	public boolean activa() { return (this.turnosActiva > 0); }

	public void lanzar(Coordenada coordImpacto) {
		//Se activa al finalizar el turno
		this.coordImpacto = coordImpacto;
		
		Mapa mapa = Juego.getInstance().getMapa();		
		Collection<Celda> celdasEnRango = mapa.obtenerRangoRadialDeCeldas(coordImpacto, 5);
		
		Iterator<Celda> it = celdasEnRango.iterator();
		
		while (it.hasNext()) {
			it.next().agregarMagia(this);
		}	
	}

	@Override
	public void afectar(Unidad unidad) {
		unidad.afectadaPorMagia(this);
	}

	public int getDanio() {
		return this.danio;
	}
	
}
