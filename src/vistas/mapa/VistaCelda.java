package vistas.mapa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;
import java.util.Collection;

import javax.swing.JComponent;

import vistas.actores.Actor;
import vistas.actores.materiales.ActorAire;
import vistas.actores.materiales.ActorTierra;
import vistas.utilidades.AsignadorVistas;
import juego.mapa.Celda;
import juego.materiales.Material;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class VistaCelda extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4451841605373415808L;
	public static final int lado = 60;
	private final Color colorInterno = new Color(50, 50, 50);
	private final Color colorBorde = new Color(80, 80, 80);

	private Celda celdaRepresentante;

	public VistaCelda(Celda celdaRepresentante) {
		this.celdaRepresentante = celdaRepresentante;
		this.setPreferredSize(new Dimension(lado + 1, lado + 1));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.dibujarFondo(g);
		this.dibujarGradientes(g);
		this.dibujarUnidades(g);
		this.dibujarRecursos(g);
		
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
	
	private void dibujarGradientes(Graphics g) {
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

	private void dibujarUnidades(Graphics g) {

		Collection<Unidad> unidadesDeCelda = this.celdaRepresentante
				.getUnidades();

		for (Unidad unidad : unidadesDeCelda) {
			Actor actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(unidad.getClass());
			actorResponsable.dibujar(g);
		}
	}
	
	private void dibujarRecursos(Graphics g) {
		Recurso recurso = this.celdaRepresentante.getRecurso();

		if (recurso != null) {
			Actor actorRecurso = AsignadorVistas.getInstance()
					.obtenerRepresentacion(recurso.getClass());
			actorRecurso.dibujar(g);
		}
	}
}
