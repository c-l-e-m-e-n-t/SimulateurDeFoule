package outils;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonDesign extends JButton{
	private static final long serialVersionUID = 1L;
	 
	public ButtonDesign(String txt) {
	    super(txt);
	    
	    setForeground(Color.WHITE);
	     
	    setOpaque(false);
	    setContentAreaFilled(false);
	    setBorderPainted(false); 
	    setFocusPainted(false);
	     
	    setHorizontalAlignment(SwingConstants.CENTER);
	    setHorizontalTextPosition(SwingConstants.CENTER);
	     
	    //setIcon(new ImageIcon("./images/button.png"));
	    //setRolloverIcon(new ImageIcon("./images/button.png"));
	    }

}
