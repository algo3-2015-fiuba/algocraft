package vistas.acciones.sonido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.sonido.Sonido;

public class AlternarEstadoDeSonido implements ActionListener {
	
	public AlternarEstadoDeSonido() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Sonido.getInstance().alternar();
	}

}
