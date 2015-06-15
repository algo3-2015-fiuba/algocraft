package juego.razas.unidades.terran;

import juego.Juego;
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

public class Marine extends UnidadComun implements Terrestre {

	public Marine() {
		super();
		this.transporte = 1;
		this.vision = 7;
		this.costoMinerales = 50;
		this.tiempoDeConstruccion = 3;
		this.danioAire = 6;
		this.danioTierra = 6;
		this.suministro = 1;
		this.rangoAtaqueAire = 4;
		this.rangoAtaqueTierra = 4;
		this.esTerrestre = true;
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Barraca)construccion).entrenar(this);
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
		if (!jugador.bolsaDeRecursos().mineralesSuficientes(this.costoMinerales)) {
			throw new RecursosInsuficientes();
		}
			
		if (!jugador.suministrosSuficientes(this.suministro)) {
			throw new SobrePoblacion();
		}
		
		jugador.bolsaDeRecursos().consumirMinerales(this.costoMinerales);
		
	}
	
	@Override
	public void actualizarEntrenamiento() {
		if (!entrenamientoFinalizado()) {
			this.vida += 13.33;
			this.tiempoDeConstruccion--;
		}
	}
	
}
