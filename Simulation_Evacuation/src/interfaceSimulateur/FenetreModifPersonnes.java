// creation de l'interface graphique de la fenêtre de modification des personnes
package interfaceSimulateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FenetreModifPersonnes {
    boolean construction  = true;
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Modification des personnes");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1080, 720);
        fenetre.setLocationRelativeTo(null);

        //creer le container de la fenetre
        Container contenu = fenetre.getContentPane();
        contenu.setLayout(new BorderLayout());

        // creer un bandeau en haut de la fenetre pour afficher le titre
        JPanel bandeau = new JPanel();
        bandeau.setBackground(Color.GRAY);
        fenetre.add(bandeau, BorderLayout.NORTH);
        //ecrire "Mode classique" dans le bandeau
        JLabel titre = new JLabel("Modification des personnes");
        bandeau.add(titre);
        //ajouter un bouton a droite du bandeau home
        JButton boutonHome = new JButton("Retour a la simulation");
        bandeau.add(boutonHome, BorderLayout.EAST);

        //creer une grille de 5 colonnes cans la partie centrale
        JPanel grille5cols = new JPanel();
        grille5cols.setLayout(new GridLayout(3, 5));
        fenetre.add(grille5cols, BorderLayout.CENTER);

        //ajouter une etiquette "vitesse" 
        JLabel etiquetteVitesse = new JLabel("Vitesse");
        grille5cols.add(etiquetteVitesse);

        // ajouter une etiquette "min"
        JLabel etiquetteMin = new JLabel("Min");
        grille5cols.add(etiquetteMin);

        // ajouter une case remplissable "min" par des chiffres
        JTextField caseMinVitesse = new JTextField();
        grille5cols.add(caseMinVitesse);
        
        // ajouter une etiquette "max"
        JLabel etiquetteMax = new JLabel("Max");
        grille5cols.add(etiquetteMax);

        //ajouter une case remplissable "max" par des chiffres
        JTextField caseMaxVitesse = new JTextField();
        //reduite la  hauteur de la case
        caseMaxVitesse.setPreferredSize(new Dimension(50, 20));
        grille5cols.add(caseMaxVitesse);
        
        //ajouter une etiquette "masse" 
        JLabel etiquetteMasse = new JLabel("Masse");
        grille5cols.add(etiquetteMasse);

        //ajouter une etiquette "reaction" 
        JLabel etiquetteReaction = new JLabel("Reaction");
        grille5cols.add(etiquetteReaction);
        
        //remplir la grille avec des chiffres 1,2,3,4,5,...
        for (int i = 0; i < 10; i++) {
            JLabel etiquette = new JLabel(Integer.toString(i + 1));
            grille5cols.add(etiquette);
        }
        

        //afficher la fenetre
        fenetre.setVisible(true);
    }
}