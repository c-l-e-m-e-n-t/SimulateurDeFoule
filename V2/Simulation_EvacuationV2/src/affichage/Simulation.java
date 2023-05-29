package affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import modele.*;
import javax.swing.Timer;
import design.ButtonDesign;

/** Classe qui gère la simulation de l'évacuation.*/
public class Simulation {

    private Timer timer;

    public Simulation(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {

        // Create the menu panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Bouton Pause
        ButtonDesign pauseButton = new ButtonDesign("Pause");
        pauseButton.addActionListener(e -> {
            timer.stop();
        });
        panel.add(pauseButton);

        // Bouton Play
        ButtonDesign playButton = new ButtonDesign("Play");
        playButton.addActionListener(e -> {
            timer.start();
        });
        panel.add(playButton);

        // Bouton Retour
        ButtonDesign backButton = new ButtonDesign("Retour");
        backButton.addActionListener(e -> {
            timer.stop();
            frame.getContentPane().remove(panel);
            frame.getContentPane().add(menuPanel, BorderLayout.EAST);
            frame.revalidate();
            frame.repaint();
        });
        panel.add(backButton);

        // Définir la disposition du panneau et l'ajouter au frame
        frame.getContentPane().add(panel, BorderLayout.EAST);
        frame.getContentPane().add(drawingPanel, BorderLayout.WEST);
        frame.revalidate();

        frame.update(frame.getGraphics()); // Ne pas toucher, ca marche comme ca
    }

    public void run(JFrame frame) {
        // Time between each iteration (in ms)
        int sleep = 30;
        timer = new Timer(sleep, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Update the position of the agents
                Deplacement.euler(SimulationData.agents, SimulationData.murs);
                //System.out.println(SimulationData.agents[0].getPosition());

                // Repaint the drawing panel
                frame.repaint(); // Ne pas toucher, ca marche comme ca (a cause du actionperformed)

                if (agentsSortis()) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private boolean agentsSortis() {
        // Voir si les agents sont sortis
        for (Agent agent : SimulationData.agents) {
            if (!agent.getEstSorti()) {
                return false;
            }
        }
        return true;
    }
}
