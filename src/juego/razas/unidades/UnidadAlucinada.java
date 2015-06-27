
package juego.razas.unidades;

import java.util.Iterator;

import juego.Juego;
import juego.decoradores.Escudo;
import juego.interfaces.Controlable;
import juego.interfaces.EstrategiaMovimiento;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public class UnidadAlucinada extends Unidad {

	private Unidad alucinada;
	
	public UnidadAlucinada(Unidad alucinada) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		this.alucinada = alucinada;
		this.estrategiaDeMovimiento = alucinada.getMovimiento();
		this.propietario = Juego.getInstance().turnoDe();
		this.ubicar(mapa.obtenerUbicacion(alucinada));
		this.propietario.asignarUnidad(this);
		this.vida = new Escudo(alucinada.vidaActual());
	}
	
	/* * * * * * * * * * * * * 
	 *                       *
	 *  Informacion basica   *
	 *                       *
	 * * * * * * * * * * * * */
	
	private void ubicar(Coordenada posicionCentral) {
		
		boolean ocupado = false;
		Iterator<Celda> celdasAOcupar = Juego.getInstance().getMapa().obtenerRangoRadialDeCeldas(posicionCentral, 5).iterator();
		
		Celda celdaPosible = null;
		
		while ((celdasAOcupar.hasNext()) && (!ocupado)) {
		
			celdaPosible = celdasAOcupar.next();
		
			Iterator<Unidad> unidadesEnCelda = celdaPosible.getUnidades().iterator();
			boolean colisiona = false;
			while (unidadesEnCelda.hasNext()) {
				Unidad unidad = unidadesEnCelda.next();
				if (unidad.colisionaCon(this.estrategiaDeMovimiento)) colisiona = true;
			}
			
			if (!colisiona) ocupado = true;
		
		}
		
		if ((!ocupado) && (celdaPosible != null)) celdaPosible.ocupar(this);
	}
	
	@Override
	public int suministrosNecesarios() { return 0; }	

	/* * * * * * * * * *
	 *                 *
	 * Entrenamiento   *
	 *                 *
	 * * * * * * * * * */
	
	@Override
	public void actualizarEntrenamiento() {
		//Las unidades alucinadas no se entrenan
	}
	
	@Override
	public boolean entrenamientoFinalizado() {
		return true;
	}
	
	/* * * * * * * * *
	 *               *
	 * Movimientos   *
 	 *               *
	 * * * * * * * * */
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		
		this.estrategiaDeMovimiento.moverse(this.propietario, this, coordFinal);
		this.posicion = coordFinal;

	}
	
	@Override
	public boolean colisionaCon(Controlable controlable) { 
		return controlable.colisionaCon(this.alucinada); 
	}
	
	@Override
	public boolean colisionaCon(EstrategiaMovimiento estrategiaDeOtro) { 
		return this.alucinada.colisionaCon(estrategiaDeOtro); 
	}
	
	@Override
	protected void morir() {
		
		this.propietario.fallecida(this);
		try {
			Mapa mapa = Juego.getInstance().getMapa();
			Celda celda = mapa.obtenerCelda(mapa.obtenerUbicacion(this));
			if (celda != null) celda.desocupar(this);
		} catch (CoordenadaFueraDeRango cfdr) {
			//No deberia suceder nunca esto.
		}
		
	}

	public void originalMuerto() {
		this.morir();
	}
	
}
