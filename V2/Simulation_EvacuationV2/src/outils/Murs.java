package outils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import affichage.SimulationData;
import modele.*;

public class Murs {
    private Boolean actif;

    /**
     * Constructeur de la classe Murs
     */
    public Murs() {
        this.actif = false;
    }

    /**ajoute un mur a partir d'un clic de souris
     * @param panel panneau ou on veut ajouter le mur
     */
    public void ajoutMur(JPanel panel) {
        panel.addMouseListener(new MouseListener() {

            private Point p1;
            private Point p2;

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override //commence le trait du mur quand la soiris est pressée
            public void mousePressed(MouseEvent e) {
                this.p1 = new Point(e.getX(), e.getY());
            }

            @Override //termine le trait du mur quand la souris est relachée
            public void mouseReleased(MouseEvent e) {
                if(actif){
                    this.p2 = new Point(e.getX(), e.getY());
                    double x1 = p1.getX() / SimulationData.NORMALISER;
                    double y1 = p1.getY() / SimulationData.NORMALISER;
        			double x2 = p2.getX() / SimulationData.NORMALISER;
        			double y2 = p2.getY() / SimulationData.NORMALISER;
                    SimulationData.addMur(new Segment(new Point(x1, y1), new Point(x2, y2)));
                    panel.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    /** active ou desactive la construction de murs
     * @param actif true pour activer
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    /** permet de savoir si le tracé des murs est actif
     * @return actif true si le tracé est actif
     */
    public Boolean getActif() {
        return this.actif;
    }

}
