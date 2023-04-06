import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreSimulation extends JFrame {

    public FenetreSimulation() {
        super("Simulation d'évacuation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        PanneauSimulation Simulation = new PanneauSimulation();
        getContentPane().add(Simulation, BorderLayout.CENTER);

        Point point1 = new Point(100, 100);
        Point point2 = new Point(200, 200);
        Simulation.ajouterPoint(point1);
        Simulation.ajouterPoint(point2);
        getContentPane().add(Simulation);

        JPanel panneauSimulation = new PanneauDroite();
        getContentPane().add(panneauSimulation, BorderLayout.EAST);

        setVisible(true);
    }
}
