import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.random.*;

public class FenetreSimulation extends JFrame {
    int nbPersonnesCourant = 0;
    public FenetreSimulation() {
        super("Simulation d'évacuation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        PanneauSimulation Simulation = new PanneauSimulation();
        JPanel panSimulation = Simulation;
        getContentPane().add(panSimulation, BorderLayout.CENTER);

        JPanel paramSimulation = new PanneauDroite();
        getContentPane().add(paramSimulation, BorderLayout.EAST);

        JSlider sliderNbPersonnes = ((PanneauDroite) paramSimulation).getSliderNbPersonnes();

        // Ajouter un ChangeListener au JSlider pour écouter les changements de valeur
        sliderNbPersonnes.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int nbPersonnes = sliderNbPersonnes.getValue();
                while (nbPersonnesCourant < nbPersonnes) {
                    int x = (int) (Math.random() * panSimulation.getWidth());
                    int y = (int) (Math.random() * panSimulation.getHeight());
                    Simulation.ajouterPersonne(new Point(x, y));
                    nbPersonnesCourant++;
                }
                System.out.println("Nombre de personnes : " + nbPersonnes);
                getContentPane().remove(Simulation);
                getContentPane().add(Simulation);
            }
        });

        setVisible(true);
    }
}
