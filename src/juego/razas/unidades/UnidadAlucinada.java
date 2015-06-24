package juego.razas.unidades;

import juego.Juego;
import juego.decoradores.Escudo;
import juego.informadores.Costos;
import juego.interfaces.Controlable;
import juego.interfaces.estrategias.EstrategiaMovimiento;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public class UnidadAlucinada extends Unidad {

	private Unidad alucinada;
	
	public UnidadAlucinada(Unidad unidadACopiar) {
		this.alucinada = unidadACopiar;
		this.propietario = Juego.getInstance().turnoDe();
		this.propietario.asignarUnidad(this);
		this.posicion = null;
		this.pesoTransporte = this.alucinada.pesoTransporte();
		this.vida = new Escudo(this.alucinada.vidaActual());
		this.vision = unidadACopiar.vision();
		this.rangoDeMovimiento = unidadACopiar.rangoDeMovimiento();
	}
	
	/* * * * * * * * * * * * * 
	 *                       *
	 *  Informacion basica   *
	 *                       *
	 * * * * * * * * * * * * */
	
	@Override
	public int suministrosNecesarios() { return 0; }
	
	@Override
	public int pesoTransporte() {
		return this.alucinada.pesoTransporte();
	}
	
	@Override
	public Costos costos() {
		return null;
	}
	

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
			celda.desocupar(this);
		} catch (CoordenadaFueraDeRango cfdr) {
			//No deberia suceder nunca esto.
		}
		
	}
	
}
