package interface_CrowdSimulator;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class InterfaceAccueil extends JFrame{
	CardLayout cardLayout;
	
	/** Constructeur qui va créer l'intreface graphique avec une image en arrière plan
	 * @param nom_image le nom de l'image utiliser 
	 * @param menuBar la barre de  menu de configuration
	 */
	
	public InterfaceAccueil() {
		super("CrowdSimulator");
		
        this.cardLayout = new CardLayout();
        Container contenu_panneaux = getContentPane();
        contenu_panneaux.setLayout(this.cardLayout);
	    
		this.elementsdeframe("Mon projet.jpg", contenu_panneaux);
	    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
	}
	public void elementsdeframe(String nom_image, Container contenu_panneaux ) {
		JMenuBar Barre_de_Menu = new JMenuBar();
		JMenu menudeConfig = new JMenu("Modes de Travail");
		JMenuItem modeClassique = new JMenuItem("Classique"); 
		JMenuItem modeCreatif = new JMenuItem("Créatif");
		JMenuItem modeAleatoire = new JMenuItem("Aléatoire"); 
		JMenuItem modeAccueil = new JMenuItem( "Accueil" );
		JPanel classique = new Classique();
		JPanel creatif = new Creatif();
		JPanel alea = new Aleatoire();
		JPanel accueil = new Accueil();
		
		contenu_panneaux.add(classique, "Classique");
		contenu_panneaux.add(creatif, "Créatif");
		contenu_panneaux.add(alea, "Aléatoire");
		contenu_panneaux.add(accueil, "Accueil");
		modeClassique.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(InterfaceAccueil.this.getContentPane(), modeClassique.getText());}
            });
		modeCreatif.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(InterfaceAccueil.this.getContentPane(), modeCreatif.getText());}
            });
		modeAleatoire.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(InterfaceAccueil.this.getContentPane(), modeAleatoire.getText());}
            });
		modeAccueil.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(InterfaceAccueil.this.getContentPane(),modeAccueil.getText());}
            });
		
		
		menudeConfig.add(modeClassique);
		menudeConfig.add(modeAleatoire);
		menudeConfig.add(modeCreatif);
		menudeConfig.add(modeAccueil);
		Barre_de_Menu.add(menudeConfig);
		setJMenuBar(Barre_de_Menu);
	    this.cardLayout.show(this.getContentPane(), "Accueil");
	   
	}
	
   public static void main(String[] args) {
	 new  InterfaceAccueil();  
   }
}