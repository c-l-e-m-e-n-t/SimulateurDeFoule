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

    public Murs() {
        this.actif = false;
    }

    public void ajoutMur(JPanel panel) {
        //ajouter unmouse listener
        panel.addMouseListener(new MouseListener() {

            private Point p1;
            private Point p2;

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                this.p1 = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(actif){
                    this.p2 = new Point(e.getX(), e.getY());
                    SimulationData.addMur(new Segment (this.p1, this.p2));
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

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
    public Boolean getActif() {
        return this.actif;
    }

}
