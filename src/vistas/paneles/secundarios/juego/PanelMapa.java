package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import vistas.handlers.CeldaMouseListener;
import vistas.handlers.interfaces.ObservadorCelda;
import vistas.mapa.VistaCelda;
import vistas.ventanas.VentanaJuego;

public class PanelMapa extends JPanel implements ObservadorCelda {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;

	private int celdas_x;
	private int celdas_y;
	private Mapa mapa;
	private VentanaJuego ventanaOriginal;
	private LinkedList<VistaCelda> vistaCeldas;
	private VistaCelda celdaSeleccionada;

	public PanelMapa(VentanaJuego ventanaJuego, Mapa mapa) {

		this.mapa = mapa;
		this.ventanaOriginal = ventanaJuego;
		this.vistaCeldas = new LinkedList<VistaCelda>();
		this.celdaSeleccionada = null;

		this.setBackground(new Color(0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new GridBagLayout());

		this.agregarCeldasDeMapa(mapa);
	}

	private void agregarCeldasDeMapa(Mapa mapa) {

		HashMap<Coordenada, Celda> celdas = mapa.obtenerMapa();

		int i = 0;

		for (Map.Entry<Coordenada, Celda> parCelda : celdas.entrySet()) {

			Coordenada coordenada = parCelda.getKey();
			Celda celda = parCelda.getValue();

			GridBagConstraints c = new GridBagConstraints();

			c.fill = GridBagConstraints.NONE;
			c.insets = new Insets(0, 0, 0, 0);
			c.gridx = coordenada.getX();
			c.gridy = coordenada.getY();

			VistaCelda vistaCelda = new VistaCelda(celda, coordenada);
			vistaCelda.addMouseListener(new CeldaMouseListener(vistaCelda));
			//vistaCelda.addMouseMotionListener(new CeldaMouseListener(vistaCelda));

			vistaCelda.agregarObservador(this);

			this.add(vistaCelda, c);
			this.vistaCeldas.add(vistaCelda);
		}
	}

	@Override
	public void notificar(final Coordenada coordenada) {

		if(this.celdaSeleccionada != null) {
			this.celdaSeleccionada.deseleccionar();
		}
		
		for (VistaCelda vista : this.vistaCeldas) {
			if (vista.obtenerPosicion().equals(coordenada)) {
				this.celdaSeleccionada = vista;
			}
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Here, we can safely update the GUI
				// because we'll be called from the
				// event dispatch thread
				try {
					ventanaOriginal.notificar(coordenada);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}