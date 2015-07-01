package vistas.mapa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;

import vistas.actores.Actor;
import vistas.actores.materiales.ActorAire;
import vistas.actores.materiales.ActorTierra;
import vistas.handlers.interfaces.ObservadorCelda;
import vistas.utilidades.AsignadorVistas;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.materiales.Material;
import juego.razas.construcciones.Construccion;
import juego.razas.construcciones.ConstruccionBase;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import juego.recursos.Recurso;

public class VistaCelda extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4451841605373415808L;
	public static final int lado = 60;
	private boolean seleccionada;
	
	private ArrayList<ObservadorCelda> observadores;

	private Celda celdaRepresentante;
	private Coordenada posicion;

	public VistaCelda(Celda celdaRepresentante, Coordenada coordenada) {
		this.celdaRepresentante = celdaRepresentante;
		this.posicion = coordenada;
		this.observadores = new ArrayList<ObservadorCelda>();
		
		
		this.seleccionada = false;
		
		this.setPreferredSize(new Dimension(lado + 1, lado + 1));
	}
	
	public Coordenada obtenerPosicion() {
		return this.posicion;
	}
	
	public void agregarObservador(ObservadorCelda o) {
		this.observadores.add(o);
	}
	
	public void removerObservador(ObservadorCelda o) {
		this.observadores.remove(o);
	}
	
	public void notificarSeleccion() {
		for(ObservadorCelda obs : this.observadores) {
			obs.notificar(this.obtenerPosicion());
		}
	}
	
	public void deseleccionar() {
		if(this.seleccionada) {
			this.seleccionada = false;
			this.repaint();
		}
	}
	
	public void seleccionar() {
		this.seleccionada = true;
		this.repaint();
		this.notificarSeleccion();
	}
	
	public Celda obtenerCelda() {
		return this.celdaRepresentante;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.dibujarFondo(g);
		this.dibujarSombraDeFondo(g);
		this.dibujarRecursos(g);
		this.dibujarConstrucciones(g);
		this.dibujarBases(g);
		this.dibujarUnidades(g);
		
		if(this.seleccionada) {
			this.dibujarSeleccion(g);
		}
		
	}

	private void dibujarBases(Graphics g) {
		ConstruccionBase base = this.celdaRepresentante
				.getBase();

		if (base != null) {
			Actor actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(base.getClass(), base);
			actorResponsable.dibujar(g);
		}
	}

	private void dibujarFondo(Graphics g) {

		Material material = this.celdaRepresentante.getMaterial();

		Actor actorMaterial;

		if (material == Material.aire) {
			actorMaterial = new ActorAire();
			actorMaterial.dibujar(g);
		} else if (material == Material.tierra) {
			actorMaterial = new ActorTierra();
			actorMaterial.dibujar(g);
		}
		
	}
	
	private void dibujarSombraDeFondo(Graphics g) {
		final float[] FRACTIONS = { 0.0f, 1.0f };
	    final Color[] DARK_COLORS = { new Color(0,0,0,150),
	    		new Color(0,0,0,0) };
		
		LinearGradientPaint gp = new LinearGradientPaint(
				new Point2D.Double(0,0),
				new Point2D.Double(lado,lado),
				FRACTIONS,
				DARK_COLORS);
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(gp);
		g2D.fillRect(0, 0, lado, lado);
	}
	
	private void dibujarSeleccion(Graphics g) {
		final float[] orden = { 0.0f, 1.0f };
	    final Color[] naranjas = { new Color(255,255,0,150),
	    		new Color(255,133,27,50) };
	    
	    int mitad = (int)(lado * 0.5);
		
		LinearGradientPaint gp = new LinearGradientPaint(
				new Point2D.Double(mitad,lado),
				new Point2D.Double(mitad,0),
				orden,
				naranjas);
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(gp);
		g2D.fillRect(0, 0, lado, lado);
	}

	private void dibujarUnidades(Graphics g) {

		Collection<Unidad> unidadesDeCelda = this.celdaRepresentante
				.getUnidades();

		for (Unidad unidad : unidadesDeCelda) {
			Actor actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(unidad.getClass(), unidad);
			actorResponsable.dibujar(g);
		}
	}
	
	private void dibujarConstrucciones(Graphics g) {

		Collection<Construccion> construccionesDeCelda = this.celdaRepresentante
				.getConstrucciones();

		for (Construccion construccion : construccionesDeCelda) {
			Actor actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(construccion.getClass(), construccion);
			actorResponsable.dibujar(g);
		}
	}
	
	private void dibujarRecursos(Graphics g) {
		Recurso recurso = this.celdaRepresentante.getRecurso();

		if (recurso != null) {
			Actor actorRecurso = AsignadorVistas.getInstance()
					.obtenerRepresentacion(recurso.getClass(), recurso);
			actorRecurso.dibujar(g);
		}
	}
}
