
package juego.razas.unidades;

import java.util.Iterator;

import juego.Juego;
import juego.decoradores.Escudo;
import juego.estrategias.EstrategiaMovimiento;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public class UnidadAlucinada extends Unidad {

	private Unidad alucinada;
	
	public UnidadAlucinada(Unidad alucinada, EstrategiaMovimiento estrategiaDeMovimiento, float escudoMaximo) {
		
		super();		
		this.alucinada = alucinada;
		this.estrategiaDeMovimiento = estrategiaDeMovimiento;
		this.propietario = Juego.getInstance().turnoDe();
		this.propietario.asignarUnidad(this);
		this.vida = new Escudo(escudoMaximo);
		
	}
	
	/* * * * * * * * * * * * * 
	 *                       *
	 *  Informacion basica   *
	 *                       *
	 * * * * * * * * * * * * */
	
	public float escudoActual() {
		return this.vida.nivelActual();
	}
	
	public float vidaActual() {
		return 0;
	}
	
	public Unidad unidadAlucinada() {
		return this.alucinada;
	}
	
	@Override
	public int suministrosNecesarios() { return 0; }	
	
	@Override
	public int pesoTransporte() {
		return this.alucinada.pesoTransporte();
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
	
	public void ubicar(Coordenada posicionCentral) {
		
		//Este metodo trata de ubicar a la unidad alucinada en un rango radial de 5.
		Iterator<Celda> celdasPosiblesDeOcupacion = Juego.getInstance().getMapa().obtenerRangoRadialDeCeldas(posicionCentral, 3).iterator();
		boolean ubicado = false;
		
		while ((celdasPosiblesDeOcupacion.hasNext()) && (!ubicado)) {
			
			Celda celdaPosible = celdasPosiblesDeOcupacion.next();
			
			if ((celdaPosible != null) && (!celdaPosible.colisiona(this))) {
				celdaPosible.ocupar(this);
				ubicado = true;
			}
			
		}
		
		if (ubicado) {
			this.estrategiaDeMovimiento.descubrirMapa(this.propietario, this);
		}
		
	}
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		
		this.estrategiaDeMovimiento.moverse(this.propietario, this, coordFinal);
		this.posicion = coordFinal;

	}
	
	@Override
	public boolean colisionaCon(Controlable controlable) { 
		return controlable.colisionaCon(this.estrategiaDeMovimiento); 
	}
	
	@Override
	public boolean colisionaCon(EstrategiaMovimiento estrategiaDeOtro) { 
		return this.estrategiaDeMovimiento.colisionaCon(estrategiaDeOtro); 
	}
	
	@Override
	public void recibirAtaque(float danio) {
		this.vida.daniar(danio);
		if (((Escudo)this.vida).escudoAgotado()) {
			this.morir();
		}
	}
	
	@Override
	protected void morir() {
		
		this.propietario.fallecido(this);
		try {
			Mapa mapa = Juego.getInstance().getMapa();
			Celda celda = mapa.obtenerCelda(mapa.obtenerUbicacion(this));
			if (celda != null) celda.desocupar(this);
		} catch (CoordenadaFueraDeRango cfdr) {}
		
	}

	public void originalMuerto() {
		this.morir();
	}

}
