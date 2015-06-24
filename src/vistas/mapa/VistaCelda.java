package vistas.mapa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;

import javax.swing.JComponent;

import vistas.actores.Actor;
import vistas.actores.ActorAire;
import vistas.actores.ActorTierra;
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
	public static final int lado = 20;
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

		Collection<Unidad> unidadesDeCelda = this.celdaRepresentante
				.getUnidades();

		for (Unidad unidad : unidadesDeCelda) {
			Actor actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(unidad.getClass());
			actorResponsable.dibujar(g);
		}
		
		Recurso recurso = this.celdaRepresentante.getRecurso();
		
		if(recurso != null) {
			Actor actorRecurso = AsignadorVistas.getInstance()
					.obtenerRepresentacion(recurso.getClass());
			actorRecurso.dibujar(g);		
		}
	}
	
	private void dibujarFondo(Graphics g) {
		
		Material material = this.celdaRepresentante.getMaterial();
		
		Actor actorMaterial;
		
		if(material == Material.aire) {
			actorMaterial = new ActorAire();
			actorMaterial.dibujar(g);
		} else if(material == Material.tierra) {
			actorMaterial = new ActorTierra();
			actorMaterial.dibujar(g);
		}
	}
}
