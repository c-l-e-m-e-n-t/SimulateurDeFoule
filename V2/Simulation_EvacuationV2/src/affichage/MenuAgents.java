package affichage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import outils.RangeSlider;

public class MenuAgents {

    private JFrame frame;
    private JPanel panel;
    private JPanel drawingPanel;

    public MenuAgents(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {
        this.frame = frame;
        this.drawingPanel = drawingPanel;

        // Créer un panel et des boutons pour la configuration de l'agent
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        // Bouton du nombre d'agents
        JSlider agentSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        agentSlider.setMajorTickSpacing(20);
        agentSlider.setMinorTickSpacing(5);
        agentSlider.setPaintTicks(true);
        agentSlider.setPaintLabels(true);
        panel.add(new JLabel("Nombre d'agents:"));
        panel.add(agentSlider);

        // Bouton Masse
        RangeSlider massSlider = new RangeSlider(0, 100);
        massSlider.setMajorTickSpacing(20);
        massSlider.setMinorTickSpacing(5);
        massSlider.setPaintTicks(true);
        massSlider.setPaintLabels(true);
        massSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Code to update the mass of agents
            }
        });
        panel.add(new JLabel("Masse:"));
        panel.add(massSlider);

        // Bouton Rayon
        RangeSlider radiusSlider = new RangeSlider(5, 50);
        radiusSlider.setMajorTickSpacing(10);
        radiusSlider.setMinorTickSpacing(5);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);
        radiusSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Code to update the radius of agents
            }
        });
        panel.add(new JLabel("Rayon:"));
        panel.add(radiusSlider);

        // Bouton Vitesse
        RangeSlider speedSlider = new RangeSlider(2, 8);
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Code to update the speed of agents
            }
        });
        panel.add(new JLabel("Vitesse:"));
        panel.add(speedSlider);

        // Bouton Configurer un agent
        JButton configureAgentButton = new JButton("Configurer un agent");
        configureAgentButton.addActionListener(e -> {
            // Code to configure an agent
        });
        panel.add(configureAgentButton);

        // Bouton placer aleatoirement
        JButton placeRandomlyButton = new JButton("Placer aléatoirement");
        placeRandomlyButton.addActionListener(e -> {
            // Code to place agents randomly
        });
        panel.add(placeRandomlyButton);

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
}
