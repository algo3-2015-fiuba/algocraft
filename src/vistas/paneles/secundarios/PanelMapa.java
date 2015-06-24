package vistas.paneles.secundarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.materiales.Material;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.protoss.Dragon;
import juego.razas.unidades.terran.Marine;
import juego.recursos.Mineral;
import vistas.Aplicacion;
import vistas.handlers.HandScrollListener;
import vistas.mapa.VistaCelda;
import vistas.utilidades.CampoDeTextoPredeterminado;
import vistas.utilidades.Item;
import vistas.ventanas.VentanaJuego;

public class PanelMapa extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	
	private int celdas_x;
	private int celdas_y;

	public PanelMapa(int celdas_x, int celdas_y) {
		
		this.celdas_x = celdas_x;
		this.celdas_y = celdas_y;
		
		this.setBackground(new Color(0, 0, 0, 180));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new GridBagLayout());
		

		for (int i = 0; i < celdas_x; i++) {
			for (int j = 0; j < celdas_y; j++) {

				GridBagConstraints c = new GridBagConstraints();
				
				c.fill = GridBagConstraints.NONE;
				c.insets = new Insets(2,2,2,2);
				c.gridx = i;
				c.gridy = j;
				
				Celda contenido = new Celda(Material.aire, null, new Coordenada(i,j));
				
				
				
				if(i % 2 == 0 && j % 2 == 0) {
					Marine unidad = new Marine();
					contenido.ocupar(unidad);
				} else if(i % 5 == 0) {
					Dragon unidad = new Dragon();
					contenido.ocupar(unidad);
				}
				
				

				VistaCelda celda = new VistaCelda(contenido);
				
				celda.addMouseListener(new MouseAdapter() {

	                @Override
	                public void mousePressed(MouseEvent e) {
	                   System.out.println(e.getSource());
	                }

	                @Override
	                public void mouseReleased(MouseEvent e) {
	                	System.out.println(e.getSource());
	                }
	            });
				
				
				
				
				
				this.add(celda, c);
				
				
			}			
		}
	}
	
	@Override
	public Dimension getMinimumSize() {
		
		VistaCelda celda = new VistaCelda(new Celda(Material.aire, new Mineral(500), new Coordenada(0,0)));
		int tamanio_x = (int) (celda.getPreferredSize().getWidth()+4);
		int tamanio_y = (int) (celda.getPreferredSize().getHeight()+4);
		
        return new Dimension(tamanio_x * this.celdas_x, tamanio_y * this.celdas_y);
    }

}