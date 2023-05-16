package affichage;

import javax.swing.*;
import java.awt.*;

import modele.*;

/** Classe qui gère les opérations du menu.*/
public class Menu {

    private JFrame frame;
    private JPanel panel;
    private JPanel drawingPanel;

    public Menu() {
        frame = new JFrame("Simulation d'évacuation - Menu");
        panel = new JPanel();
        drawingPanel = new DrawingPanel();

        // Ajouter des éléments de menu au panel
        JButton launchButton = new JButton("Lancer la simulation");
        panel.add(launchButton);
        panel.add(new JLabel(""));

        JButton configRoomButton = new JButton("Configuration de la piece");
        panel.add(configRoomButton);
        JButton configAgentButton = new JButton("Configuration des agents");
        panel.add(configAgentButton);
        panel.add(new JLabel(""));

        JToggleButton panicButton = new JToggleButton("Panique (ON / OFF)");
        panel.add(panicButton);
        JToggleButton externalPhenomenaButton = new JToggleButton("Phénomènes externes (ON / OFF)");
        panel.add(externalPhenomenaButton);
        JToggleButton reportsButton = new JToggleButton("Rapports (ON / OFF)");
        panel.add(reportsButton);
        panel.add(new JLabel(""));

        JButton exitButton = new JButton("Quitter");
        panel.add(exitButton);

        // Définir la disposition du panneau et l'ajouter au frame
        panel.setLayout(new GridLayout(10, 1));
        frame.getContentPane().add(panel, BorderLayout.EAST);
        frame.getContentPane().add(drawingPanel, BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setVisible(true);

        // Ajouter des action listeners aux buttons
        launchButton.addActionListener(e -> {
        	boolean allNull = true;
        	for (Agent agent : SimulationData.agents) {
        	    if (agent != null) {
        	        allNull = false;
        	        break;
        	    }
        	}
        	if (!allNull) {
        		frame.getContentPane().remove(panel);
        		Simulation simulation = new Simulation(frame, drawingPanel, panel);
        		// Pause between iterations
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
        		simulation.run(drawingPanel);
        	} else {
        		System.out.println("La simulation n'est pas configuré entièrement");
        	}
        });
        configRoomButton.addActionListener(e -> {
            frame.getContentPane().remove(panel);
            MenuObstacles menuObstacles = new MenuObstacles(frame, drawingPanel, panel);
        });
        configAgentButton.addActionListener(e -> {
            frame.getContentPane().remove(panel);
            MenuAgents menuAgents = new MenuAgents(frame, drawingPanel, panel);
        });
        panicButton.addActionListener(e -> {
            // Code to toggle panic mode
        });
        externalPhenomenaButton.addActionListener(e -> {
            // Code to toggle external phenomena
        });
        reportsButton.addActionListener(e -> {
            // Code to toggle reports
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    public class DrawingPanel extends JPanel {

        public DrawingPanel() {
            setPreferredSize(new Dimension(720, 720));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Dessiner les murs
            g.setColor(Color.BLACK);
            for (Segment mur : SimulationData.murs) {
                if (mur != null) {
                	int x1 = (int) (mur.getExtremite1().getX() * SimulationData.NORMALISER);
                	int y1 = (int) (mur.getExtremite1().getY() * SimulationData.NORMALISER);
                	int x2 = ( int) (mur.getExtremite2().getX() * SimulationData.NORMALISER);
                	int y2 = (int) (mur.getExtremite2().getY() * SimulationData.NORMALISER);
                    g.drawLine(x1, y1, x2, y2);
                }
            }

            // Dessiner les agents
            for (Agent agent : SimulationData.agents) {
            	if (agent != null) {
                    int x = (int) (agent.getPosition().getX() * SimulationData.NORMALISER);
                    int y = (int) (agent.getPosition().getY() * SimulationData.NORMALISER);
                    int radius = (int) (agent.getRayon() * SimulationData.NORMALISER);
                    Color color = agent.getCouleur();
                    g.setColor(color);
                    g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
                }
            }
            
            // Afficher la sortie
            if (SimulationData.sortie != null) {
            	g.setColor(Color.BLACK);
            	int x = (int) (SimulationData.sortie.getX() * SimulationData.NORMALISER);
            	int y = (int) (SimulationData.sortie.getY() * SimulationData.NORMALISER);
            	g.drawLine(x - 10, y, x + 10, y);
                g.drawLine(x, y - 10, x, y + 10);
                g.drawString("Sortie: " + SimulationData.sortie, 10, getHeight() - 10);
            }
        }

    }

}
