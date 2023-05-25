package affichage;

import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;

import modele.*;
import modele.Point;
import outils.ButtonDesign;
import outils.Sauvegarde;
import outils.Supprimer;

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
        ButtonDesign launchButton = new ButtonDesign("Lancer la simulation");
        panel.add(launchButton);
        panel.add(new JLabel(""));

        ButtonDesign configRoomButton = new ButtonDesign("Configuration de la piece");
        panel.add(configRoomButton);
        ButtonDesign configAgentButton = new ButtonDesign("Configuration des agents");
        panel.add(configAgentButton);
        panel.add(new JLabel(""));

        JToggleButton supprimer = new JToggleButton("Supprimer (ON / OFF)");
        panel.add(supprimer);
        JToggleButton externalPhenomenaButton = new JToggleButton("Phénomènes externes (ON / OFF)");
        panel.add(externalPhenomenaButton);
        JToggleButton reportsButton = new JToggleButton("Rapports (ON / OFF)");
        panel.add(reportsButton);

        ButtonDesign saveButton = new ButtonDesign("Sauvegarder");
        panel.add(saveButton);

        ButtonDesign loadButton = new ButtonDesign("Charger");
        panel.add(loadButton);

        panel.add(new JLabel(""));

        ButtonDesign exitButton = new ButtonDesign("Quitter");
        panel.add(exitButton);

        // Définir la disposition du panneau et l'ajouter au frame
        panel.setLayout(new GridLayout(12, 1));
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
                SimulationData.HAUTEUR = drawingPanel.getHeight();
                SimulationData.LARGEUR = drawingPanel.getWidth();
        		// Pause between iterations
                try {
                    Thread.sleep(500);
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
        supprimer.addActionListener(e -> {
            
        });
        externalPhenomenaButton.addActionListener(e -> {
            // Code to toggle external phenomena
        });
        reportsButton.addActionListener(e -> {
            // Code to toggle reports
        });
        saveButton.addActionListener(e -> {
            Sauvegarde.sauvegarder();
        });
        loadButton.addActionListener(e -> {
            Sauvegarde.charger(drawingPanel);
        });
        exitButton.addActionListener(e -> System.exit(0));

        drawingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (supprimer.isSelected()) {
                    Supprimer.suppression(frame, e);
                }
            }
        });
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
                	int x2 = (int) (mur.getExtremite2().getX() * SimulationData.NORMALISER);
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
            for (Point sortie : SimulationData.sortie) {
            	if (sortie != null) {
	        		int x = (int) (sortie.getX());
	                int y = (int) (sortie.getY());
	                g.setColor(Color.black);
	                g.drawLine(x - 10, y, x + 10, y);
	                g.drawLine(x, y - 10, x, y + 10);
            	}
            }
        }
    }

}
