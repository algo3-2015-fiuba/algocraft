package juego.estrategias;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.materiales.Material;
import juego.razas.ataques.Ataques;
import juego.razas.construcciones.Construccion;
import juego.razas.construcciones.ConstruccionRecolectora;

public class MovimientoConstruccion extends EstrategiaMovimiento {
	
	public MovimientoConstruccion(int vision) {
		super();
		this.vision = vision;
	}
	
	@Override
	public void descubrirMapa(Jugador propietario, Controlable controlable) {
		
		Construccion construccion = (Construccion) controlable;
		Mapa mapa = Juego.getInstance().getMapa();
		Iterator<Celda> it = null;
		
		it = construccion.obtenerRangoDeOcupacion().iterator();
		
		if (it != null) {
		
			while (it.hasNext()) {
				
				Coordenada posicion = it.next().getPosicion();
				Collection<Celda> descubierto = mapa.obtenerRangoRadialDeCeldas(posicion, this.vision);
				Iterator<Celda> itDesc = descubierto.iterator();
				while (itDesc.hasNext()) {
					itDesc.next().agregarObservador(controlable);
				}

				propietario.mapaDescubierto(descubierto);
				
			}
			
		}
		
	}
	
	@Override
	public void atacar(Ataques ataques, Controlable victima) {
		//Las construcciones no pueden atacar
	}
	
	@Override
	public boolean estaEnRangoDeAtaque(Ataques ataques, Coordenada ubicacionAgresor, Controlable victima) {
		//Las construcciones no puede atacar.
		return false;		
	}
	
	@Override
	public boolean puedeOcupar(Controlable controlable, Celda celda) {
		
		Construccion construccion = (Construccion) controlable;
		
		if (!celda.getMaterial().equals(Material.tierra)) return false;
		
		if (celda.poseeBase()) return false;
		
		if (construccion.puedeExtraerRecursos()) {
			
			if (!celda.poseeRecurso()) return false;
			if (!celda.getRecurso().puedeRecolectar((ConstruccionRecolectora)construccion)) return false;
			
		} else {
			if (celda.poseeRecurso()) return false;
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

		Collection<Celda> celdasOcupadas = ((Construccion)controlable).obtenerRangoDeOcupacion();
			
		Iterator<Celda> it = celdasOcupadas.iterator();
		
		if (it != null) {
			
			while (it.hasNext()) {
					
				it.next().desocupar(((Construccion)controlable));
				
			}
			
		}
		
	}

	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return (movimientoDesconocido.colisionaCon(this));
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
