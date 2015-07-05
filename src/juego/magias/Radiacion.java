package juego.magias;

import java.util.Iterator;

import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.terran.NaveCiencia;

public class Radiacion extends Magia {
	
	private Unidad infectado;
	
	public Radiacion(NaveCiencia mago) {
		
		super(mago);
		this.costoEnergia = 75;
		this.infectado = null;
		
	}

	@Override
	public void activar() {
		Mapa mapa = Juego.getInstance().getMapa();
		this.irradiar(mapa.obtenerUbicacion(this.infectado));
	}
	
	private void irradiar(Coordenada ubicacionInfectado) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Iterator<Celda> it = mapa.obtenerRangoRadialDeCeldas(ubicacionInfectado, 1).iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}
		
	}
	
	public void infectar(Unidad unidad) {
		this.infectado = unidad;
	}
	
	@Override
	public void afectar(Unidad unidad) {
		unidad.afectadaPorMagia(this);
	}

	public void fallecido(Unidad unidad) {
		if (this.infectado == unidad) this.infectado = null;		
	}

	@Override
	public boolean activa() {
		return (this.infectado == null);
	}

}
