package juego.estrategias;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.ataques.Ataques;

public abstract class EstrategiaMovimiento {

	protected int vision;
	
	public boolean visionSuficiente(Coordenada posicion, Coordenada coordFinal) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		return (mapa.distanciaEntreCoordenadas(posicion, coordFinal) <= this.vision);
		
	}
	public abstract void descubrirMapa(Jugador propietario, Controlable controlable);
	
	public abstract void atacar(Ataques ataques, Controlable victima);
	public abstract boolean estaEnRangoDeAtaque(Ataques ataques, Coordenada ubicacionAgresor, Controlable victima);
	
	public abstract void moverse(Jugador controlador, Controlable controlable, Coordenada coordFinal) throws UbicacionInvalida;
	public abstract boolean puedeOcupar(Controlable controlable, Celda celda);
	
	//Por defecto no puede alcanzar ninguna distancia
	public boolean distanciaAlcanzable(int distanciaAMover) {
		return false;
	}
	
	public abstract void desocupar(Controlable controlable);

	
	public abstract boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido);
	
	//Por defecto no colisiona con otros movimientos
	public boolean colisionaCon(MovimientoVolador volador) {
		return false;
	}
	public boolean colisionaCon(MovimientoTerrestre terrestre) {
		return false;
	}
	public boolean colisionaCon(MovimientoConstruccion construccion) {
		return false;
	}
	
}
