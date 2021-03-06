package juego.jugadores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.MapaJugador;
import juego.razas.construcciones.Construccion;
import juego.razas.construcciones.ConstruccionBase;
import juego.razas.construcciones.ConstruccionHabitable;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.razas.unidades.Unidad;
import juego.recursos.RecursosJugador;

public abstract class Jugador {

	protected String nombre;
	protected Color color;
	protected Collection<ConstruccionBase> bases;
	protected Collection<Construccion> enConstruccion;
	protected Collection<Construccion> construcciones;
	protected Collection<Unidad> unidades;
	protected RecursosJugador recursos;
	protected MapaJugador mapaDescubierto;
	
	public Jugador(String nombre, Color color) {
		
		super();
		this.nombre = nombre;
		this.color = color;
		this.bases = new ArrayList<ConstruccionBase>();
		this.construcciones = new ArrayList<Construccion>();
		this.enConstruccion = new ArrayList<Construccion>();
		this.unidades = new ArrayList<Unidad>();
		this.recursos = new RecursosJugador();
		this.mapaDescubierto = new MapaJugador(this);
		
	}
	
	public Color getColor() { return this.color; }
	public String getNombre() { return this.nombre; }
	public boolean perdio() { return (this.bases.size() == 0); }
	
	public void rendirse() {
		this.bases.clear();
	}
	
	public void finalizarTurno() {	
		Juego.getInstance().finalizarTurno();		
	}
	
	public void inicializarMapa() throws UbicacionInvalida {
		
		Iterator<ConstruccionBase> it = this.bases.iterator();
		
		while (it.hasNext()) {
			it.next().inicializarMapa();
		}
		
	}
		
	public boolean suministrosSuficientes(int cantidad) { 
		
		this.recursos.poblacionActual(this.unidades, this.getMilitables());
		this.recursos.limiteDePoblacion(this.getHospedables());
		
		return this.recursos.suministrosSuficientes(cantidad); 
		
	}
	
	public boolean mineralesSuficientes(int cantidad) { return this.recursos.mineralesSuficientes(cantidad); }
	public boolean gasVespenoSuficiente(int cantidad) { return this.recursos.gasVespenoSuficiente(cantidad); }
	
	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes { this.recursos.consumirMinerales(costoMinerales); }
	public void consumirGasVespeno(int costoGasVespeno) throws RecursosInsuficientes { this.recursos.consumirGasVespeno(costoGasVespeno); }
	
	public void recolectarGasVespeno(int extraidos) { this.recursos.recolectarGasVespeno(extraidos); }
	public void recolectarMinerales(int extraidos) { this.recursos.recolectarMinerales(extraidos); }
	
	public int getGasVespenoRecolectado() {	return this.recursos.getGasVespenoRecolectado(); }
	public int getMineralesRecolectados() {	return this.recursos.getMineralesRecolectados(); }
	
	public int limiteDePoblacion() { return this.recursos.limiteDePoblacion(this.getHospedables()); }
	public int poblacionActual() { return this.recursos.poblacionActual(this.unidades, this.getMilitables()); }
	
	public boolean tieneVision(Celda celda) { return this.mapaDescubierto.tieneVision(celda); }
	public boolean tieneVision(Controlable controlable) { return this.mapaDescubierto.tieneVision(controlable); }
	
	public boolean esAliado(Controlable controlable) { 
	
		if (this.unidades.contains(controlable)) return true;	
		if (this.construcciones.contains(controlable)) return true;
		
		return (this.bases.contains(controlable)); 
	
	}
	
	public void baseDestruida(ConstruccionBase base) {
		if (this.bases.contains(base)) {
			this.bases.remove(base);
		}
	}
	
	public void visionPerdida(Controlable controlable) {
		this.mapaDescubierto.perdidaDeVision(controlable);
	}
	
	public void fallecido(Controlable controlable) { 
		
		this.visionPerdida(controlable);
		
		if (this.unidades.contains(controlable)) {
			this.unidades.remove(controlable); 
		}
		
		if (this.construcciones.contains(controlable)) {
			this.construcciones.remove(controlable); 
		} else if (this.enConstruccion.contains(controlable)) {
			this.enConstruccion.remove(controlable);
		}
	
	}
	
	public void asignarBase(ConstruccionBase nuevaBase) {
		if (!this.bases.contains(nuevaBase)) {
			this.bases.add(nuevaBase);
		}
	}
	
