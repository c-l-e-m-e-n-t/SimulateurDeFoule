package affichage;

import javax.swing.*;
import java.awt.*;

import modele.*;

/** Classe qui gère la simulation de l'évacuation.*/
public class Simulation {

    private JPanel panel;
    private JPanel drawingPanel;

    public Simulation(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {
    	
        this.drawingPanel = drawingPanel;

        // Create the menu panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Bouton Pause
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> {
            // Code to place agents randomly
        });
        panel.add(pauseButton);

        // Bouton Play
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            // Code to place agents randomly
        });
        panel.add(playButton);

        // Bouton Retour
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
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

        // Update le panel
        frame.repaint();
    }

    public void run(JFrame frame) {
        // Time between each iteration (in ms)
        long sleep = 10;

        // Main loop
        while (!agentsSortis()) {

            // Update the position of the agents
            Deplacement.euler(SimulationData.agents, SimulationData.murs);
            System.out.println(SimulationData.agents[0].getPosition());

            // Repaint the drawing panel
            frame.update(frame.getGraphics()); // Ne pas toucher, ca marche comme ca

            // Pause between iterations
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
