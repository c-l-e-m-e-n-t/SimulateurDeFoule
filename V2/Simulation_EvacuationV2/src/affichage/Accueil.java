package affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import modele.*;
import modele.Point;

/** Programme Principal.*/
public class Accueil {

    private JFrame frame;
    public static JPanel panel;
    private JLabel imageLabel;

    public Accueil() {
        frame = new JFrame("Simulation d'évacuation");
        panel = new JPanel(new BorderLayout());
        imageLabel = new JLabel(new ImageIcon("./img/bg.png")); // Image d'accueil
        //imageLabel = new JLabel(new ImageIcon("./img/logo.png")); // Image d'accueil
        //pressAnyKeyLabel = new JLabel("Appuyer sur une touche", SwingConstants.CENTER);
        //pressAnyKeyLabel.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(imageLabel, BorderLayout.CENTER);
        //panel.add(pressAnyKeyLabel, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setVisible(true);

        // Ajouter un key listener au frame pour détecter si une touche est pressée.
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // Si une touche est pressée, on passe a la fenêtre Menu
                panel.remove(imageLabel);
                //panel.remove(pressAnyKeyLabel);
                new Menu();
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        SimulationData.agents = new Agent[0];
        SimulationData.murs = new Segment[0];
        SimulationData.sortie = new Point[1];
        SimulationData.sortie[0] = new Point(5, 10);
        new Accueil();
    }

}
