import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanneauSimulation extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel carteMenu;
    private final JPanel carteSimulation;
    private final JPanel carteEdition;


    public PanneauSimulation() {
        super(new BorderLayout());

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Carte du menu principal
        carteMenu = new JPanel(new BorderLayout());
        JButton boutonLancerSimulation = new JButton("Lancer la simulation");
        carteMenu.add(boutonLancerSimulation, BorderLayout.CENTER);

        // Carte de la simulation
        carteSimulation = new JPanel(new BorderLayout());
        JLabel labelSimulation = new JLabel("Simulation en cours...");
        carteSimulation.add(labelSimulation, BorderLayout.CENTER);
        JButton boutonRetourMenu = new JButton("Retour au menu");
        carteSimulation.add(boutonRetourMenu, BorderLayout.SOUTH);
        add(carteSimulation, "simulation");

        // Carte d'édition
        carteEdition = createPannelEdit();
        add(carteEdition, "edition");

        // Ajout des actions sur les boutons
        boutonLancerSimulation.addActionListener(e -> cardLayout.show(this, "simulation"));
        boutonRetourMenu.addActionListener(e -> cardLayout.show(this, "edition"));

    }

    private static JPanel createPannelEdit() {
        JPanel pannelEdit = new JPanel();
        pannelEdit.setLayout(new BorderLayout());
    
        //créer une grille de 11 lignes et 1 colonne
        JPanel grilleParamDroite = new JPanel();
        grilleParamDroite.setLayout(new GridLayout(11, 1));
        pannelEdit.add(grilleParamDroite, BorderLayout.CENTER);
    
        //Ajouter un bouton lancer la simulation en haut de la grilleParamDroite
        JButton boutonLancerSimu = new JButton("Lancer la simulation");
        grilleParamDroite.add(boutonLancerSimu);
    
        grilleParamDroite.add(new JLabel(" "));
    
        //ajouter un bouton modeles
        JButton boutonModele = new JButton("Modèles");
        grilleParamDroite.add(boutonModele);
    
        //ajouter un label outils
        JLabel outils = new JLabel("Outils");
        grilleParamDroite.add(outils);
    
        //créer une grille de 2 colonnes
        JPanel grilleOutils = new JPanel();
        grilleOutils.setLayout(new GridLayout(1, 2));
        grilleParamDroite.add(grilleOutils);
    
        //ajouter un bouton pour ajouter des murs
        JToggleButton boutonAjouterMurs = new JToggleButton("Mur");
        grilleOutils.add(boutonAjouterMurs);
    
        //ajouter un bouton pour ajouter des obstacles
        JToggleButton boutonAjouterObstacles = new JToggleButton("Obstacle");
        grilleOutils.add(boutonAjouterObstacles);
    
        //créer une grille outils2 de 2 colonnes
        JPanel grilleOutils2 = new JPanel();
        grilleOutils2.setLayout(new GridLayout(1, 2));
        grilleParamDroite.add(grilleOutils2);
    
        //ajouter un bouton pour ajouter des sorties
        JToggleButton boutonAjouterSorties = new JToggleButton("Sortie");
        grilleOutils2.add(boutonAjouterSorties);
        //ajouter une case vide
        JLabel vide = new JLabel(" ");
        grilleOutils2.add(vide);
    
        //ajouter un bouton retour
        JButton boutonRetour = new JButton("Retour");
        grilleParamDroite.add(boutonRetour);
    
        return pannelEdit;
    }
}