import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreSimulation extends JFrame {

    public FenetreSimulation() {
        super("Simulation d'évacuation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        JPanel panneauSimulation = new PanneauDroite();
        getContentPane().add(panneauSimulation, BorderLayout.EAST);

        setVisible(true);
    }
}
