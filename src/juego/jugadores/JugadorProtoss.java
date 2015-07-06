package juego.jugadores;

import java.awt.Color;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.RequierePuertoEstelar;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.construcciones.protoss.ArchivoTemplario;
import juego.razas.construcciones.protoss.Asimilador;
import juego.razas.construcciones.protoss.BaseProtoss;
import juego.razas.construcciones.protoss.NexoMineral;
import juego.razas.construcciones.protoss.Pilon;
import juego.razas.construcciones.protoss.PuertoEstelarProtoss;

public class JugadorProtoss extends Jugador {

	private int puertoEstelarHabilitado, archivoTemplarioHabilitado;
	
	public JugadorProtoss(String nombre, Color color) {
		
		super(nombre, color);
		this.puertoEstelarHabilitado = 0;
		this.archivoTemplarioHabilitado = 0;
		
	}

	public boolean puertoEstelarHabilitado() { return (this.puertoEstelarHabilitado > 0); }
	public boolean archivoTemplarioHabilitado() { return (this.archivoTemplarioHabilitado > 0); }
	
	public void activarPuertoEstelar(boolean puertoEstelarActivo) {
		if (puertoEstelarActivo) this.puertoEstelarHabilitado++;
		else this.puertoEstelarHabilitado--;
	}
	
	public void activarArchivoTemplario(boolean fabricaActiva) {
		if (fabricaActiva) this.archivoTemplarioHabilitado++;
		else archivoTemplarioHabilitado--;
	}
	
	@Override
	public BaseProtoss crearBase(Coordenada posicion) throws UbicacionInvalida {
		return new BaseProtoss(this, posicion);
	}

	public void construir(NexoMineral nexoMineral, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(nexoMineral, posicion);
		
	}
	
	public void construir(Asimilador asimilador, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(asimilador, posicion);
		
	}
	
	public void construir(Pilon pilon, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(pilon, posicion);
		
	}
	
	public void construir(Acceso acceso, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(acceso, posicion);
		
	}
	
	public void construir(PuertoEstelarProtoss puertoEstelar, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		if (!this.puertoEstelarHabilitado()) throw new RequiereAcceso();
		this.constructor(puertoEstelar, posicion);
		
	}
	
	public void construir(ArchivoTemplario archivoTemplario, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		if (!this.archivoTemplarioHabilitado()) throw new RequierePuertoEstelar();
		this.constructor(archivoTemplario, posicion);
		
	}
	
}
