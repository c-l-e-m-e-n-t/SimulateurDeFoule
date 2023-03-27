package interfaceSimulateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Creatif extends JPanel {
	public Creatif() {
		super();
		this.elementsdeframe("Mon projet.jpg");

	}
	public void elementsdeframe(String nom_image) {
		try {
		
			BufferedImage background = ImageIO.read(new File(nom_image));
			JLabel image = new JLabel(new ImageIcon(background) );
			setLayout(new BorderLayout());
			add(image);
			setLayout(new FlowLayout());
			
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
}