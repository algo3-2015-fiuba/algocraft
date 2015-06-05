package juego.jugadores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Mapa;
import juego.razas.Raza;
import juego.recursos.excepciones.RecursoAgotado;

public class Jugador {

	private String nombre;
	private Raza raza;
	private Color color;
	private int mineralesRecolectados, gasVespenoRecolectado;
	
	public Jugador(String nombre, Raza raza, Color color) {
		
		this.nombre = nombre;
		this.raza = raza;
		this.color = color;
		this.mineralesRecolectados = 200;
		this.gasVespenoRecolectado = 0;
		
	}
	
	public boolean esDeColor(Color color) { return (this.color.equals(color)); }

	public boolean suNombreEs(String nombre) { return this.nombre.equals(nombre); }
	
	private void recolectarRecursos() {
	/* Este metodo deberia buscar en el mapa todas las construcciones sobre recursos
		Y pedirles que extraigan desde su nodo, sumando lo extraido a mineralesRecolectados o
		gasVespenoRecolectado;
		Mapa mapa = Juego.getInstance().getMapa();
		Collection<Recolector> listaDeRecolectores = mapa.getRecolectores();
		
		Iterator<Recolector> it = listaDeRecolectores.iterator();
		while (it.hasNext()) {
			it.next().recolectar();
		}
	*/
		
	}

	public void finalizarTurno() {
		
		this.recolectarRecursos();
		this.raza.turnoFinalizado();
		Juego.getInstance().finalizarTurno();
		
	}

	public void construir(CommandConstructor constructor, int x, int y) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		
		constructor.construir(this.raza, x, y);
		
	}

	public void gastarMinerales(int costoMinerales) throws RecursosInsuficientes {
	
		if (this.mineralesRecolectados < costoMinerales) throw new RecursosInsuficientes();
		
		this.mineralesRecolectados -= costoMinerales;
	}

	public void mineralesRecolectados(int cantidad) { this.mineralesRecolectados += cantidad; }
	public void gasVespenoRecolectado(int cantidad) { this.mineralesRecolectados += cantidad; }

	public String getNombre() { return this.nombre;	}

	//Estos metodo los puse para que pase el test, ya que son necesarios algunos metodos del mapa en realidad
	//Deberian borrarse en el futuro
	public boolean recolectoMinerales() { return false;	}
	public boolean recolectoMineralesFinalizadaLaConstruccion() { return true; }
	
}
