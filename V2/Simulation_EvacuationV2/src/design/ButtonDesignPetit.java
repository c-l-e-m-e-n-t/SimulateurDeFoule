package design;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonDesignPetit extends JButton{
	private static final long serialVersionUID = 1L;
	 
	public ButtonDesignPetit(String txt) {
	    super(txt);
	    
	    setForeground(Color.WHITE);
	     
	    setOpaque(false);
	    setContentAreaFilled(false);
	    setBorderPainted(false); 
	    setFocusPainted(false);
	     
	    setHorizontalAlignment(SwingConstants.CENTER);
	    setHorizontalTextPosition(SwingConstants.CENTER);
	     
	    setIcon(new ImageIcon("./img/buttonPetit.png"));
	    setRolloverIcon(new ImageIcon("./img/buttonPetit_pressed.png"));
	}
}
