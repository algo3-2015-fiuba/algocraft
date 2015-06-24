package vistas.paneles.secundarios;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;
import vistas.Aplicacion;

public class PanelAcciones extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Unidad unidadActual;
	private JLabel nombreUnidad;
	
	public PanelAcciones() {
		this.setBackground(new Color(0, 0, 0, 180));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel titulo = Aplicacion.titulo("Acciones", 42f);
		titulo.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.add(titulo);
		
		JLabel mover = Aplicacion.titulo("Moverse", 24f);
		
		this.add(titulo);
		this.add(Box.createRigidArea(new Dimension(1,20)));
		this.add(Aplicacion.titulo("Moverse", 24f));
		this.add(Aplicacion.titulo("Atacar", 24f));
		
		
		this.removerSeleccion();		
		this.seleccionarUnidad(new AltoTemplario());
		
	}
	
	public void seleccionarUnidad(Unidad seleccionada) {
		this.unidadActual = seleccionada;
		
	}
	
	public void removerSeleccion() {
		this.unidadActual = null;
	}
	
	
}