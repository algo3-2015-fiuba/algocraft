package juego.jugadores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.informadores.RecursosJugador;
import juego.informadores.VisionJugador;
import juego.interfaces.Construible;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionHabitable;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.razas.unidades.Unidad;

public abstract class Jugador {

	protected String nombre;
	protected Color color;
	protected Collection<Construible> enConstruccion;
	protected Collection<Construible> construcciones;
	protected Collection<Unidad> unidades;
	protected RecursosJugador recursos;
	protected VisionJugador vision;
	
	public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
		this.construcciones = new ArrayList<Construible>();
		this.enConstruccion = new ArrayList<Construible>();
		this.unidades = new ArrayList<Unidad>();
		this.recursos = new RecursosJugador();
		this.vision = new VisionJugador(this.unidades);
	}
	
	public Color getColor() { return this.color; }
	public String getNombre() { return this.nombre; }
	
	public void finalizarTurno() {	
		Juego.getInstance().finalizarTurno();		
	}
		
	public boolean mineralesSuficientes(int cantidad) { return this.recursos.mineralesSuficientes(cantidad); }
	public boolean gasVespenoSuficiente(int cantidad) { return this.recursos.gasVespenoSuficiente(cantidad); }
	public boolean suministrosSuficientes(int cantidad) { 
		
		this.recursos.poblacionActual(this.unidades, this.getMilitables());
		this.recursos.limiteDePoblacion(this.getHospedables());
		
		return this.recursos.suministrosSuficientes(cantidad); 
		
	}
	
	
	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes { 
		this.recursos.consumirMinerales(costoMinerales); 
		}
	
	public void consumirGasVespeno(int costoGasVespeno) throws RecursosInsuficientes {
		this.recursos.consumirGasVespeno(costoGasVespeno);
	}
	
	public void recolectarGasVespeno(int extraidos) {
		this.recursos.recolectarGasVespeno(extraidos);		
	}
	
	public void recolectarMinerales(int extraidos) {
		this.recursos.recolectarMinerales(extraidos);		
	}
	
	public int getGasVespenoRecolectado() {
		return this.recursos.getGasVespenoRecolectado();
	}
	
	public int getMineralesRecolectados() {
		return this.recursos.getMineralesRecolectados();
	}
	
	public int poblacionMaxima() {
		return this.recursos.limiteDePoblacion(this.getHospedables());
	}
	
	public int poblacionActual() {
		return this.recursos.poblacionActual(this.unidades, this.getMilitables());
	}
	
	public boolean tieneVisionDe(Unidad unidad) {
		return this.vision.tieneVisionDe(unidad);
	}
	
	public abstract void construir(Construible construible, Coordenada cordenada) throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos;
	
	public void asignarUnidad(Unidad unidad) {
		if (!this.unidades.contains(unidad)) {
			this.unidades.add(unidad);
			unidad.asignarPropietario(this);}
	}
	
	public void actualizarObservadores() {
		this.actualizarConstrucciones();
		this.actualizarEntrenamientos();
		this.actualizarUnidades();
		this.recolectarRecursos();
	}
	
	public boolean esAliado(Unidad unidad) {
		return (this.unidades.contains(unidad));
	}
	
	public boolean esAliado(Construible construible) {
		return (this.construcciones.contains(construible));
	}

	public void fallecida(Unidad unidad) {
		this.unidades.remove(unidad);
	}
	
	public void fallecida(Construible construible) {
		this.construcciones.remove(construible);
	}
	
	protected Collection<ConstruccionRecolectora> getRecolectores() {
		
		Collection<ConstruccionRecolectora> recolectores = new ArrayList<ConstruccionRecolectora>();
 		Iterator<Construible> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construible construible = it.next();
			if (construible.puedeExtraerRecursos())  {
				recolectores.add((ConstruccionRecolectora)construible);
			}
			
		}
		
		return recolectores;
		
	}
	
	protected Collection<ConstruccionHabitable> getHospedables() {
		
		Collection<ConstruccionHabitable> hospedables = new ArrayList<ConstruccionHabitable>();
 		Iterator<Construible> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construible construible = it.next();
			if (construible.puedeHospedarUnidades())  {
				hospedables.add((ConstruccionHabitable)construible);
			}
			
		}
		
		return hospedables;
		
	}
	
	protected Collection<ConstruccionMilitar> getMilitables() {
		
		Collection<ConstruccionMilitar> militables = new ArrayList<ConstruccionMilitar>();
 		Iterator<Construible> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construible construible = it.next();
			if (construible.puedeEntrenarUnidades())  {
				militables.add((ConstruccionMilitar)construible);
			}
			
		}
		
		return militables;
		
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
	
	private void actualizarEntrenamientos() {
				
		Iterator<ConstruccionMilitar> it = this.getMilitables().iterator();
			
		while (it.hasNext()) {		
			it.next().actualizarEntrenamientos();
		}

	}
	
	private void actualizarUnidades() {
		
		Iterator<Unidad> it = this.unidades.iterator();
		
		while(it.hasNext()) {
			it.next().actualizar();	
		}
		
	}
	
	private void recolectarRecursos() {
		
		Iterator<ConstruccionRecolectora> it = this.getRecolectores().iterator();
		
		while (it.hasNext()) {
			it.next().recolectar();
		}
		
	}
	
}
