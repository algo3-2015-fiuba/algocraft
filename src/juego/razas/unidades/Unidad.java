package juego.razas.unidades;

import juego.Juego;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;

public abstract class Unidad implements Controlable {
	
	protected Jugador propietario;
	protected float vida;
	protected boolean esVolador;
	protected int suministro;
	protected Celda celdaOcupada;
	protected int transporte;
	
	public Unidad(Jugador propietario) {
		this.propietario = propietario;
	}

	@Override
	public boolean esPropietario(Jugador jugador) {
		return (this.propietario.equals(jugador));
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

}
