package interfaceSimulateur2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Menu implements ActionListener {
    JPanel panneau;
	JMenuItem mode;
	String nom_mode;
	CardLayout card;
	public Menu( JPanel panneau, JMenuItem nouveau_mode, String nouveau_nom, CardLayout card) {
		this.nom_mode = nouveau_nom;
		this.panneau = panneau;
		this.mode = nouveau_mode;
		this.card = card;
		this.mode.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.mode) {
			this.card.show(this.panneau, this.nom_mode);
		}
		
		
	}

}
