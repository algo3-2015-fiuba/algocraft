package juego.estrategias;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;

public class MovimientoTerrestre implements EstrategiaMovimiento {

	private int rangoDeMovimiento;
	private int vision;
	
	public MovimientoTerrestre(int rangoDeMovimiento, int vision) {
	
		this.rangoDeMovimiento = rangoDeMovimiento;
		this.vision = vision;
		
	}
	
	@Override
	public int getVision() { return this.vision; }
	
	@Override
	public void moverse(Controlable controlable, Coordenada coordFinal) throws UbicacionInvalida {
		
		Unidad unidad = (Unidad) controlable;
		
		if (this.puedeMoverse(unidad, coordFinal)) {
			
			Mapa mapa = Juego.getInstance().getMapa();
		
			Coordenada coordInicial = mapa.obtenerUbicacion(unidad);
				
			if (coordInicial != null) mapa.obtenerCelda(coordInicial).desocupar(unidad);
				
			mapa.obtenerCelda(coordFinal).ocupar(unidad);	
		
		} else {
			throw new UbicacionInvalida();
		}

	}
	
	private boolean puedeMoverse(Unidad unidad, Coordenada coordFinal) throws UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Coordenada ubicacionUnidad = mapa.obtenerUbicacion(unidad);
		
		if (ubicacionUnidad == null) return true;
		
		if (!mapa.obtenerCelda(coordFinal).puedeOcuparTierra(unidad)) return false;
		
		int distanciaAMover = mapa.distanciaEntreCoordenadas(ubicacionUnidad, coordFinal);
		
		return (distanciaAMover <= this.rangoDeMovimiento);
		
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
