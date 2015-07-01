package vistas.actores.materiales;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JLabel;

import vistas.acciones.AccionPendienteUnidad;
import vistas.actores.Actor;
import vistas.mapa.VistaCelda;

public class ActorMaterial extends Actor {
	
	protected Color colorInterno = new Color(50, 50, 50);
	protected Color colorBorde = new Color(80, 80, 80);

	@Override
	public void dibujar(Graphics g) {
		
		int lado = VistaCelda.lado;	
	
		g.setColor(colorInterno);
		g.fillRect(0, 0, lado, lado);
		g.setColor(colorBorde);
		g.drawRect(0, 0, lado, lado);
	}

}
