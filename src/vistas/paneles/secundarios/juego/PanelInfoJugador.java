package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juego.Juego;
import juego.jugadores.Jugador;
import vistas.Aplicacion;
import vistas.handlers.FinalizarTurnoListener;
import vistas.ventanas.VentanaJuego;

public class PanelInfoJugador extends JPanel {

	private VentanaJuego ventanaOriginal;
	private JLabel labelNombreJugador;
	private JLabel labelMinerales;
	private JLabel labelGas;
	private JLabel labelSuministros;
	private JButton finalizarTurnoButton;

	public PanelInfoJugador(VentanaJuego ventanaOriginal) {

		this.ventanaOriginal = ventanaOriginal;

		this.setBackground(new Color(0, 0, 0, 80));
		this.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 0));
		this.setLayout(new GridBagLayout());

		this.labelNombreJugador = new JLabel();
		this.labelMinerales = new JLabel();
		this.labelGas = new JLabel();
		this.labelSuministros = new JLabel();
		this.finalizarTurnoButton = new JButton("Finalizar Turno");
		this.finalizarTurnoButton.addMouseListener(new FinalizarTurnoListener(this.ventanaOriginal));

		this.actualizarDatosDelJugador();
	}
	
	private void comandosDeJugador() {

		Jugador jugador = Juego.getInstance().turnoDe();
		String nombre = jugador.getNombre();

		this.labelNombreJugador = new JLabel();

		labelNombreJugador.setFont(Aplicacion.fontBebas(24f));

		String text = String
				.format("<html><font color='#DDDDDD'>%s</font></html>",
						nombre);

		labelNombreJugador.setText(text);
	}

	private void actualizarMinerales() {

		Jugador jugador = Juego.getInstance().turnoDe();
		int cantidadDeMinerales = jugador.getMineralesRecolectados();

		this.labelMinerales = new JLabel();

		labelMinerales.setFont(Aplicacion.fontBebas(24f));

		String text = String
				.format("<html><font color='#7FDBFF'>Minerales:</font> <font color=white>%s</font></html>",
						cantidadDeMinerales);

		labelMinerales.setText(text);
	}
	
	private void actualizarGas() {

		Jugador jugador = Juego.getInstance().turnoDe();
		int cantidadDeGas = jugador.getGasVespenoRecolectado();

		this.labelGas = new JLabel();

		labelGas.setFont(Aplicacion.fontBebas(24f));

		String text = String
				.format("<html><font color='#2ECC40'>Gas:</font> <font color=white>%s</font></html>",
						cantidadDeGas);

		labelGas.setText(text);
	}
	
	private void actualizarSuministros() {

		Jugador jugador = Juego.getInstance().turnoDe();
		int poblacionActual = jugador.poblacionActual();
		int cantidadDeSuministros = jugador.limiteDePoblacion();

		this.labelSuministros = new JLabel();

		labelSuministros.setFont(Aplicacion.fontBebas(24f));

		String text = String
				.format("<html><font color='#FFDC00'>Poblacion:</font> <font color=white>%s/%s</font></html>",
						poblacionActual, cantidadDeSuministros);

		labelSuministros.setText(text);
	}

	public void actualizarDatosDelJugador() {

		Jugador jugador = Juego.getInstance().turnoDe();

		this.actualizarMinerales();
		this.actualizarGas();
		this.actualizarSuministros();
		this.comandosDeJugador();
		
		this.removeAll();

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(0, 20, 0, 0);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;

		this.add(this.labelMinerales, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;

		this.add(this.labelGas, c);

		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1;

		this.add(this.labelSuministros, c);
		
		c.insets = new Insets(0, 20, 0, 0);
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 10;
		c.anchor = GridBagConstraints.LINE_END;
		
		this.add(this.labelNombreJugador, c);
		
		c.insets = new Insets(0, 0, 0, 40);
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = GridBagConstraints.LINE_END;
		
		this.add(this.finalizarTurnoButton, c);

	}

}
