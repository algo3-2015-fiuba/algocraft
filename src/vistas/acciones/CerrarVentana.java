package vistas.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CerrarVentana implements ActionListener {
	
	public CerrarVentana() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
