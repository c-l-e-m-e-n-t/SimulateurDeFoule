package affichage;

import javax.swing.*;
import design.*;
import java.awt.*;
import modele.*;
import modele.Point;
import outils.*;
import outils.BureauObstacle;

/** Classe du menu des obstacles.*/
public class MenuObstacles {

    private JPanel panel;
    /**
     * @param frame
     * @param drawingPanel
     * @param menuPanel
     */
    public MenuObstacles(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {
    	frame.setTitle("Simulation d'évacuation - Menu Obstacles");
        // Créer un panel et des boutons pour la configuration des obstacles
        panel = new JPanel() ;
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
        ToggleButtonDesignPetit wallButton = new ToggleButtonDesignPetit("Mur");
        ToggleButtonDesignPetit obstacleButton = new ToggleButtonDesignPetit("Bureau");
        ToggleButtonDesignPetit chaiseButton = new ToggleButtonDesignPetit("Chaise");
        ToggleButtonDesignPetit exitButton = new ToggleButtonDesignPetit("Sortie");
        ButtonDesignPetit resetButton = new ButtonDesignPetit("Réinitialiser");

        ButtonGroup obstacleButtonGroup = new ButtonGroup();
        obstacleButtonGroup.add(wallButton);
        obstacleButtonGroup.add(obstacleButton);
        obstacleButtonGroup.add(exitButton);
        obstacleButtonGroup.add(chaiseButton);

        // Ajouter des action listeners aux boutons
        Murs mur = new Murs();
        Sortie sortie = new Sortie();
        BureauObstacle bureau = new BureauObstacle(40,40);
        ChaiseObstacle chaise = new ChaiseObstacle();
        wallButton.addActionListener(e -> {
            obstacleButton.setSelected(false);
            exitButton.setSelected(false);  
            if (wallButton.isSelected()) {
                sortie.setActif(false);
                bureau.setActif(false);
                chaise.setActif(false);
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
            if (obstacleButton.isSelected()) {
                mur.setActif(false);
                sortie.setActif(false);
                chaise.setActif(false);
                bureau.setActif(true);
                bureau.ajouterObstacle(drawingPanel);
            }else {
                bureau.setActif(false);   
             }
            
        });

        chaiseButton.addActionListener(e -> {
            wallButton.setSelected(false);
            exitButton.setSelected(false);
            mur.setActif(false);
            sortie.setActif(false);
            if (chaiseButton.isSelected()) {
                mur.setActif(false);
                sortie.setActif(false);
                bureau.setActif(false);
                chaise.setActif(true);
                chaise.ajouterchaise(drawingPanel);
            }else {
                chaise.setActif(false);   
             }
            
        });

        exitButton.addActionListener(e -> {
            wallButton.setSelected(false);
            obstacleButton.setSelected(false);
            if (exitButton.isSelected()) {
                mur.setActif(false);
                bureau.setActif(false);
                chaise.setActif(false);
                sortie.setActif(true);
                sortie.ajoutSortie(drawingPanel);
            }
            else{
                sortie.setActif(false);
            }
        });
        resetButton.addActionListener(e -> {
            // Code to reset the obstacles
        	SimulationData.agents = new Agent[0];
        	SimulationData.sortie = new Point[0];
        	SimulationData.murs = new Segment[0];
            SimulationData.bureau = new BureauObstacle[0];
            SimulationData.chaise = new ChaiseObstacle[0];
        	
            wallButton.setSelected(false);
            obstacleButton.setSelected(false);
            exitButton.setSelected(false);
            mur.setActif(false);
            
            frame.repaint();
        });

        JPanel toolButtonPanel = new JPanel(new GridLayout(3, 2));
        toolButtonPanel.add(wallButton);
        toolButtonPanel.add(exitButton);
        toolButtonPanel.add(obstacleButton);
        toolButtonPanel.add(resetButton);
        toolButtonPanel.add(chaiseButton);
        panel.add(toolButtonPanel);

        // Bouton Retour
        ButtonDesign backButton = new ButtonDesign("Retour");
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
    
   
        wallButton.setSelected(false);
        exitButton.setSelected(false);
        mur.setActif(false);
        sortie.setActif(false);
        bureau.setActif(false);
        if (chaiseButton.isSelected()) {
            mur.setActif(false);
            sortie.setActif(false);
            bureau.setActif(false);
            chaise.setActif(true);
            chaise.ajouterchaise(drawingPanel);
        }else {
            chaise.setActif(false);
   
        }
    }
}


