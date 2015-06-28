package juego.estrategias;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.materiales.Material;
import juego.razas.unidades.Unidad;

public class MovimientoTerrestre implements EstrategiaMovimiento {

	private int rangoDeMovimiento;
	private int vision;
	
	public MovimientoTerrestre(int rangoDeMovimiento, int vision) {
	
		this.rangoDeMovimiento = rangoDeMovimiento;
		this.vision = vision;
		
	}
	
	@Override
	public boolean visionSuficiente(Coordenada posicion, Coordenada coordFinal) {
		Mapa mapa = Juego.getInstance().getMapa();
		return (mapa.distanciaEntreCoordenadas(posicion, coordFinal) <= this.vision);
	}
	
	@Override
	public void descubrirMapa(Jugador propietario, Controlable controlable) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada posicion = mapa.obtenerUbicacion((Unidad) controlable);
		
		propietario.mapaDescubierto(mapa.obtenerRangoRadialDeCeldas(posicion, this.vision));
		
	}
	
	@Override
	public boolean distanciaAlcanzable(int distanciaAMover) {
		return (distanciaAMover <= this.rangoDeMovimiento);
	}
	
	@Override
	public boolean puedeOcupar(Controlable controlable, Celda celda) {
		
		if (!celda.getMaterial().equals(Material.tierra)) return false;
		
		if (celda.poseeBase()) return false;
		
		if (celda.poseeRecursos()) return false;
		
		if (celda.colisiona(controlable)) return false;
		
		return true;
		
	}
	
	@Override
	public void moverse(Jugador controlador, Controlable controlable, Coordenada coordFinal) throws UbicacionInvalida {
		
		Unidad unidad = (Unidad) controlable;
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Coordenada coordInicial = mapa.obtenerUbicacion(unidad);
			
		if (coordInicial != null) mapa.obtenerCelda(coordInicial).desocupar(unidad);
			
		mapa.obtenerCelda(coordFinal).ocupar(unidad);	
		
		this.descubrirMapa(controlador, controlable);

	}
	
	@Override
	public void desocupar(Controlable controlable) {
		
		try {
			
			Mapa mapa = Juego.getInstance().getMapa();
			Coordenada coordenada = mapa.obtenerUbicacion((Unidad)controlable);
			if (coordenada != null) mapa.obtenerCelda(coordenada).desocupar((Unidad)controlable);
			
		} catch (CoordenadaFueraDeRango cfdr) {
			//Esto no deberia ocurrir nunca a que si el mapa encontro la ubicacion del controlable, la coordenada deberia ser valida.
		}
	}
	
	@Override
	public boolean colisionaCon(EstrategiaMovimiento movimientoDesconocido) {
		return movimientoDesconocido.colisionaCon(this);
	}

	@Override
	public boolean colisionaCon(MovimientoTerrestre terrestre) {
		return true;
	}
	
	@Override
	public boolean colisionaCon(MovimientoVolador volador) {
		return false;
	}
	
	@Override
	public boolean colisionaCon(MovimientoConstruccion construccion) {
		return true;
	}
	
}
