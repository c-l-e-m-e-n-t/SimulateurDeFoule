package interface_CrowdSimulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class Accueil extends JPanel implements ContenusDePanneaux {
	BufferedImage background;
	public Accueil() {
		super();
		this.elementsdepanneau("Mon projet.jpg");

	}
	
	public void elementsdepanneau(String nom_image) {
		try {
		
		    this.background = ImageIO.read(new File(nom_image));
			
		    JLabel logoLabel = new JLabel("Bienvenue sur CrowdSimulator.", JLabel.CENTER);
		    logoLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 30));
		    logoLabel.setForeground(new Color(124, 252, 0));
		    add(logoLabel, BorderLayout.CENTER);
		    setPreferredSize(new Dimension(this.background.getWidth(null), this.background.getHeight(null)));
			
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