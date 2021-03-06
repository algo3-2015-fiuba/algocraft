package juego.razas.construcciones;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;
import juego.recursos.Recurso;

public abstract class ConstruccionRecolectora extends Construccion {

	protected Recurso nodoRecurso;
	
	public ConstruccionRecolectora() {
		super();
	}
	
	public abstract void recolectar();
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() {
		
		try {
			
			Mapa mapa = Juego.getInstance().getMapa();
			return mapa.obtenerRangoDeCeldas(this.posicion, 1, 1);
		
		
		} catch (CoordenadaFueraDeRango cfdr) {
		
			return null;
		
		}
	
	}
	
	@Override
	public void posicionar(Coordenada posicion) throws UbicacionInvalida {
		
		this.posicion = posicion;
		
		Collection<Celda> celdas = this.obtenerRangoDeOcupacion();		
		
		if (celdas == null) throw new CoordenadaFueraDeRango();
		
		Iterator<Celda> itCeldas = celdas.iterator();
		
		try {
			while (itCeldas.hasNext()) {
				Celda celda = itCeldas.next();
				if (!this.estrategiaDeMovimiento.puedeOcupar(this, celda)) throw new UbicacionInvalida();
				celda.ocupar(this);
				this.nodoRecurso = celda.getRecurso();
			}
		} catch (UbicacionInvalida ui) {
			itCeldas = celdas.iterator();
			while (itCeldas.hasNext()) {
				Celda celda = itCeldas.next();
				celda.desocupar(this);
			}
			throw new UbicacionInvalida();
		}
		
		this.estrategiaDeMovimiento.descubrirMapa(this.propietario, this);
		
	}
	
	@Override
	public boolean puedeExtraerRecursos() { return true; }
	
	//Cada edificio recolector redefine el recurso que puede extraer
	public boolean puedeExtraer(GasVespeno gv) { return false; }
	public boolean puedeExtraer(Mineral m) { return false; }
			
}