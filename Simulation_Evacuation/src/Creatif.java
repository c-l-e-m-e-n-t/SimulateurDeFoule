package interface_CrowdSimulator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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

public class Creatif extends JPanel implements ContenusDePanneaux {
	BufferedImage background;
	/*Constructeur qui crée la page du mode créatif*/
	public Creatif() {
		super();
		this.elementsdepanneau("couleurs.jpg");

	}
	public void elementsdepanneau(String nom_image) {
		try {
		 
			 this.background = ImageIO.read(new File(nom_image));
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);
    }
}