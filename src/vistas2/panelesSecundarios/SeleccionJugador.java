package vistas2.panelesSecundarios;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import vistas2.utilidades.CampoDeTextoPredeterminado;

public class SeleccionJugador extends JPanel {
	public SeleccionJugador(String nombre) {

		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		CampoDeTextoPredeterminado f = new CampoDeTextoPredeterminado(nombre);
		
		this.add(f);
	}
}
