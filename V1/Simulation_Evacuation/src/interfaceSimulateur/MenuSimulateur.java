import javax.swing.*;
import java.awt.*;

/** Menu du simulateur. */
public class MenuSimulateur {

	public MenuSimulateur(JFrame fenetre) {
		// Créer le container de la fenetre      
		Container contenu = fenetre.getContentPane();   
		contenu.setLayout(new BorderLayout());

        // Créer un bandeau en haut de la fenetre pour afficher le titre
        JPanel bandeau = new JPanel();
        bandeau.setBackground(Color.GRAY);
        fenetre.add(bandeau, BorderLayout.NORTH);
        // Ecrire "Mode classique" dans le bandeau
        JLabel titre = new JLabel("Menu");
        bandeau.add(titre);

        // Creation de la zone des paramètres dans la partie droite de la fenetre
        JPanel cadreParametre = new JPanel();
        cadreParametre.setLayout(new GridLayout(11, 1));
        cadreParametre.setBackground(Color.LIGHT_GRAY);
        fenetre.add(cadreParametre, BorderLayout.EAST);
        
        // Creation de la zone de simulation dans la partie gauche de la fenetre
        JPanel cadreSimulation = new JPanel();
        cadreSimulation.setBackground(Color.WHITE);
        // Mettre des bordures autour de la zone de simulation
        cadreSimulation.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        fenetre.add(cadreSimulation, BorderLayout.CENTER);

        // Ajouter un bouton lancer la simulation
        JButton boutonLancerSimu = new JButton("Lancer la simulation");
        cadreParametre.add(boutonLancerSimu);

        cadreParametre.add(new JLabel(" "));

        // Ajouter un bouton configuration de la pièce
        JButton boutonConfigPiece = new JButton("Configurer le bâtiment");
        cadreParametre.add(boutonConfigPiece);

        cadreParametre.add(new JLabel(" "));

        // Ajouter un bouton configuration des agents
        JButton boutonConfigPers = new JButton("Configurer les personnes");
        cadreParametre.add(boutonConfigPers);

        // Ajoutre le label param avancés en dessous de la liste déroulante centré
        JLabel paramAvances = new JLabel("--Paramètres avancés--");
        paramAvances.setHorizontalAlignment(JLabel.CENTER);
        cadreParametre.add(paramAvances);

        // Grille de paramètres avancés
        JPanel grilleParamAvancés = new JPanel();
        grilleParamAvancés.setLayout(new GridLayout(3, 2));
        cadreParametre.add(grilleParamAvancés);

        // Panique : Bouton on / off
        JLabel etiquettePanique = new JLabel("Panique : ");
        grilleParamAvancés.add(etiquettePanique);
        JToggleButton boutonOnOffPanique = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffPanique);

        // Phénomènes Extérieurs : Bouton ON / OFF
        JLabel etiquettePhenoExte = new JLabel("Phénomènes extérieurs : ");
        grilleParamAvancés.add(etiquettePhenoExte);
        JToggleButton boutonOnOffPhenoExte = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffPhenoExte);

        // Ajout du rapport (graphique)
        JLabel etiquetteRapport = new JLabel("Rapport : ");
        grilleParamAvancés.add(etiquetteRapport);
        JToggleButton boutonOnOffRapport = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffRapport);
        

        // Redessiner le contenu de la fenêtre
        fenetre.revalidate();
        fenetre.repaint();
	}
}
