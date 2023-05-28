package affichage;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import modele.*;
import outils.BureauObstacle;
import outils.ChaiseObstacle;
import modele.Point;

import outils.Sauvegarde;

public class MenuBar {
    public static double scale;

    public static JMenuBar createMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fichier");
        JMenu editMenu = new JMenu("Édition");
        JMenu helpMenu = new JMenu("Aide");
        JMenu reinitialiserMenu = new JMenu("Reinitialiser");
        JMenuItem newpage = new JMenuItem("nouvelle simulation");
        JMenuItem openItem = new JMenuItem("Ouvrir");
        JMenuItem saveItem = new JMenuItem("Enregistrer");
        JMenuItem exitItem = new JMenuItem("Quitter");
        JMenuItem zoome = new JMenuItem("Zom In");
        JMenuItem dezoom = new JMenuItem("Zom out");
        JMenuItem aide = new JMenuItem("manuel-utilisateur");

        // Ajouter un écouteur d'événement pour l'élément "Ouvrir"
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sauvegarde.charger(Accueil.panel);

            }
        });

        // Ajouter un écouteur d'événement pour l'élément "Enregistrer"
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sauvegarde.sauvegarder();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        newpage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu();
            }
        });

        reinitialiserMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationData.agents = new Agent[0];
                SimulationData.sortie = new Point[0];
                SimulationData.murs = new Segment[0];
                SimulationData.bureau = new BureauObstacle[0];
                SimulationData.chaise = new ChaiseObstacle[0];
                System.exit(0);

            }
        });

        zoome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scale = 1.2;

            }
        });

        dezoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scale = 0.8;

            }
        });

        aide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("HELP");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Création des ImageIcon pour les images

                ImageIcon imageIcon = new ImageIcon("./img/image1.jpg");
                //ImageIcon imageIcon2 = new ImageIcon("./img/image2.jpg");
         

                // Création du JPanel avec les deux images
                JLabel imageLabel = new JLabel(imageIcon);
                
                 JPanel panel = new JPanel() ;
                 panel.add(imageLabel);
                  
                
                 

                

                
                frame.pack();
                frame.setSize(1000, 1000);

                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        fileMenu.add(newpage);
        editMenu.add(zoome);
        editMenu.add(dezoom);
        helpMenu.add(aide);

        // Ajouter d'autres éléments de menu et actions ici

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        menuBar.add(reinitialiserMenu);
        return menuBar;
    }

}
