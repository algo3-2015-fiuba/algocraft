package juego.jugadores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Hospedable;
import juego.interfaces.Jugable;
import juego.interfaces.Militable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public abstract class Jugador implements Jugable {

	protected String nombre;
	protected Color color;
	protected Collection<Construible> enConstruccion;
	protected Collection<Construible> construcciones;
	protected Collection<Unidad> unidades;
	protected int mineralesRecolectados;
	protected int gasVespenoRecolectado;
	
	public Jugador(String nombre, Color color) {
		super();
		this.nombre = nombre;
		this.color = color;
		this.construcciones = new ArrayList<Construible>();
		this.enConstruccion = new ArrayList<Construible>();
		this.unidades = new ArrayList<Unidad>();
		this.mineralesRecolectados = 200;
		this.gasVespenoRecolectado = 0;
	}
		
	public Color getColor() { return this.color; }
	public String getNombre() { return this.nombre; }
	
	public void finalizarTurno() {		
		Juego.getInstance().finalizarTurno();		
	}
	
	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes {	
		if (this.mineralesRecolectados < costoMinerales) throw new RecursosInsuficientes();		
		this.mineralesRecolectados -= costoMinerales;
	}
	
	public void consumirGasVespeno(int costoGas) throws RecursosInsuficientes {	
		if (this.gasVespenoRecolectado < costoGas) throw new RecursosInsuficientes();		
		this.gasVespenoRecolectado -= costoGas;
	}
	
	public boolean mineralesSuficientes(int cantidad) { return (cantidad <= this.mineralesRecolectados); }
	public boolean gasVespenoSuficiente(int cantidad) { return (cantidad <= this.gasVespenoRecolectado); }

	public void recolectarMinerales(int cantidad) { this.mineralesRecolectados += cantidad; }
	public void recolectarGasVespeno(int cantidad) { this.gasVespenoRecolectado += cantidad; }

	public int getMineralesRecolectados() { return this.mineralesRecolectados;	}
	public int getGasVespenoRecolectado() { return this.gasVespenoRecolectado;	}
	
	public void construir(Construible construible, Coordenada cordenada) throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {}
	
	public void actualizarObservadores() {
		this.actualizarConstrucciones();
		this.recolectarRecursos();
		this.actualizarEntrenamientos();
	}
	
	public int limiteDePoblacion() {
		
		Collection<Hospedable> hospedables = this.getHospedables();
		int limiteDePoblacion = 0;
		
		Iterator<Hospedable> it = hospedables.iterator();
		while (it.hasNext()) {
			limiteDePoblacion += it.next().capacidadDeHabitantes();
		}
		
		return (limiteDePoblacion > 200) ? 200 : limiteDePoblacion;
		
	}
	
	public int poblacionActual() {	
		return this.unidades.size();
	}
	
	protected Collection<Recolector> getRecolectores() {
		
		Collection<Recolector> recolectores = new ArrayList<Recolector>();
 		Iterator<Construible> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construible construible = it.next();
			if (construible.puedeExtraerRecursos())  {
				recolectores.add((Recolector)construible);
			}
			
		}
		
		return recolectores;
		
	}
	
	protected Collection<Hospedable> getHospedables() {
		
		Collection<Hospedable> hospedables = new ArrayList<Hospedable>();
 		Iterator<Construible> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construible construible = it.next();
			if (construible.puedeHospedarUnidades())  {
				hospedables.add((Hospedable)construible);
			}
			
		}
		
		return hospedables;
		
	}
	
	protected Collection<Militable> getMilitables() {
		
		Collection<Militable> militables = new ArrayList<Militable>();
 		Iterator<Construible> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construible construible = it.next();
			if (construible.puedeEntrenarUnidades())  {
				militables.add((Militable)construible);
			}
			
		}
		
		return militables;
		
	}
	
	private void recolectarRecursos() {
		
		Iterator<Recolector> it = this.getRecolectores().iterator();
		
		while (it.hasNext()) {
			it.next().recolectar();
		}
		
	}
	
	private void actualizarEntrenamientos() {
				
		Iterator<Militable> it = this.getMilitables().iterator();
			
		while (it.hasNext()) {			
			it.next().actualizarEntrenamientos();		
		}

	}
	
	private void actualizarConstrucciones() {
			
		Collection<Construible> construccionesFinalizadas = new ArrayList<Construible>();
			
		Iterator<Construible> it = this.enConstruccion.iterator();
			
		while (it.hasNext()) {			
			Construible construible = it.next();
			construible.actualizarConstruccion();
			if (construible.construccionFinalizada()) {
				construccionesFinalizadas.add(construible);	
				construcciones.add(construible);
			}
		}
			
		this.enConstruccion.removeAll(construccionesFinalizadas);
		
	}
	
}
