package affichage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import modele.*;
import modele.Point;
import outils.RangeSlider;

public class MenuPlacementManuel {
    private double oldViteseMin = SimulationData.vitesseMin;
    private double oldViteseMax = SimulationData.vitesseMax;
    private double oldMasseMin = SimulationData.masseMin;
    private double oldMasseMax = SimulationData.masseMax;
    private double oldRayonMin = SimulationData.rayonMin;
    private double oldRayonMax = SimulationData.rayonMax;
    private Color oldColor = SimulationData.couleur;

    private JPanel panel;

    public MenuPlacementManuel(JFrame frame, JPanel drawingPanel, JPanel menuAgent){
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1));

        // Slider pour la masse
        JSlider masseSlider = new JSlider(JSlider.HORIZONTAL, 40, 120, 80);
        masseSlider.setMajorTickSpacing(20);
        masseSlider.setMinorTickSpacing(5);
        masseSlider.setPaintTicks(true);
        masseSlider.setPaintLabels(true);
        masseSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SimulationData.masseMin = masseSlider.getValue();
                SimulationData.masseMax = masseSlider.getValue();
            }
        });
        panel.add(new JLabel("Masse:"));
        panel.add(masseSlider);

        // Slider pour le rayon
        JSlider rayonSlider = new JSlider(JSlider.HORIZONTAL, 35, 45, 40);
        rayonSlider.setMajorTickSpacing(5);
        rayonSlider.setMinorTickSpacing(1);
        rayonSlider.setPaintTicks(true);
        rayonSlider.setPaintLabels(true);
        rayonSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SimulationData.rayonMin = (float) rayonSlider.getValue()/100;
                SimulationData.rayonMax = (float) rayonSlider.getValue()/100;
            }
        });
        panel.add(new JLabel("Rayon:"));
        panel.add(rayonSlider);

        // Slider pour la vitesse
        JSlider vitesseSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        vitesseSlider.setMajorTickSpacing(2);
        vitesseSlider.setMinorTickSpacing(1);
        vitesseSlider.setPaintTicks(true);
        vitesseSlider.setPaintLabels(true);
        vitesseSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SimulationData.vitesseMin = vitesseSlider.getValue();
                SimulationData.vitesseMax = vitesseSlider.getValue();
            }
        });
        panel.add(new JLabel("Vitesse:"));
        panel.add(vitesseSlider);


       
        //faire un slider par couleur
        GridLayout gridRGB = new GridLayout(4, 1);
        JSlider rSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        JSlider gSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        JSlider bSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        JPanel colorPanel = new JPanel();
        
        //ajouter les slider a la grid
        JPanel frameRGB = new JPanel();
        panel.add(frameRGB);
        frameRGB.add(new JLabel("Couleur (R,G,B):"));
        frameRGB.setLayout(gridRGB);
        frameRGB.add(rSlider);
        frameRGB.add(gSlider);
        frameRGB.add(bSlider);
        rSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SimulationData.couleur = new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue());
                colorPanel.setBackground(SimulationData.couleur);
            }
        });
        gSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SimulationData.couleur = new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue());
                colorPanel.setBackground(SimulationData.couleur);
            }
        });
        bSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SimulationData.couleur = new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue());
                colorPanel.setBackground(SimulationData.couleur);
            }
        });

        //afficher une case avec un bg coloré
        colorPanel.setBackground(Color.BLACK);
        panel.add(colorPanel);

        // Bouton Retour
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            SimulationData.vitesseMin = oldViteseMin;
            SimulationData.vitesseMax = oldViteseMax;
            SimulationData.masseMin = oldMasseMin;
            SimulationData.masseMax = oldMasseMax;
            SimulationData.rayonMin = oldRayonMin;
            SimulationData.rayonMax = oldRayonMax;
            SimulationData.couleur = oldColor;
            frame.getContentPane().remove(panel);
            frame.getContentPane().add(menuAgent, BorderLayout.EAST);
            frame.revalidate();
            frame.repaint();
        });
        panel.add(backButton);

        frame.getContentPane().add(panel, BorderLayout.EAST);
        frame.getContentPane().add(drawingPanel, BorderLayout.WEST);
        frame.revalidate();

        // Update le panel
        frame.repaint();
        
        drawingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SimulationData.N += 1;
                Point position = new Point(e.getX(), e.getY());
                Point sortie = SimulationData.sortie;
                double vitesse = SimulationData.vitesseMin;
                double rayon = SimulationData.rayonMin;
                double masse = SimulationData.masseMin;
                double TAU = 0.5;
                Color couleur = SimulationData.couleur;
                SimulationData.updateAgents((double) e.getX(), (double) e.getY());

                drawingPanel.update(drawingPanel.getGraphics());
            }
        });
    }
}