package outils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

import affichage.SimulationData;

public class ChaiseObstacle {
    private int taille;
    private Color couleur;
    double x, y;
    boolean actif;

  

    public ChaiseObstacle(double x, double y) {
        this.x = x;
        this.y = y;
        this.taille = 20;
        this.couleur = Color.red;
        this.actif = true;
    }
    public ChaiseObstacle() {
        this.x = 100;
        this.y = 100;
        this.taille = 20;
        this.couleur = Color.red;
        this.actif = true;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean getActif() {
        return this.actif;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return this.x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getTaille() {
        return this.taille;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public Shape getHitbox() {
        double centerX = getX();
        double centerY = getY();
        int size = getTaille();
        Path2D.Double path = new Path2D.Double();
        path.moveTo(centerX, centerY - size / 2);
        path.lineTo(centerX + size / 2, centerY + size / 2);
        path.lineTo(centerX - size / 2, centerY + size / 2);
        path.closePath();
        return path;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape hitbox = getHitbox();
        g2d.setColor(Color.RED);
        g2d.draw(hitbox);
        g2d.setColor(getCouleur());
        g2d.fill(hitbox);
    }

    public void ajouterchaise(JPanel panel) {
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (actif){
                    SimulationData.addchaise((double) e.getX(), (double) e.getY());
                    panel.repaint();
                }
            }
        });
        
    }



}
