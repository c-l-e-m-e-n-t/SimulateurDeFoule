package affichage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import design.ButtonDesign;
import java.awt.*;
import outils.*;

/** Classe du menu des agents.*/
public class MenuAgents {

    private JPanel panel;

    public MenuAgents(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {
        frame.setTitle("Simulation d'évacuation - Menu Agents");
        // Créer un panel et des boutons pour la configuration de l'agent
        panel = new JPanel();
        panel.setLayout(new GridLayout(11, 1));

        // Bouton du nombre d'agents
        JSlider agentSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        agentSlider.setMajorTickSpacing(20);
        agentSlider.setMinorTickSpacing(5);
        agentSlider.setPaintTicks(true);
        agentSlider.setPaintLabels(true);
        agentSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SimulationData.N = agentSlider.getValue();
                SimulationData.updateAgents();
                frame.repaint();
            }
        });
        panel.add(new JLabel("Nombre d'agents:"));
        panel.add(agentSlider);

        // Bouton Masse
        RangeSlider massSlider = new RangeSlider(40, 120);
        massSlider.setMajorTickSpacing(20);
        massSlider.setMinorTickSpacing(5);
        massSlider.setPaintTicks(true);
        massSlider.setPaintLabels(true);
        SimulationData.masseMin = massSlider.getValue();
    	SimulationData.masseMax = massSlider.getUpperValue();
    	SimulationData.updateAgents();
        massSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	SimulationData.masseMin = massSlider.getValue();
            	SimulationData.masseMax = massSlider.getUpperValue();
            	SimulationData.updateAgents();
            	frame.repaint();
            }
        });
        panel.add(new JLabel("Masse:"));
        panel.add(massSlider);

        // Bouton Rayon
        RangeSlider radiusSlider = new RangeSlider(35, 45);
        radiusSlider.setMajorTickSpacing(5);
        radiusSlider.setMinorTickSpacing(1);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);
        SimulationData.rayonMin = (float) radiusSlider.getValue() / 100;
    	SimulationData.rayonMax = (float) radiusSlider.getUpperValue() / 100;
    	SimulationData.updateAgents();
        radiusSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	SimulationData.rayonMin = (float) radiusSlider.getValue() / 100;
            	SimulationData.rayonMax = (float) radiusSlider.getUpperValue() / 100;
            	SimulationData.updateAgents();
            	frame.repaint();
            }
        });
        panel.add(new JLabel("Rayon:"));
        panel.add(radiusSlider);

        // Bouton Vitesse
        RangeSlider speedSlider = new RangeSlider(5, 20);
        speedSlider.setMajorTickSpacing(10);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        SimulationData.vitesseMin = speedSlider.getValue()/10;
    	SimulationData.vitesseMax = speedSlider.getUpperValue()/10;
    	SimulationData.updateAgents();
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	SimulationData.vitesseMin = speedSlider.getValue();
            	SimulationData.vitesseMax = speedSlider.getUpperValue();
            	SimulationData.updateAgents();
            	frame.repaint();
            }
        });
        panel.add(new JLabel("Vitesse:"));
        panel.add(speedSlider);

        

        // Bouton Configurer un agent
        ButtonDesign configureAgentButton = new ButtonDesign("Configurer un agent");
        configureAgentButton.addActionListener(e -> {
            // Code to configure an agent
        });
        panel.add(configureAgentButton);

        // Bouton placer manuellement
        ButtonDesign placeManuallyButton = new ButtonDesign("Placer manuellement");
        placeManuallyButton.addActionListener(e -> {
            frame.getContentPane().remove(panel);
            new MenuPlacementManuel(frame, drawingPanel, panel);
        });
        panel.add(placeManuallyButton);

        // Bouton Retour
        ButtonDesign backButton = new ButtonDesign("Retour");
        backButton.addActionListener(e -> {
            frame.getContentPane().remove(panel);
            frame.getContentPane().add(menuPanel, BorderLayout.EAST);
            frame.setTitle("Simulation d'évacuation - Menu");
            frame.revalidate();
            frame.repaint();
        });
        panel.add(backButton);

        // Définir la disposition du panneau et l'ajouter au frame
        frame.getContentPane().add(drawingPanel, BorderLayout.WEST);
        frame.getContentPane().add(panel, BorderLayout.EAST);
        frame.revalidate();

        // Update le panel
        frame.repaint();
    }

}
