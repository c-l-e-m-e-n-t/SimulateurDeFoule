package interfaceSimulateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FenetreSimulation {
    boolean construction  = true;
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Simulation d'évacuation");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1080, 720);
        fenetre.setLocationRelativeTo(null);

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

        // Créer une grilleParamDroite dans la partie droite de la fenetre
        JPanel grilleParamDroite = new JPanel();
        grilleParamDroite.setLayout(new GridLayout(11, 1));
        fenetre.add(grilleParamDroite, BorderLayout.EAST);

        // Ajouter un bouton lancer la simulation en haut de la grilleParamDroite
        JButton boutonLancerSimu = new JButton("Lancer la simulation");
        grilleParamDroite.add(boutonLancerSimu);


        // Ajouter une liste déroulante a droite pour selectionner le nombre de personnes au dessus du bouton lancer la simulation dans la grilleParamDroite
        // Creer une liste déroulante de 1 a 500 personnes
        String[] nbPersonnes = new String[500];
        for (int i = 0; i < 500; i++) {
            nbPersonnes[i] = Integer.toString(i + 1);
        }
        JComboBox listeDeroulante = new JComboBox(nbPersonnes);
        // Créer une grille de 2 colonnes
        JPanel grille2cols = new JPanel();
        grille2cols.setLayout(new GridLayout(1, 2));
        grilleParamDroite.add(grille2cols);
        // Ajouter une étiquette "Nombre de personnes" a gauche de la liste déroulante
        JLabel etiquette = new JLabel("Nombre de personnes : ");
        grille2cols.add(etiquette);
        // Ajouter la liste déroulante a droite de l'étiquette
        grille2cols.add(listeDeroulante);

        // Sauter une ligne
        JLabel vide = new JLabel(" ");
        grilleParamDroite.add(vide);

        // Ajoutre le label param avancés en dessous de la liste déroulante centré
        JLabel paramAvances = new JLabel("--Paramètres avancés--");
        paramAvances.setHorizontalAlignment(JLabel.CENTER);
        grilleParamDroite.add(paramAvances);

        JPanel grilleParamAvancés = new JPanel();
        grilleParamAvancés.setLayout(new GridLayout(3, 2));
        grilleParamDroite.add(grilleParamAvancés);
        // Ajouter le nombre pourcentage d'enfants sous la forme d'une liste déroulante
        String[] nbEnfants = new String[100];
        for (int i = 0; i < 100; i++) {
            nbEnfants[i] = Integer.toString(i + 1);
        }
        JComboBox listeDeroulanteEnfants = new JComboBox(nbEnfants);
        // Creer une grille de 2 colonnes
        grilleParamDroite.add(grilleParamAvancés);
        // Ajouter une etiquette "Nombre d'enfants" a gauche de la liste déroulante
        JLabel etiquetteEnfants = new JLabel("Nombre d'enfants (%) : ");
        grilleParamAvancés.add(etiquetteEnfants);
        grilleParamAvancés.add(listeDeroulanteEnfants);

        JLabel etiquettePanique = new JLabel("Mode Panique : ");
        grilleParamAvancés.add(etiquettePanique);

        // Ajouter un bouton on/off pour le mode panique vert quand cliqué rouge sinon
        JToggleButton boutonOnOffPanique = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffPanique);

        // Ajout du rapport (graphique)

        JLabel etiquetteRapport = new JLabel("Rapport : ");
        grilleParamAvancés.add(etiquetteRapport);

        JToggleButton boutonOnOffRapport = new JToggleButton("On/Off");
        grilleParamAvancés.add(boutonOnOffRapport);

        // Sauter une case
        grilleParamDroite.add(vide);

        // Ajouter un bouton pour configurer les personnes
        JButton boutonConfigPersonnes = new JButton("Configurer les personnes");
        grilleParamDroite.add(boutonConfigPersonnes);
        
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

        // Rendre la fentre visible
        fenetre.setVisible(true);
    }

}