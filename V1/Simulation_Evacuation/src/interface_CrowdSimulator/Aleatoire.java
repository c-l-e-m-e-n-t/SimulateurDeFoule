package interface_CrowdSimulator;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
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

import simulateur.PanneauSimulation;
public class Aleatoire extends JPanel implements ContenusDePanneaux {
	BufferedImage background;
	public Aleatoire() {
		super();
		this.elementsdepanneau("crowd.jpg");
	}
	public void elementsdepanneau(String nom_image) {
		try {
		
			 this.background = ImageIO.read(new File(nom_image));
			 setLayout(new BorderLayout());
			
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
