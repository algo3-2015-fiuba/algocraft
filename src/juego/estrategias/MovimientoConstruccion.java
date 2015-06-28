package juego.estrategias;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.materiales.Material;
import juego.razas.construcciones.Construccion;
import juego.razas.construcciones.ConstruccionRecolectora;

public class MovimientoConstruccion implements EstrategiaMovimiento {

	private int vision;
	
	public MovimientoConstruccion(int vision) {
		super();
		this.vision = vision;
	}
	
	@Override
	public boolean visionSuficiente(Coordenada posicion, Coordenada coordFinal) {
		Mapa mapa = Juego.getInstance().getMapa();
		return (mapa.distanciaEntreCoordenadas(posicion, coordFinal) <= this.vision);
	}
	
	@Override
	public void descubrirMapa(Jugador propietario, Controlable controlable) {
		
		Construccion construccion = (Construccion) controlable;
		Mapa mapa = Juego.getInstance().getMapa();
		Iterator<Celda> it = null;
		
		try {
			it = construccion.obtenerRangoDeOcupacion().iterator();
		} catch (CoordenadaFueraDeRango cfdr) {}
		
		while (it.hasNext()) {
			
			Coordenada posicion = mapa.obtenerCoordenada(it.next());
			propietario.mapaDescubierto(mapa.obtenerRangoRadialDeCeldas(posicion, this.vision));
			
		}
		
	}
	
	@Override
	public boolean distanciaAlcanzable(int distanciaAMover) {
		return false;
	}
	
	@Override
	public boolean puedeOcupar(Controlable controlable, Celda celda) {
		
		Construccion construccion = (Construccion) controlable;
		
		if (!celda.getMaterial().equals(Material.tierra)) return false;
		
		if (celda.poseeBase()) return false;
		
		if (construccion.puedeExtraerRecursos()) {
			
			if (!celda.poseeRecursos()) return false;
			if (!celda.getRecurso().puedeRecolectar((ConstruccionRecolectora)construccion)) return false;
			
		} else {
			if (celda.poseeRecursos()) return false;
		}
		
		if (celda.colisiona(controlable)) return false;
		
		return true;
		
	}
	
	@Override
	public void moverse(Jugador controlador, Controlable construccion, Coordenada coordenadaFinal) {
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
