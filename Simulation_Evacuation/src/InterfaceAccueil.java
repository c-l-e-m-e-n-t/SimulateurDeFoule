package interface_CrowdSimulator;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceAccueil extends JFrame{
	CardLayout cardLayout;
	JPanel cards; 
	/** Constructeur qui va créer l'intreface graphique avec une image en arrière plan
	 * @param nom_image le nom de l'image utiliser 
	 * @param menuBar la barre de  menu de configuration
	 */
	
	public InterfaceAccueil() {
		super("CrowdSimulator");
		cards = new JPanel();
        cardLayout = new CardLayout();
        cards.setLayout(cardLayout);
	    
	    this.elementsdeframe("Mon projet.jpg");
	    add(cards, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	public void elementsdeframe(String nom_image) {
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
		new Menu(classique, modeClassique , "Classique", cardLayout );
		new Menu(creatif, modeCreatif , "Créatif", cardLayout );
		new Menu(classique, modeAleatoire , "Aléatoire", cardLayout );
		new Menu(classique, modeAccueil, "Accueil", cardLayout );
		cards.add(classique, "Classique");
        cards.add(creatif, "Créatif");
        cards.add(alea, "Aléatoire");
        cards.add(accueil, "Accueil");
		menudeConfig.add(modeClassique);
		menudeConfig.add(modeAleatoire);
		menudeConfig.add(modeCreatif);
		menudeConfig.add(modeAccueil);
		Barre_de_Menu.add(menudeConfig);
		setJMenuBar(Barre_de_Menu);
		cardLayout.show(cards, "Accueil" );
	}
	
		
		
	
   public static void main(String[] args) {
	 new  InterfaceAccueil();  
   }
}