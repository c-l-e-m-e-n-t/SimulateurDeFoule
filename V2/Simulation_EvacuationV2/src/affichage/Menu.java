package affichage;

import javax.swing.*;
import java.awt.*;

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

        JButton configPartButton = new JButton("Configuration de la partie");
        panel.add(configPartButton);
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
            // Code to launch the simulation
        });
        configPartButton.addActionListener(e -> {
            frame.getContentPane().remove(panel);
            MenuObstacles menuObstacles = new MenuObstacles(frame, drawingPanel);
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

    private class DrawingPanel extends JPanel {

        public DrawingPanel() {
            setPreferredSize(new Dimension(720, 720));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = 50;
            g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        }
    }

}
