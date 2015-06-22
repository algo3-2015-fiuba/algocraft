package vistas2.utilidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class CampoDeTextoPredeterminado extends JTextField {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3218993033002386177L;
	
	private final String textoPredeterminado;
	
	public CampoDeTextoPredeterminado(final String textoPredeterminado) {
		this.textoPredeterminado = textoPredeterminado;
		
		this.setText(textoPredeterminado);
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0, 0, 0)), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		this.setColumns(20);
		
		this.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
            	if(((JTextField)e.getSource()).getText().equals(textoPredeterminado)) {
            		((JTextField)e.getSource()).setText("");
            	}
            }

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
	}
	
	@Override
	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
          RenderingHints.VALUE_RENDER_QUALITY);
        super.paintComponent(g2);
    }
}
