package vistas.acciones;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.InicioInvalido;
import juego.excepciones.NombreInvalido;
import juego.jugadores.Jugador;
import vistas.paneles.principales.PanelJugadores;
import vistas.ventanas.VentanaJuego;

public class IniciarJuego implements ActionListener {
	
	private PanelJugadores panelOriginal;
	
	public IniciarJuego(PanelJugadores panelOriginal) {
		this.panelOriginal = panelOriginal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean cargaValida = true;
		Vector<Jugador> jugadores = panelOriginal.obtenerJugadores();
		
		JFrame frame = (JFrame)SwingUtilities.getRoot((Component) e.getSource());
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		for (Jugador jugador : jugadores) {
			
			try {
				
			juego.crearJugador(jugador);
				
			} catch (NombreInvalido ni) {
				JOptionPane.showMessageDialog(null, "Error, parece que hay un nombre invalido.", "AlgoCraft",1);
				cargaValida = false;
				
			} catch (ColorInvalido ci){
				JOptionPane.showMessageDialog(null, "Error, parece que los colores son iguales.", "AlgoCraft",1);
				cargaValida = false;
				
			} catch (InicioInvalido ii) {
				JOptionPane.showMessageDialog(null, "Error, inicio invalido.", "AlgoCraft",1);
				cargaValida = false;
				
			}
		}

		if (cargaValida) {
			
			try {
				juego.iniciarJuego("mapas/1v1.map");
			} catch (FaltanJugadores fj) {
				JOptionPane.showMessageDialog(null, "Error, parece que los jugadores son insuficientes.", "AlgoCraft",1);
			} catch (InicioInvalido ii) {
				JOptionPane.showMessageDialog(null, "Error, inicio invalido.", "AlgoCraft",1);
			}
		
			VentanaJuego v = new VentanaJuego(jugadores);
		
			frame.dispose();
		}
		
	}

}
