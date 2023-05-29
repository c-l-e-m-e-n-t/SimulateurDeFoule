package affichage;

import javax.swing.*;
import design.*;
import java.awt.*;

import modele.*;
import modele.Point;
import outils.*;
import outils.BureauObstacle;

/** Classe qui gère les opérations du menu. */
public class Menu {

    private JFrame frame;
    private JPanel panel;
    public static JPanel drawingPanel;

    public Menu() {
        frame = new JFrame("Simulation d'évacuation - Menu");
        panel = new JPanel();
        drawingPanel = new DrawingPanel();

        JMenuBar menuBar = MenuBar.createMenu(frame);
        frame.setJMenuBar(menuBar);

        // Ajouter des éléments de menu au panel
        ButtonDesign launchButton = new ButtonDesign("Lancer la simulation");
        panel.add(launchButton);
        panel.add(new JLabel(""));

        ButtonDesign configRoomButton = new ButtonDesign("Configuration de la piece");
        panel.add(configRoomButton);
        ButtonDesign configAgentButton = new ButtonDesign("Configuration des agents");
        panel.add(configAgentButton);
        panel.add(new JLabel(""));

        ToggleButtonDesign supprimer = new ToggleButtonDesign("Supprimer (ON / OFF)");
        panel.add(supprimer);
        ToggleButtonDesign externalPhenomenaButton = new ToggleButtonDesign("Phénomènes externes (ON / OFF)");
        panel.add(externalPhenomenaButton);
        ToggleButtonDesign reportsButton = new ToggleButtonDesign("Rapports (ON / OFF)");
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
            supprimer.setSelected(false);
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
                simulation.run(frame);
            } else {
                System.out.println("La simulation n'est pas configuré entièrement");
            }
        });
        configRoomButton.addActionListener(e -> {

            supprimer.setSelected(false);
            frame.getContentPane().remove(panel);
            new MenuObstacles(frame, drawingPanel, panel);
        });
        configAgentButton.addActionListener(e -> {
            supprimer.setSelected(false);
            frame.getContentPane().remove(panel);
            new MenuAgents(frame, drawingPanel, panel);
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
            supprimer.setSelected(false);
            Sauvegarde.sauvegarder();
        });
        loadButton.addActionListener(e -> {
            supprimer.setSelected(false);
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
                    int x = (int) (sortie.getX()* SimulationData.NORMALISER);
                    int y = (int) (sortie.getY()* SimulationData.NORMALISER);
                    g.setColor(Color.black);
                    g.drawLine(x - 10, y, x + 10, y);
                    g.drawLine(x, y - 10, x, y + 10);
                }
            }

            // desiner les obstacles
            Graphics2D g2d = (Graphics2D) g;
            if(SimulationData.bureau != null){
                for (BureauObstacle bureau : SimulationData.bureau) {
                    if (bureau != null) {
                        bureau.paint(g2d);
                    }
                }
            }
            if(SimulationData.chaise != null){
                for (ChaiseObstacle chaise : SimulationData.chaise) {
                    if (chaise != null) {
                        chaise.paint(g2d);
                    }
                }

                g2d.scale(MenuBar.scale, MenuBar.scale);
            }
            panel.repaint();
        }

    }
}
