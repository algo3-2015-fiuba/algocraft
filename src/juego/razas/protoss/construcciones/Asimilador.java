package juego.razas.protoss.construcciones;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.jugadores.Jugador;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Asimilador extends EdificioRecolector implements Construible, Recolector, Controlable {
	
	GasVespeno nodoGasVespeno;
	int tiempoDeConstruccion;
	Jugador propietario;
	int vida;
	
	public Asimilador(Recurso recurso) {
		super();
		this.nodoGasVespeno = (GasVespeno) recurso;
		this.vida = 0;
		this.tiempoDeConstruccion = 0;
		this.propietario = Juego.getInstance().turnoDe();
	}

	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 75; 
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
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 6);
	}

}
