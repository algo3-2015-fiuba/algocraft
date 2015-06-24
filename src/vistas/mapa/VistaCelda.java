package vistas.mapa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JLabel;

import vistas.actores.Actor;
import vistas.utilidades.AsignadorVistas;
import juego.mapa.Celda;
import juego.razas.unidades.Unidad;

public class VistaCelda extends JComponent {

	private final int lado = 50;
	private final Color colorInterno = new Color(50, 50, 50);
	private final Color colorBorde = new Color(80, 80, 80);

	private Celda celdaRepresentante;

	public VistaCelda(Celda celdaRepresentante) {
		this.celdaRepresentante = celdaRepresentante;
		this.setPreferredSize(new Dimension(lado + 1, lado + 1));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(colorInterno);
		g.fillRect(0, 0, lado, lado);
		g.setColor(colorBorde);
		g.drawRect(0, 0, lado, lado);

		Collection<Unidad> unidadesDeCelda = this.celdaRepresentante
				.getUnidades();

		for (Unidad unidad : unidadesDeCelda) {
			Actor actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(unidad.getClass());
			actorResponsable.dibujar(g);
		}

	}

}
