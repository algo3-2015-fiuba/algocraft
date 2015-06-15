package juego.razas.unidades.terran;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.interfaces.Terrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.unidades.UnidadComun;

public class Golliat extends UnidadComun implements Terrestre {

	public Golliat() {
		super();
		this.transporte = 2;
		this.vision = 8;
		this.suministro = 1;
		this.bolsaDeCostos = new BolsaDeCostos(50,0,3);
		this.bolsaDeAtaque = new BolsaDeAtaque(12,10,6,5);
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Fabrica)construccion).entrenar(this);
	}
	
	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {

		Mapa mapa = Juego.getInstance().getMapa();
		
		if (this.posicion == null) {
			mapa.obtenerCelda(coordFinal).ocupar(this);
		} else {
			//Esta parte esta implementada la idea pero habria que verificar que puede avanzar hasta ahi segun el supuesto.
			mapa.obtenerCelda(this.posicion).desocupar(this);
			mapa.obtenerCelda(coordFinal).ocupar(this);
		}
		
	}
	
	public void iniciarEntrenamiento() throws RecursosInsuficientes, SobrePoblacion {
		
		Jugador jugador = Juego.getInstance().turnoDe();
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) { throw new RecursosInsuficientes(); }
			
		if (!jugador.suministrosSuficientes(this.suministro)) { throw new SobrePoblacion();	}
		
		this.bolsaDeCostos.consumirRecursos(jugador);
		
	}
	
	@Override
	public void actualizarEntrenamiento() {
		if (!entrenamientoFinalizado()) {
			this.vida += 13.33;
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
		}
	}
	
}
