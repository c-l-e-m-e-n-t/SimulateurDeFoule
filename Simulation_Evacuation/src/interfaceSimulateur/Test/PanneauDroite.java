import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class PanneauDroite extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel carteMenu;
    private final JPanel carteEdition;

    private int nombrePersonnes;

    JSlider sliderNombrePersonnes = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);


    public PanneauDroite() {
        super(new BorderLayout());

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Carte du menu principal
        carteMenu = createPannelClassique(cardLayout);;
        add(carteMenu, "classique");

        // Carte d'édition
        carteEdition = createPannelEdit(cardLayout);
        add(carteEdition, "edition");

        // Carte des personnes
        JPanel cartePersonnes = createPannelPersonnes(cardLayout);
        add(cartePersonnes, "personnes");

    }

    private JPanel createPannelEdit(CardLayout cardLayout) {
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
        boutonRetour.addActionListener(e -> cardLayout.show(this, "classique"));
    
        return pannelEdit;
    }

    private JPanel createPannelClassique(CardLayout cardLayout) {
        JPanel pannelClassic = new JPanel();
        pannelClassic.setLayout(new BorderLayout());

        // Créer une grilleParamDroite dans la partie droite de la fenetre
        JPanel grilleParamDroite = new JPanel();
        grilleParamDroite.setLayout(new GridLayout(11, 1));
        pannelClassic.add(grilleParamDroite, BorderLayout.CENTER);

        // Ajouter un bouton lancer la simulation en haut de la grilleParamDroite
        JButton boutonLancerSimu = new JButton("Lancer la simulation");
        grilleParamDroite.add(boutonLancerSimu);

        // Sauter une ligne
        JLabel vide = new JLabel(" ");
        grilleParamDroite.add(vide);

        // Ajouter un bouton pour configurer les personnes
        JButton boutonConfigPiece = new JButton("Configurer la pièce");
        grilleParamDroite.add(boutonConfigPiece);

        // Ajouter un bouton pour configurer les personnes
        JButton boutonConfigPersonnes = new JButton("Configurer les personnes");
        grilleParamDroite.add(boutonConfigPersonnes);

        // Sauter une ligne
        grilleParamDroite.add(vide);

        // Ajoutre le label param avancés en dessous de la liste déroulante centré
        JLabel paramAvances = new JLabel("--Paramètres avancés--");
        paramAvances.setHorizontalAlignment(JLabel.CENTER);
        grilleParamDroite.add(paramAvances);

        JPanel grilleParamAvancés = new JPanel();
        grilleParamAvancés.setLayout(new GridLayout(3, 2));
        grilleParamDroite.add(grilleParamAvancés);


        JLabel etiquettePanique = new JLabel("Mode Panique : ");
        grilleParamAvancés.add(etiquettePanique);

        // Ajouter un bouton on/off pour le mode panique 
        JToggleButton boutonOnOffPanique = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffPanique);

        //ajout d'un bouton on/off pour les phénomènes exterieurs
        JLabel etiquettePhenomene = new JLabel("Phénomènes extérieurs : ");
        grilleParamAvancés.add(etiquettePhenomene);
        JToggleButton boutonOnOffPhenomene = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffPhenomene);

        // Ajout du rapport (graphique)
        JLabel etiquetteRapport = new JLabel("Rapport : ");
        grilleParamAvancés.add(etiquetteRapport);

        JToggleButton boutonOnOffRapport = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffRapport);

        // Sauter une case
        grilleParamDroite.add(vide);
        
        // Ajouter une section pour afficher les informations sur la simulation
        JPanel grilleInfo = new JPanel();
        grilleInfo.setLayout(new GridLayout(2, 2));
        grilleParamDroite.add(grilleInfo);

        // Ajout d'une section titre avec une case remplissable par l'utilisateur
        JLabel titreInfo = new JLabel("Titre : ");
        grilleInfo.add(titreInfo);
        JTextField titreInfoRemplissable = new JTextField();
        grilleInfo.add(titreInfoRemplissable);

        // Ajout d'une section titre avec une case remplissable par l'utilisateur
        JLabel auteurInfo = new JLabel("Auteur : ");
        grilleInfo.add(auteurInfo);
        JTextField auteurInfoRemplissable = new JTextField();
        grilleInfo.add(auteurInfoRemplissable);

        // Ajout d'une section commentaires avec une case remplissable par l'utilisateur sur plusieurs lignes
        JLabel commentaireInfo = new JLabel("Commentaires : ");
        grilleParamDroite.add(commentaireInfo);
        JTextArea commentaireInfoRemplissable = new JTextArea();
        commentaireInfoRemplissable.setLineWrap(true);
        commentaireInfoRemplissable.setWrapStyleWord(true);
        commentaireInfoRemplissable.setRows(5);
        commentaireInfoRemplissable.setColumns(20);
        JScrollPane scroll = new JScrollPane(commentaireInfoRemplissable);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        grilleParamDroite.add(scroll);


        // configurer les boutons
        boutonConfigPiece.addActionListener(e -> cardLayout.show(this, "edition"));
        boutonConfigPersonnes.addActionListener(e -> cardLayout.show(this, "personnes"));

        return pannelClassic;
    }

    private JPanel createPannelPersonnes(CardLayout cardLayout) {
        JPanel pannelPersonnes = new JPanel();
        pannelPersonnes.setLayout(new BorderLayout());

        // Créer une grilleParamDroite dans la partie droite de la fenetre
        JPanel grilleParamDroite = new JPanel();
        grilleParamDroite.setLayout(new GridLayout(11, 1));
        pannelPersonnes.add(grilleParamDroite, BorderLayout.EAST);

        // Ajouter un bouton lancer la simulation en haut de la grilleParamDroite
        JButton boutonLancerSimu = new JButton("Lancer la simulation");
        grilleParamDroite.add(boutonLancerSimu);


        //ajout d'un label nombre de personnes
        JLabel etiquetteNombrePersonnes = new JLabel("Nombre de personnes : 0");
        grilleParamDroite.add(etiquetteNombrePersonnes);

        //ajout d'un slider pour le nombre de personnes allant de 0 à 100 en ffichant le nombre courrant
        sliderNombrePersonnes.setLabelTable(sliderNombrePersonnes.createStandardLabels(10));
        sliderNombrePersonnes.setPaintLabels(true);
        grilleParamDroite.add(sliderNombrePersonnes);
        sliderNombrePersonnes.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                etiquetteNombrePersonnes.setText("Nombre de personnes : " + slider.getValue());
                nombrePersonnes = slider.getValue();
            }
        });

        //creer une grille de 2 colonnes
        JPanel grilleMasse = new JPanel();
        grilleMasse.setLayout(new GridLayout(1, 2));
        grilleParamDroite.add(grilleMasse);

        //ajout du label masse
        JLabel etiquetteMasse = new JLabel("Masse : 0 - 100");
        grilleMasse.add(etiquetteMasse);

        //ajout d'un range slider pour la masse allant de 0 à 100
        RangeSlider sliderMasse = new RangeSlider();
        sliderMasse.setMinimum(0);
        sliderMasse.setMaximum(100);
        sliderMasse.setValue(0);
        sliderMasse.setUpperValue(100);
        grilleMasse.add(sliderMasse);
        sliderMasse.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                etiquetteMasse.setText("Masse : "+ String.valueOf(slider.getValue()) + " - " + String.valueOf(slider.getUpperValue()));
                FenetreSimulation.parametresSimulation.setMasseMin(slider.getValue());
                FenetreSimulation.parametresSimulation.setMasseMax(slider.getUpperValue());
            }
        });

        //creer une grille de 2 colonnes pour le rayon
        JPanel grilleRayon = new JPanel();
        grilleRayon.setLayout(new GridLayout(1, 2));
        grilleParamDroite.add(grilleRayon);

        //ajout du label rayon
        JLabel etiquetteRayon = new JLabel("Rayon : 0 - 100");
        grilleRayon.add(etiquetteRayon);

        //ajout d'un range slider pour le rayon allant de 0 à 100
        RangeSlider sliderRayon = new RangeSlider();
        sliderRayon.setMinimum(0);
        sliderRayon.setMaximum(100);
        sliderRayon.setValue(0);
        sliderRayon.setUpperValue(100);
        grilleRayon.add(sliderRayon);
        sliderRayon.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                etiquetteRayon.setText("Rayon : "+ String.valueOf(slider.getValue()) + " - " + String.valueOf(slider.getUpperValue()));
                FenetreSimulation.parametresSimulation.setRayonMin(slider.getValue());
                FenetreSimulation.parametresSimulation.setRayonMax(slider.getUpperValue());
            }
        });

        //creer une grille de 2 colonnes pour la vitesse
        JPanel grilleVitesse = new JPanel();
        grilleVitesse.setLayout(new GridLayout(1, 2));
        grilleParamDroite.add(grilleVitesse);

        //ajout du label vitesse
        JLabel etiquetteVitesse = new JLabel("Vitesse : 0 - 100");
        grilleVitesse.add(etiquetteVitesse);
        
        //ajout d'un range slider pour la vitesse allant de 0 à 100
        RangeSlider sliderVitesse = new RangeSlider();
        sliderVitesse.setMinimum(0);
        sliderVitesse.setMaximum(100);
        sliderVitesse.setValue(0);
        sliderVitesse.setUpperValue(100);
        grilleVitesse.add(sliderVitesse);
        sliderVitesse.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                etiquetteVitesse.setText("Vitesse : "+ String.valueOf(slider.getValue()) + " - " + String.valueOf(slider.getUpperValue()));
                FenetreSimulation.parametresSimulation.setVitesseMin(slider.getValue());
                FenetreSimulation.parametresSimulation.setVitesseMax(slider.getUpperValue());
            }
        });

        //ajouter une case vide
        JLabel etiquetteVide = new JLabel("");
        grilleParamDroite.add(etiquetteVide);

        //ajout d'un bouton de configuration d'un agent
        JButton boutonAgent = new JButton("Configurer un agent");
        grilleParamDroite.add(boutonAgent);

        //ajout d'un bouton retour a l'ecran principal
        JButton boutonRetour = new JButton("Retour");
        grilleParamDroite.add(boutonRetour);

        // configurer les boutons
        boutonRetour.addActionListener(e -> cardLayout.show(this, "classique"));

        return pannelPersonnes;

    }

    //getters

    public JSlider getSliderNbPersonnes() {
        return sliderNombrePersonnes;
    }

}