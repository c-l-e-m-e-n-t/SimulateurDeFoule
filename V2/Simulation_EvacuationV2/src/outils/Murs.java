package outils;

import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonListener;

import affichage.SimulationData;
import modele.*;

public class Murs {
    private Segment segment;
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
                    SimulationData.addMur(new Segment (this.p1, this.p2));
                    panel.repaint();
                    new Segment (this.p1, this.p2);
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

    /** change la valeur du segment
     * @param segment
     */
    public void setSegment(Segment segment) {
        this.segment = segment;
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
