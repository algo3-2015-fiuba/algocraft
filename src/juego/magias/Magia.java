package juego.magias;

import juego.energia.Energia;
import juego.interfaces.excepciones.EnergiaInsuficiente;
import juego.jugadores.Jugador;
import juego.razas.unidades.Unidad;

public abstract class Magia {

	protected int costoEnergia;
	protected Jugador propietario;
	
	public Magia() {
		
		super();
		this.costoEnergia = 0;
		this.propietario = null;
		
	}
	
	public boolean energiaSuficiente(Energia energia) {
		return (energia.energiaSuficiente(this.costoEnergia));
	}
	
	public void consumir(Energia energia) throws EnergiaInsuficiente {
		energia.consumir(this.costoEnergia);
	}

	public abstract void activar();
	
	public abstract void afectar(Unidad unidad);

	public abstract boolean activa();
	
}
