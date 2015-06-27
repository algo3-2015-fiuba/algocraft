package juego.razas.construcciones;

import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
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
	public void posicionar(Coordenada posicion) throws UbicacionInvalida {
		
		this.posicion = posicion;
		
		Collection<Celda> celdas = this.obtenerRangoDeOcupacion();		
		
		Iterator<Celda> itCeldas = celdas.iterator();
		
		try {
			while (itCeldas.hasNext()) {
				Celda celda = itCeldas.next();
				if (!celda.puedeConstruir(this)) throw new UbicacionInvalida();
				celda.ocupar(this);
				this.nodoRecurso = celda.getRecurso();
			}
		} catch (UbicacionInvalida ui) {
			itCeldas = celdas.iterator();
			while (itCeldas.hasNext()) {
				itCeldas.next().desocupar(this);
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