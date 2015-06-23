package juego.razas.construcciones.protoss;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.informadores.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.protoss.NaveTransporte;
import juego.razas.unidades.protoss.Scout;

public class PuertoEstelar extends ConstruccionMilitar {
	
	public PuertoEstelar() {
		super();
		this.vida = new Escudo(new Vida(600), 600);
		this.costos = new Costos(150,150,10,0);
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		Mapa mapa = Juego.getInstance().getMapa();
				
		if (!this.costos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		if (!jugador.puertoEstelarHabilitado()) throw new RequiereAcceso();
		
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
	
		this.costos.consumirRecursos(jugador);
		
		this.posicion = coordenada;
		this.propietario = jugador;
			
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.costos.disminuirTiempoDeConstruccion();	
			if (this.construccionFinalizada()) {
				((JugadorProtoss)this.propietario).activarArchivoTemplario(true);
			}
		}	
	}

	public void entrenar(NaveTransporte naveTransporte) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(naveTransporte);
		}
	}

	public void entrenar(Scout scout) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(scout);	
		}
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 2, 3);
	}
	
}
