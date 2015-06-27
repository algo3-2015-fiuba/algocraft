package juego.estrategias;

import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;

public class MovimientoConstruccion implements EstrategiaMovimiento {

	private int vision;
	
	public MovimientoConstruccion(int vision) {
		super();
		this.vision = vision;
	}
	
	@Override
	public int getVision() { return this.vision; }
	
	@Override
	public void moverse(Controlable construccion, Coordenada coordenadaFinal) {
		//Las construcciones no se mueven
	}
	
	@Override
	public void desocupar(Controlable controlable) {
		
		try {
		
			Collection<Celda> celdasOcupadas = ((Construccion)controlable).obtenerRangoDeOcupacion();
			
			Iterator<Celda> it = celdasOcupadas.iterator();
			
			while (it.hasNext()) {
				(it.next()).desocupar(((Construccion)controlable));
			}
		
		} catch (CoordenadaFueraDeRango cfdr) {
			//Esta excepcion no deberia ocurrir nunca, ya que este metodo solo debe usarse si la construccion ya fue construida.
		}
		
	}

	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return (movimientoDesconocido.colisionaCon(this));
	}

	@Override
	public boolean colisionaCon(MovimientoVolador volador) {
		return false;
	}

	@Override
	public boolean colisionaCon(MovimientoTerrestre terrestre) {
		return true;
	}
	
	@Override
	public boolean colisionaCon(MovimientoConstruccion construccion) {
		return true;
	}

}
