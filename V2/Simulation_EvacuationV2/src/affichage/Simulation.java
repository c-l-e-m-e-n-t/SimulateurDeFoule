package affichage;

import javax.swing.*;
import java.awt.*;

import modele.*;

public class Simulation {

    private JFrame frame;
    private JPanel panel;
    private JPanel drawingPanel;

    private Agent[] agents;
    private Segment[] murs;

    public Simulation(Agent[] agents, Segment[] murs, JFrame frame, JPanel drawingPanel, JPanel menuPanel) {
    	this.agents = agents;
    	this.murs = murs;
    	
    	this.frame = frame;
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

    public void start() {
        // Time between each iteration (in ms)
        long sleep = 10;

        // Main loop
        while (!agentsSortis()) {

            // Update the position of the agents
            Deplacement.euler(agents, murs);
            drawingPanel.repaint();

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
        for (Agent agent : agents) {
            if (!agent.getEstSorti()) {
                return false;
            }
        }
        return true;
    }

}
