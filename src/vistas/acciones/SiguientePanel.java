package vistas.acciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vistas.paneles.principales.MenuPanel;

public class SiguientePanel extends MouseAdapter {
	
	private MenuPanel panelOriginal;
	
	public SiguientePanel(MenuPanel panelOriginal) {
		this.panelOriginal = panelOriginal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		panelOriginal.irASiguientePanel();
	}

}
