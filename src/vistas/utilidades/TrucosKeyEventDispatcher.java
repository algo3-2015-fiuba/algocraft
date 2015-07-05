package vistas.utilidades;

import java.awt.KeyEventDispatcher;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Vector;

import juego.Juego;
import juego.jugadores.Jugador;
import vistas.ventanas.VentanaJuego;

public class TrucosKeyEventDispatcher implements KeyEventDispatcher {
	
	private Vector<Character> teclasApretadas = new Vector<Character>();
	private VentanaJuego ventanaOriginal;
	
	public TrucosKeyEventDispatcher(VentanaJuego ventanaOriginal) {
		this.ventanaOriginal = ventanaOriginal;
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_TYPED) {
			this.teclasApretadas.addElement(e.getKeyChar());
			System.out.println("Got key event! " + e.getKeyChar());
			
			if(this.trucoEscrito()) {
				
				this.teclasApretadas.removeAllElements();
				
				this.ventanaOriginal.notificarMensaje("Truco \"show me the money\" activado!");
				Jugador jugadorActual = Juego.getInstance().turnoDe();
				jugadorActual.recolectarMinerales(10000);
				jugadorActual.recolectarGasVespeno(10000);
				this.ventanaOriginal.actualizarPantalla();
			}
		}
		
		
		return false;
	}
	
	private boolean trucoEscrito() {
		
		String trucoRecursos = "show me the money";
		

		ArrayList<Character> listCharTruco = new ArrayList<Character>();
		
		for(char c : trucoRecursos.toCharArray()) {
			listCharTruco.add(c);
		}		
		
		ListIterator<Character> liApretadas = this.teclasApretadas.listIterator(this.teclasApretadas.size());
		ListIterator<Character> liTruco = listCharTruco.listIterator(listCharTruco.size());

		
		int matchCount = 0;
		
		// Iterate in reverse.
		while(liApretadas.hasPrevious()) {
		  if(liTruco.hasPrevious()) {
			  if(liApretadas.previous() == liTruco.previous()) {
				  matchCount++;
			  } else {
				  matchCount = 0;
			  }
		  } else {
			  break;
		  }
		}
		
		if(matchCount == trucoRecursos.length()) {
			return true;		
		} else {
			return false;
		}
	}
}
