package juego.razas.construcciones.terran;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Vida;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereFabrica;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.terran.Espectro;
import juego.razas.unidades.terran.NaveCiencia;
import juego.razas.unidades.terran.NaveTransporte;

public class PuertoEstelar extends ConstruccionMilitar {

	public PuertoEstelar() {
		super();
		this.vida = new Vida(1300);
		this.bolsaDeCostos = new BolsaDeCostos(150,100,10,0);
	}
	
	@Override
	public void construir(JugadorTerran jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		Mapa mapa = Juego.getInstance().getMapa();
				
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		if (!jugador.puertoEstelarHabilitado()) throw new RequiereFabrica();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 3);
		Iterator<Celda> it = rangoDeCeldas.iterator();
		
		try {
			while (it.hasNext()) {
				Celda celda = it.next();
				if ((celda.poseeRecursos()) || (!celda.puedeConstruir(this))) throw new UbicacionInvalida();
				celda.ocupar(this);
			}
		} catch (UbicacionInvalida ui) {
			it = rangoDeCeldas.iterator();
			while (it.hasNext()) {
				it.next().desocupar(this);
			}
			throw new UbicacionInvalida();
		}
	
		this.bolsaDeCostos.consumirRecursos(jugador);
		
		this.posicion = coordenada;
		this.propietario = jugador;
			
	}

	public void entrenar(Espectro espectro) throws RecursosInsuficientes, SobrePoblacion {
		espectro.iniciarEntrenamiento();
		this.entrenamientos.add(espectro);
	}
	
	public void entrenar(NaveCiencia naveCiencia) throws RecursosInsuficientes, SobrePoblacion {
		naveCiencia.iniciarEntrenamiento();
		this.entrenamientos.add(naveCiencia);
	}

	public void entrenar(NaveTransporte naveTransporte) throws RecursosInsuficientes, SobrePoblacion {
		naveTransporte.iniciarEntrenamiento();
		this.entrenamientos.add(naveTransporte);
		
	}
	
}