	public void asignarUnidad(Unidad unidad) {
		
		if (!this.unidades.contains(unidad)) {
			this.unidades.add(unidad);
			unidad.asignarPropietario(this);
		}
		
	}
	
	public void mapaDescubierto(Collection<Celda> celdasDescubiertas) {
		
		Iterator<Celda> it = celdasDescubiertas.iterator();
		while (it.hasNext()) {
			this.mapaDescubierto.celdaDescubierta(it.next());
		}
	
	}
	
	public void actualizarObservadores() {
		
		this.actualizarBases();
		this.actualizarConstrucciones();
		this.actualizarEntrenamientos();
		this.actualizarUnidades();
		this.recolectarRecursos();
		
	}
	
	public void construir(ConstruccionBase construccionBase) 
			throws UbicacionInvalida, RecursosInsuficientes {
		
		if (!construccionBase.recursosSuficientes(this)) throw new RecursosInsuficientes();
		
		construccionBase.consumirRecursos(this);
		construccionBase.posicionar(construccionBase.getPosicion());
		
		this.asignarBase(construccionBase);
		
	}
	
	protected void constructor(Construccion construccion, Coordenada posicion) throws UbicacionInvalida, RecursosInsuficientes {
		
		if (!construccion.recursosSuficientes(this)) throw new RecursosInsuficientes();
		
		construccion.setPropietario(this);
		construccion.posicionar(posicion);
		construccion.consumirRecursos(this);
		
		this.enConstruccion.add(construccion);

	}
	
	protected Collection<ConstruccionRecolectora> getRecolectores() {
		
		Collection<ConstruccionRecolectora> recolectores = new ArrayList<ConstruccionRecolectora>();
 		Iterator<Construccion> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construccion construccion = it.next();
			if (construccion.puedeExtraerRecursos())  {
				recolectores.add((ConstruccionRecolectora)construccion);
			}
			
		}
		
		return recolectores;
		
	}
	
	protected Collection<ConstruccionHabitable> getHospedables() {
		
		Collection<ConstruccionHabitable> hospedables = new ArrayList<ConstruccionHabitable>();
 		Iterator<Construccion> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construccion construccion = it.next();
			if (construccion.puedeHospedarUnidades())  {
				hospedables.add((ConstruccionHabitable)construccion);
			}
			
		}
		
		return hospedables;
		
	}
	
	protected Collection<ConstruccionMilitar> getMilitables() {
		
		Collection<ConstruccionMilitar> militables = new ArrayList<ConstruccionMilitar>();
 		Iterator<Construccion> it = this.construcciones.iterator();
		
		while (it.hasNext()) {
		
			Construccion construccion = it.next();
			if (construccion.puedeEntrenarUnidades())  {
				militables.add((ConstruccionMilitar)construccion);
			}
			
		}
		
		return militables;
		
	}
	
	private void actualizarBases() {
		
		Iterator<ConstruccionBase> it = this.bases.iterator();
		
		while (it.hasNext()) {
			
			ConstruccionBase base = it.next();
			
			if (!base.construccionFinalizada()) {
				base.actualizarConstruccion();
			}
			
		}
		
	}
	
	private void actualizarConstrucciones() {
		
		Collection<Construccion> construccionesFinalizadas = new ArrayList<Construccion>();
			
		Iterator<Construccion> it = this.enConstruccion.iterator();
			
		while (it.hasNext()) {			
			Construccion construccion = it.next();
			construccion.actualizarConstruccion();
			if (construccion.construccionFinalizada()) {
				construccionesFinalizadas.add(construccion);	
				construcciones.add(construccion);
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
		
		ArrayList<Unidad> arrayUnidades = new ArrayList<Unidad>(this.unidades);
		
		//Hacemos esto para poder modificar en el medio del loop
		for (int i = 0; i < arrayUnidades.size(); i++) {
			arrayUnidades.get(i).actualizar();
		}
		
	}
	
	private void recolectarRecursos() {
		
		Iterator<ConstruccionRecolectora> it = this.getRecolectores().iterator();
		
		while (it.hasNext()) {
			it.next().recolectar();
		}
		
	}

	public abstract ConstruccionBase crearBase(Coordenada posicion) throws UbicacionInvalida;
	
}
