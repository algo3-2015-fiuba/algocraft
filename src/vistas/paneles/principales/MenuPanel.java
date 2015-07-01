package vistas.paneles.principales;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	protected JPanel panelBase;
	
	public MenuPanel(JPanel panelBase) {
		
		this.panelBase = panelBase;
		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public void irAPanel(String nombrePanel) {
		CardLayout cl = (CardLayout)(this.panelBase.getLayout());
		cl.show(panelBase, nombrePanel);
	}
	
	public void irASiguientePanel() {
		CardLayout cl = (CardLayout)(this.panelBase.getLayout());
		cl.next(panelBase);
	}
}
