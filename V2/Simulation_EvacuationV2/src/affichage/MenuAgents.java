package affichage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MenuAgents {

    private JFrame frame;
    private JPanel panel;
    private JPanel drawingPanel;

    public MenuAgents(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {
        this.frame = frame;
        this.drawingPanel = drawingPanel;

        // Create panel and buttons for agent configuration
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        // Number of agents button
        JSlider agentSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        agentSlider.setMajorTickSpacing(20);
        agentSlider.setMinorTickSpacing(5);
        agentSlider.setPaintTicks(true);
        agentSlider.setPaintLabels(true);
        panel.add(new JLabel("Nombre d'agents:"));
        panel.add(agentSlider);

        // Mass button
        JSlider massSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
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

        // Radius button
        JSlider radiusSlider = new JSlider(JSlider.HORIZONTAL, 5, 50, 5);
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

        // Speed button
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 2, 8, 2);
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

        // Configure an agent button
        JButton configureAgentButton = new JButton("Configurer un agent");
        configureAgentButton.addActionListener(e -> {
            // Code to configure an agent
        });
        panel.add(configureAgentButton);

        // Place randomly button
        JButton placeRandomlyButton = new JButton("Placer aléatoirement");
        placeRandomlyButton.addActionListener(e -> {
            // Code to place agents randomly
        });
        panel.add(placeRandomlyButton);

        // Back button
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            frame.getContentPane().remove(panel);
            frame.getContentPane().add(menuPanel, BorderLayout.EAST);
            frame.revalidate();
            frame.repaint();
        });
        panel.add(backButton);

        // Set layout and add panel to frame
        frame.getContentPane().add(panel, BorderLayout.EAST);
        frame.getContentPane().add(drawingPanel, BorderLayout.WEST);
        frame.revalidate();

        // Update drawing panel
        frame.repaint();
    }
}
