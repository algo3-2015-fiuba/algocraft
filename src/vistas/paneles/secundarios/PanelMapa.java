package vistas.paneles.secundarios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.materiales.Material;
import juego.razas.unidades.protoss.Dragon;
import juego.razas.unidades.terran.Marine;
import juego.recursos.Mineral;
import vistas.handlers.CeldaMouseListener;
import vistas.handlers.PassOverListener;
import vistas.mapa.VistaCelda;

public class PanelMapa extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	
	private int celdas_x;
	private int celdas_y;
	private Mapa mapa;

	public PanelMapa(Mapa mapa) {
		
		this.mapa = mapa;
		
		this.setBackground(new Color(0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new GridBagLayout());
		

		this.agregarCeldasDeMapa(mapa);
	}
	
	private void agregarCeldasDeMapa(Mapa mapa) {
		
		HashMap<Coordenada, Celda> celdas = mapa.obtenerCeldas();
		
		int i = 0;
		
		for (Map.Entry<Coordenada, Celda> parCelda : celdas.entrySet()) {
			
			Coordenada coordenada = parCelda.getKey();
			Celda celda = parCelda.getValue();
		
			GridBagConstraints c = new GridBagConstraints();
			
			c.fill = GridBagConstraints.NONE;
			c.insets = new Insets(2,2,2,2);
			c.gridx = coordenada.getX();
			c.gridy = coordenada.getY();
			
			VistaCelda vistaCelda = new VistaCelda(celda);
			
			vistaCelda.addMouseListener(new CeldaMouseListener());
			vistaCelda.addMouseMotionListener(new CeldaMouseListener());
			
			this.add(vistaCelda,c);	
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