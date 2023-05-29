package outils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


import affichage.SimulationData;

public class BureauObstacle {
    private int largeur;
    private int longueur;
    private Color color ;
    public double x;
    public double y;
    boolean actif;

    
   
 
    public BureauObstacle(double x, double y) {
        this.x = x;
        this.y = y;
        this.largeur = 40;
        this.longueur = 40;
        this.actif = true;
        
    }
    public BureauObstacle(int largeur, int longueur) {
        this.x = 100;
        this.y = 100;
        this.largeur = 40;
        this.longueur = 40;
        this.color = Color.black;
        this.actif = true;
       
    }

    public Shape getHitbox() {
        double centerX = x;
        double centerY = y;
        int width = largeur / 2;
        int height = longueur / 2;
        Rectangle2D.Double rect = new Rectangle2D.Double(centerX - width, centerY - height, largeur, longueur);
        Double angle = Math.atan(x / y);
        AffineTransform transform = AffineTransform.getRotateInstance(angle, centerX, centerY);
        Shape transformedRect = transform.createTransformedShape(rect);
        return transformedRect;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape hitbox = getHitbox();
        g2d.setColor(color);
        g2d.fill(hitbox);
    }

    public void ajouterObstacle(JPanel panel) {
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (actif){
                    SimulationData.addbureau((double) e.getX(), (double) e.getY());
                    panel.repaint();
                }
            }
        });
        
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    boolean getActif() {
        return this.actif;
    }

    public int getX(){
        return (int) this.x;
    }

    public int getY(){
        return (int) this.y;
    }
    
    }



    

   


