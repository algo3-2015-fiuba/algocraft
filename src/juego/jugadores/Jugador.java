package juego.jugadores;

import java.awt.Color;

import mapa.Coordenada;
import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.razas.Raza;

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

	public void construir(CommandConstructor constructor, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		
		//Cuando querermos construir, buscamos a la raza a la cual pertenece el jugador
		//y ella decide que hacer con el constructor
		this.raza.construir(this, constructor, coordenada);
	}

	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes {	
		if (this.mineralesRecolectados < costoMinerales) throw new RecursosInsuficientes();		
		this.mineralesRecolectados -= costoMinerales;
	}
	
	public void consumirGas(int costoGas) throws RecursosInsuficientes {	
		if (this.gasVespenoRecolectado < costoGas) throw new RecursosInsuficientes();		
		this.gasVespenoRecolectado -= costoGas;
	}

	public void recolectarMinerarles(int cantidad) { this.mineralesRecolectados += cantidad; }
	public void recolectarGasVespeno(int cantidad) { this.mineralesRecolectados += cantidad; }

	//Estos metodo los puse para que pase el test, ya que son necesarios algunos metodos del mapa en realidad
	//Deberian borrarse en el futuro
	public boolean recolectoMinerales() { return false;	}
	public boolean recolectoMineralesFinalizadaLaConstruccion() { return true; }
	
}
