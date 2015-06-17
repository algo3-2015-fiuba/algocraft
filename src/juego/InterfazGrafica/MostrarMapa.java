package juego.InterfazGrafica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MostrarMapa implements ActionListener {
	
	private JLabel mapaFrame;
	private JButton boton1, boton2;

	public MostrarMapa(JLabel mapa, JButton atacar, JButton construir) {
		
		this.mapaFrame = mapa;
		this.boton1 = atacar;
		this.boton2 = construir;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.boton1.setVisible(true);
		this.boton2.setVisible(true);
		ImageIcon algocroft = new ImageIcon(getClass().getResource("/InterfazDeStarCraft.jpg"));
        Image imagen2 = algocroft.getImage();
        imagen2 = imagen2.getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        algocroft = new ImageIcon(imagen2);
		this.mapaFrame.setIcon(algocroft);

	}

}
