package vistas.acciones.sonido;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vistas.sonido.Sonido;

public class AlternarEstadoDeSonido extends MouseAdapter {
	
	public AlternarEstadoDeSonido() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Sonido.getInstance().alternar();
	}

}
