package vistas.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
