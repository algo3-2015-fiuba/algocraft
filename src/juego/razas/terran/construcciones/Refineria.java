package juego.razas.terran.construcciones;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Refineria implements Construible, Recolector, Controlable {
	
	private GasVespeno nodoGasVespeno;
	private int tiempoDeConstruccion;
	private Jugador propietario;
	private int vida;
	
	public Refineria(Recurso recurso) {
		super();
		this.nodoGasVespeno = (GasVespeno) recurso;
		this.vida = 0;
		this.tiempoDeConstruccion = 0;
		this.propietario = Juego.getInstance().turnoDe();
	}

	@Override
	public boolean construccionFinalizada() { return (this.tiempoDeConstruccion == 6); }
	
	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 125; 
			this.tiempoDeConstruccion++;
		}
	}
	
	@Override
	public void recolectar() throws RecursoAgotado {

		if (!this.nodoGasVespeno.estaAgotado()) {
			int extraidos = this.nodoGasVespeno.extraer();
			Juego.getInstance().turnoDe().recolectarGasVespeno(extraidos);		
		}
		
	}
	
	@Override
	public boolean esPropietario(Jugador aComprobar) {
		return (this.propietario.equals(aComprobar));
	}

	@Override
	public void ocuparCelda(Celda celda) throws CeldaOcupada{
		celda.ocuparTierra(this);
	}

	@Override
	public void moverse(Coordenada coordFinal) throws ConstruccionesNoSeMueven, PropietarioInvalido {
		
		if (!this.esPropietario(Juego.getInstance().turnoDe())) { throw new PropietarioInvalido(); }
		
		throw new ConstruccionesNoSeMueven();
	}

}