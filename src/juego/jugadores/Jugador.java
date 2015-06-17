package juego.jugadores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeRecursos;
import juego.interfaces.Construible;
import juego.interfaces.Entrenable;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
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
	protected BolsaDeRecursos bolsaDeRecursos;
	
	public Jugador(String nombre, Color color) {
		super();
		this.nombre = nombre;
		this.color = color;
		this.construcciones = new ArrayList<Construible>();
		this.enConstruccion = new ArrayList<Construible>();
		this.unidades = new ArrayList<Unidad>();
		this.bolsaDeRecursos = new BolsaDeRecursos();
	}
		
	public Color getColor() { return this.color; }
	public String getNombre() { return this.nombre; }
	public BolsaDeRecursos bolsaDeRecursos() { return this.bolsaDeRecursos; }
	
	public void finalizarTurno() {		
		Juego.getInstance().finalizarTurno();		
	}
	
	public abstract void construir(Construible construible, Coordenada cordenada) throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos;
	
	public void asignarUnidad(Unidad unidad) {
		this.unidades.add(unidad);
	}
	
	public void actualizarObservadores() {
		this.actualizarConstrucciones();
		this.actualizarEntrenamientos();
		this.actualizarUnidades();
		this.recolectarRecursos();
	}

	public int poblacionMaxima() {
		
		Collection<ConstruccionHabitable> hospedables = this.getHospedables();
		int limiteDePoblacion = 0;
		
		Iterator<ConstruccionHabitable> it = hospedables.iterator();
		while (it.hasNext()) {
			limiteDePoblacion += it.next().capacidadDeHabitantes();
		}
		
		return (limiteDePoblacion > 200) ? 200 : limiteDePoblacion;
		
	}
	
	public int poblacionActual() {	
		
		int poblacionActual = 0;
		
		Iterator<Unidad> itU = this.unidades.iterator();
		
		while (itU.hasNext()) {
			poblacionActual += itU.next().suministrosNecesarios();
		}
		
		Iterator<ConstruccionMilitar> itC = this.getMilitables().iterator();
		
		while (itC.hasNext()) {
			poblacionActual += itC.next().suministrosEnEntrenamiento();
		}
		
		return poblacionActual;
		
	}
	
	public boolean suministrosSuficientes(int costoSuministros) {
		int suministrosRestantes = this.poblacionMaxima() - this.poblacionActual();
		return (suministrosRestantes >= costoSuministros);
	}
	
	public void entrenar(ConstruccionMilitar construccion, Entrenable entrenable) 
			throws RecursosInsuficientes, SobrePoblacion, RequerimientosInvalidos {

		Iterator<ConstruccionMilitar> it = this.getMilitables().iterator();
		
		while (it.hasNext()) {			
			if (it.next() == construccion) {
				entrenable.entrenador(construccion);
			}
		}
	}
	
	public void unidadActiva(Unidad unidadActiva) {
		
		if (!this.unidades.contains(unidadActiva)) {
			this.unidades.add(unidadActiva);
		}
		
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

	public void activarUnidad(ConstruccionMilitar construccion, Unidad unidad, Coordenada coordFinal) throws UbicacionInvalida {
		
		// Cuando un entrenamiento finaliza, la unidad esta inactiva en la construccion creadora, con este metodo
		// puede activarse la unidad moviendola a otra ubicacion cercana.
		Iterator<ConstruccionMilitar> it = this.getMilitables().iterator();
		
		while (it.hasNext()) {
			if (it.next() == construccion) construccion.ubicar(unidad, coordFinal);
		}
		
	}
	
}
