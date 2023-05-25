package design;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class ToggleButtonDesignPetit extends JToggleButton{
	private static final long serialVersionUID = 1L;
	 
	public ToggleButtonDesignPetit(String txt) {
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
		setSelectedIcon(new ImageIcon("./img/buttonPetit_selected.png"));
	}
}
