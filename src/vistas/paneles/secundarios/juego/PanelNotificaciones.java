package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import vistas.Aplicacion;
import vistas.sonido.Sonido;
import vistas.ventanas.VentanaJuego;

public class PanelNotificaciones extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7060834467081499042L;
	
	private VentanaJuego ventanaOriginal;
	private JLabel sectorNotificaciones;
	private Timer timer;

	public PanelNotificaciones(VentanaJuego ventanaOriginal) {

		this.ventanaOriginal = ventanaOriginal;
		this.sectorNotificaciones = new JLabel();
		this.sectorNotificaciones.setForeground(Color.WHITE);
		this.sectorNotificaciones.setFont(Aplicacion.fontBebas(24f));
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		this.add(this.sectorNotificaciones);
		
		this.limpiar();
	}
	

	public void notificar(String mensaje) {
		
		if(this.timer != null) {
			this.timer.stop();
		}
		
		this.timer = new Timer(5000, new LimpiarAreaDeNotificaciones(this));		
		this.timer.setRepeats(false);
		
		
		
		this.timer.start();
		this.setBackground(Color.RED);
		this.sectorNotificaciones.setText(mensaje);
		Sonido.getInstance().reproducirNotificacion();
	}

	public void limpiar() {
		this.sectorNotificaciones.setText("");
		this.setBackground(new Color(0,0,0,0));
		this.ventanaOriginal.actualizarPantalla();
		
	}

}

class LimpiarAreaDeNotificaciones implements ActionListener {
	
	private PanelNotificaciones panelNotificaciones;
	
	public LimpiarAreaDeNotificaciones(PanelNotificaciones panel) {
		this.panelNotificaciones = panel;
	}
	
	public void actionPerformed(ActionEvent e) {

		this.panelNotificaciones.limpiar();

	}
}