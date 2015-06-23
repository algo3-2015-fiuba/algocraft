package vistas.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.paneles.principales.MenuPanel;

public class SiguientePanel implements ActionListener {
	
	private MenuPanel panelOriginal;
	
	public SiguientePanel(MenuPanel panelOriginal) {
		this.panelOriginal = panelOriginal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		panelOriginal.irASiguientePanel();
	}

}
