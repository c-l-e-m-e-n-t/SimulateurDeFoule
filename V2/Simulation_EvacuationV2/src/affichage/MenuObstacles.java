package affichage;

import javax.swing.*;
import java.awt.*;

import modele.*;
import modele.Point;
import outils.*;

/** Classe du menu des obstacles.*/
public class MenuObstacles {

    private JFrame frame;
    private JPanel panel;
    private JPanel drawingPanel;

    public MenuObstacles(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {
    	this.frame = frame;
        frame.setTitle("Simulation d'évacuation - Menu Obstacles");
        this.drawingPanel = drawingPanel;

        // Créer un panel et des boutons pour la configuration des obstacles
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Menu déroulant des types de pièce
        String[] roomTypes = {"Piece 1", "Piece 2", "Piece 3", "Piece 4"};
        JComboBox<String> roomComboBox = new JComboBox<>(roomTypes);
        roomComboBox.addActionListener(e -> {
            String selectedObstacleType = (String) roomComboBox.getSelectedItem();
            Segment mur1, mur2, mur3, mur4, mur5, mur6;
            Segment[] murs;
            switch (selectedObstacleType) {
                case "Piece 1":
                    // Code to select the wall tool
                	mur1 = new Segment(new Point(5, 9), new Point(5, 5));
                	mur2 = new Segment(new Point(5, 5), new Point(15, 5));
                	mur3 = new Segment(new Point(15, 5), new Point(15, 15));
                	mur4 = new Segment(new Point(15, 15), new Point(5, 15));
                	mur5 = new Segment(new Point(5, 15), new Point(5, 11));
                	murs = new Segment[]{mur1, mur2, mur3, mur4, mur5};
                	SimulationData.murs = murs;
                	frame.repaint();
                    break;
                case "Piece 2":
                    // Code to select the wall tool
                	mur1 = new Segment(new Point(5, 9), new Point(5, 5));
                	mur2 = new Segment(new Point(5, 5), new Point(15, 5));
                	mur3 = new Segment(new Point(15, 5), new Point(15, 15));
                	mur4 = new Segment(new Point(15, 15), new Point(5, 15));
                	mur5 = new Segment(new Point(5, 15), new Point(5, 11));
                	mur6 = new Segment(new Point(8, 8), new Point(8, 12));
                	murs = new Segment[]{mur1, mur2, mur3, mur4, mur5, mur6};
                	SimulationData.murs = murs;
                	frame.repaint();
                    break;
                case "Piece 3":
                    // Code to select the exit tool
                    break;
                case "Piece 4":
                    // Code to reset the obstacles
                    break;
            }
        });
        panel.add(new JLabel("Types d'obstacles:"));
        panel.add(roomComboBox);

        // Label pour les outils
        panel.add(new JLabel("Outils:"));

        // Boutons On/Off pour les outils
        JToggleButton wallButton = new JToggleButton("Mur");
        JToggleButton obstacleButton = new JToggleButton("Obstacle");
        JToggleButton exitButton = new JToggleButton("Sortie");
        JButton resetButton = new JButton("Réinitialiser");

        ButtonGroup obstacleButtonGroup = new ButtonGroup();
        obstacleButtonGroup.add(wallButton);
        obstacleButtonGroup.add(obstacleButton);
        obstacleButtonGroup.add(exitButton);

        // Ajouter des action listeners aux boutons
        Murs mur = new Murs();
        Sortie sortie = new Sortie();
        wallButton.addActionListener(e -> {
            obstacleButton.setSelected(false);
            exitButton.setSelected(false);  
            if (wallButton.isSelected()) {
                sortie.setActif(false);
                mur.setActif(true);
                mur.ajoutMur(drawingPanel);
            }
            else{
                mur.setActif(false);
            }
        });
        obstacleButton.addActionListener(e -> {
            wallButton.setSelected(false);
            exitButton.setSelected(false);
            mur.setActif(false);
            sortie.setActif(false);
        });
        exitButton.addActionListener(e -> {
            wallButton.setSelected(false);
            obstacleButton.setSelected(false);
            if (exitButton.isSelected()) {
                mur.setActif(false);
                sortie.setActif(true);
                sortie.ajoutSortie(drawingPanel);
            }
            else{
                sortie.setActif(false);
            }
        });
        resetButton.addActionListener(e -> {
            // Code to reset the obstacles
            wallButton.setSelected(false);
            obstacleButton.setSelected(false);
            exitButton.setSelected(false);
            mur.setActif(false);
        });

        JPanel toolButtonPanel = new JPanel(new GridLayout(2, 2));
        toolButtonPanel.add(wallButton);
        toolButtonPanel.add(obstacleButton);
        toolButtonPanel.add(exitButton);
        toolButtonPanel.add(resetButton);
        panel.add(toolButtonPanel);

        // Bouton Retour
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            mur.setActif(false);
            sortie.setActif(false);
            frame.getContentPane().remove(panel);
            frame.getContentPane().add(menuPanel, BorderLayout.EAST);
            frame.setTitle("Simulation d'évacuation - Menu");
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
