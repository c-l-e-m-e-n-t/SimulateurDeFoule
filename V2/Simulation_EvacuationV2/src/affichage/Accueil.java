package affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Accueil {

    private JFrame frame;
    private JPanel panel;
    private JLabel imageLabel;
    private JLabel pressAnyKeyLabel;

    public Accueil() {
        frame = new JFrame("Simulation d'évacuation");
        panel = new JPanel(new BorderLayout());
        imageLabel = new JLabel(new ImageIcon("crowd.jpg")); // Image d'accueil
        pressAnyKeyLabel = new JLabel("Appuyer sur une touche", SwingConstants.CENTER);
        pressAnyKeyLabel.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(pressAnyKeyLabel, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setVisible(true);

        // Ajouter un key listener au frame pour détecter si une touche est pressée.
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // Si une touche est pressée, on passe a la fenêtre Menu
                panel.remove(imageLabel);
                panel.remove(pressAnyKeyLabel);
                Menu menu = new Menu();
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        Accueil accueil = new Accueil();
    }

}
