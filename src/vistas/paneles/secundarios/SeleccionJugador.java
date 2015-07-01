package vistas.paneles.secundarios;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import vistas.utilidades.CampoDeTextoPredeterminado;
import vistas.utilidades.Item;

public class SeleccionJugador extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private String nombre;
	private CampoDeTextoPredeterminado nombreDelJugador;
	private JComboBox<String> seleccionDeRaza;
	private JComboBox<Item> seleccionDeColor;
	
	@SuppressWarnings("unchecked")
	public SeleccionJugador(String nombre, int indexSeleccionColor) {
		
		this.nombre = nombre;

		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.nombreDelJugador = new CampoDeTextoPredeterminado(nombre);
		
		String[] razasDisponibles = { "Terran", "Protoss" };
		
		this.seleccionDeRaza = new JComboBox<String>(razasDisponibles);
		this.seleccionDeRaza.setFont(new Font(seleccionDeRaza.getFont().getName(), Font.PLAIN, 20));
		this.seleccionDeRaza.setSelectedIndex(0);
		
		Vector<Item> coloresDisponibles = new Vector<Item>();
		coloresDisponibles.add(new Item(Color.red, "Rojo"));
		coloresDisponibles.add(new Item(Color.blue, "Azul"));
		
		this.seleccionDeColor = new JComboBox<Item>(coloresDisponibles);
		this.seleccionDeColor.setFont(new Font(this.seleccionDeRaza.getFont().getName(), Font.PLAIN, 20));
		this.seleccionDeColor.setSelectedIndex(indexSeleccionColor);
		this.seleccionDeColor.setRenderer(new ItemRenderer());
		
		this.add(this.nombreDelJugador);
		this.add(this.seleccionDeRaza);
		this.add(this.seleccionDeColor);
	}
	
	public Jugador obtenerJugador() {
		
		String nombre = this.nombreDelJugador.getText();
		String raza = String.valueOf(this.seleccionDeRaza.getSelectedItem());
		Color color = ((Item) this.seleccionDeColor.getSelectedItem()).getColor();
		
		Jugador jugador = null;
		
		switch (raza) {
		  case "Terran":
			  	jugador = new JugadorTerran(nombre, color);
		        break;
		  case "Protoss": 
			    jugador = new JugadorProtoss(nombre, color);
		        break;
		}

		return jugador;
	}
}

class ItemRenderer extends BasicComboBoxRenderer
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4780176907840572857L;

	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(
        JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index,
            isSelected, cellHasFocus); 

        if (value != null)
        {
            Item item = (Item)value;
            setText( item.getDescription().toUpperCase() );
        }

        if (index == -1)
        {
            Item item = (Item)value;
            setText( item.getDescription().toUpperCase() );
        }


        return this;
    }
}