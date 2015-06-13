package juego.razas.unidades;

import juego.Juego;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.interfaces.Atacable;
import juego.interfaces.Atacante;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionHabitable;

public abstract class Unidad implements Controlable, Atacable, Atacante {
	
	protected Jugador propietario;
	protected float vida;
	protected boolean esVolador;
	protected int suministro;
	protected Celda celdaOcupada;
	protected int transporte;
	protected int vision;
	protected int ataqueTerrestre;
	protected int ataqueAereo;
	
	public Unidad(Jugador propietario) {
		this.propietario = propietario;
	}
	
	public float vidaRestante() {
		return this.vida;
	}
	
	public boolean estaMuerto() {
		return this.vida <= 0.0;
	}

	@Override
	public void ocuparCelda(Celda celda) throws CeldaOcupada {
		if(this.esVolador) {
			celda.ocuparAire(this);
		} else {
			celda.ocuparTierra(this);
		}
		
		this.celdaOcupada = celda;
	}

	@Override
	public void moverse(Coordenada coordFinal) throws CeldaOcupada,
			CoordenadaFueraDeRango, ConstruccionesNoSeMueven,
			PropietarioInvalido {
		Mapa mapa = Juego.getInstance().getMapa();
		
		Celda actual = this.celdaOcupada;
		Celda destino = mapa.obtenerCelda(coordFinal);
		
		int distancia = mapa.distanciaEntreCeldas(actual, destino);
		
		if(distancia <= this.transporte) {
			destino.agregarControlable(this);
			
			if(this.esVolador) {
				actual.removerUnidadEnAire();
			} else {
				actual.removerUnidadEnTierra();
			}
			
			this.celdaOcupada = destino;
		} else {
			throw new CoordenadaFueraDeRango();
		}
	}
	
	private void morir() {
		if(this.esVolador) {
			this.celdaOcupada.removerUnidadEnAire();
		} else {
			this.celdaOcupada.removerUnidadEnTierra();
		}
	}
	
	public void atacar(Atacable destino) throws YaFueDestruido {
		destino.recibirAtaqueAereo(this.ataqueTerrestre);
		destino.recibirAtaqueTerrestre(this.ataqueTerrestre);
	}
	
	private void recibirAtaque(int cantidad) {
		
		this.vida -= (float) cantidad;
		
		if(this.vida <= 0) {
			this.morir();
		}
	}
	
	@Override
	public void recibirAtaqueTerrestre(int cantidad) {
		if(!this.esVolador) {
			this.recibirAtaque(cantidad);
		}
	}
	
	@Override
	public void recibirAtaqueAereo(int cantidad) {
		if(this.esVolador) {
			this.recibirAtaque(cantidad);
		}
	}

	public boolean chocan(Construible construible) {
		return (this.esVolador);
	}
	
}
