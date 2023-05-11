package affichage;

import javax.swing.*;
import java.awt.*;

public class MenuObstacles {

    private JFrame frame;
    private JPanel panel;
    private JPanel drawingPanel;

    public MenuObstacles(JFrame frame, JPanel drawingPanel) {
        this.frame = frame;
        this.drawingPanel = drawingPanel;

        // Create the panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Add the buttons
        JButton addAgentButton = new JButton("Ajouter un agent");
        panel.add(addAgentButton);

        JButton removeAgentButton = new JButton("Retirer un agent");
        panel.add(removeAgentButton);

        JButton changeAgentButton = new JButton("Changer un agent");
        panel.add(changeAgentButton);

        JButton addExitButton = new JButton("Ajouter une sortie");
        panel.add(addExitButton);

        JButton removeExitButton = new JButton("Retirer une sortie");
        panel.add(removeExitButton);

        JButton exitButton = new JButton("Retour");
        panel.add(exitButton);

        // Add the panel to the frame
        frame.getContentPane().add(panel, BorderLayout.EAST);
        frame.getContentPane().add(drawingPanel, BorderLayout.WEST);
        frame.revalidate();

        
        // Add action listeners to the buttons
        addAgentButton.addActionListener(e -> {
            // Code to add an agent
        });

        removeAgentButton.addActionListener(e -> {
            // Code to remove an agent
        });

        changeAgentButton.addActionListener(e -> {
            // Code to change an agent
        });

        addExitButton.addActionListener(e -> {
            // Code to add an exit
        });

        removeExitButton.addActionListener(e -> {
            // Code to remove an exit
        });

        exitButton.addActionListener(e -> {
            // Remove the panel from the frame and go back to the main menu
            frame.getContentPane().remove(panel);
            Menu menu = new Menu();
        });

        // Refresh the frame to display the new panel
        frame.revalidate();
        frame.repaint();
    }
}
