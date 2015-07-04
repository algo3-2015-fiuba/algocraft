package vistas.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CerrarVentana extends MouseAdapter {
	
	public CerrarVentana() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}

}
