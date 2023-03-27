package interfaceSimulateur;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class FenetreSimulation {
    boolean construction  = true;
    private static void PannelClassique(JFrame fenetre) {
        // Créer une grilleParamDroite dans la partie droite de la fenetre
        JPanel grilleParamDroite = new JPanel();
        grilleParamDroite.setLayout(new GridLayout(11, 1));
        fenetre.add(grilleParamDroite, BorderLayout.EAST);

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

        //si je bouton configurer les personnes est cliqué changer de menu et reupdate la partie droite de la fenetre
        boutonConfigPersonnes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear la partie east de la fenetre
                fenetre.remove(grilleParamDroite);
                PannelPersonnes(fenetre);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });
    }

    private static void PannelPersonnes(JFrame fenetre){
        // Créer une grilleParamDroite dans la partie droite de la fenetre
        JPanel grilleParamDroite = new JPanel();
        grilleParamDroite.setLayout(new GridLayout(11, 1));
        fenetre.add(grilleParamDroite, BorderLayout.EAST);

        // Ajouter un bouton lancer la simulation en haut de la grilleParamDroite
        JButton boutonLancerSimu = new JButton("Lancer la simulation");
        grilleParamDroite.add(boutonLancerSimu);


        //ajout d'un label nombre de personnes
        JLabel etiquetteNombrePersonnes = new JLabel("Nombre de personnes : 0");
        grilleParamDroite.add(etiquetteNombrePersonnes);

        //ajout d'un slider pour le nombre de personnes allant de 0 à 100 en ffichant le nombre courrant
        JSlider sliderNombrePersonnes = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        sliderNombrePersonnes.setLabelTable(sliderNombrePersonnes.createStandardLabels(10));
        sliderNombrePersonnes.setPaintLabels(true);
        grilleParamDroite.add(sliderNombrePersonnes);
        sliderNombrePersonnes.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                etiquetteNombrePersonnes.setText("Nombre de personnes : " + slider.getValue());
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

        //si je bouton retour est cliqué changer de menu et reupdate la partie droite de la fenetre
        boutonRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear la partie east de la fenetre
                fenetre.remove(grilleParamDroite);
                PannelClassique(fenetre);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });

    }
    
    private static void baseFenetre(JFrame fenetre){
        // Créer le container de la fenetre
        Container contenu = fenetre.getContentPane();
        contenu.setLayout(new BorderLayout());

        // Créer un bandeau en haut de la fenetre pour afficher le titre
        JPanel bandeau = new JPanel();
        bandeau.setBackground(Color.GRAY);
        fenetre.add(bandeau, BorderLayout.NORTH);
        // Ecrire "Mode classique" dans le bandeau
        JLabel titre = new JLabel("Mode classique");
        bandeau.add(titre);
        // Ajouter un bouton a droite du bandeau home
        JButton boutonHome = new JButton("Home");
        bandeau.add(boutonHome, BorderLayout.EAST);
        
        PannelClassique(fenetre);
        
        // Ajouter un bandeau en bas de la page pour afficher les auteurs du logiciel
        JPanel bandeauBas = new JPanel();
        bandeauBas.setBackground(Color.GRAY);
        fenetre.add(bandeauBas, BorderLayout.SOUTH);
        JLabel auteur = new JLabel("ENSEEIHT - Brandois Félix - Cognard Clément - El Guerraoui Oussama - Fresco Alan - Mimoun Ibtissam - Fraine Sofiane - Murugesapillai Vithursan - 2023");
        bandeauBas.add(auteur);


        // Creation de la zone de simulation
        JPanel cadreSimulation = new JPanel();
        cadreSimulation.setBackground(Color.WHITE);
        // Mettre des bordures autour de la zone de simulation
        cadreSimulation.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        fenetre.add(cadreSimulation, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Simulation d'évacuation");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1080, 720);
        fenetre.setLocationRelativeTo(null);

        baseFenetre(fenetre);

        // Rendre la fentre visible
        fenetre.setVisible(true);
    }

}
