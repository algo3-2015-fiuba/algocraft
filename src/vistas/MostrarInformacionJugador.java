package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MostrarInformacionJugador implements ActionListener {
	
	private JPanel panelDeInfo;
	private boolean sePuedeMostrar;

	public MostrarInformacionJugador(JPanel unPanel) {
		
		this.panelDeInfo = unPanel;
		this.sePuedeMostrar = false;
	}

	public MostrarInformacionJugador(JPanel unPanel, boolean mostrarPanel) {
		
		this.panelDeInfo = unPanel;
		this.sePuedeMostrar = mostrarPanel;
	}

	public MostrarInformacionJugador(JPanel panelCargaDeJugadores, JButton atacar, JButton construir, boolean b) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.panelDeInfo.setVisible(this.sePuedeMostrar);

	}

}
