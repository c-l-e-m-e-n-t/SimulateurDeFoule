import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Agent {
    private int x; // Coordonnée x du centre du cercle noir
    private int y; // Coordonnée y du centre du cercle noir
    private int tailleAgent; // Taille du cercle noir

    public Agent(int x, int y, int tailleAgent) {
        this.x = x;
        this.y = y;
        this.tailleAgent = tailleAgent;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x - tailleAgent / 2, y - tailleAgent / 2, tailleAgent, tailleAgent);
    }

    // Getters et setters pour les attributs x, y et tailleAgent
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTailleAgent() {
        return tailleAgent;
    }

    public void setTailleAgent(int tailleAgent) {
        this.tailleAgent = tailleAgent;
    }
    
    public int getXMilieu() {
    	return getX() + getTailleAgent() / 2;
    }
    
    public int getYMilieu() {
    	return getY() + getTailleAgent() / 2;
    }
    
    public Ellipse2D getCollisionEllipse() {
        return new Ellipse2D.Double(x - tailleAgent / 2, y - tailleAgent / 2, tailleAgent, tailleAgent);
    }
    
    public void paintCollisionEllipse(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        Ellipse2D ellipse = getCollisionEllipse();
        g2d.draw(ellipse);
    }
    
    public double[] getPointsX() {
        double[] pointsX = new double[360];
        for(int i = 0; i < 360; i++) {
            double rad = Math.toRadians(i);
            double x = getX() + (getTailleAgent() / 2.0) * Math.cos(rad);
            pointsX[i] = x;
        }
        return pointsX;
    }

    public double[] getPointsY() {
        double[] pointsY = new double[360];
        for(int i = 0; i < 360; i++) {
            double rad = Math.toRadians(i);
            double y = getY() + (getTailleAgent() / 2.0) * Math.sin(rad);
            pointsY[i] = y;
        }
        return pointsY;
    }

    public boolean tropProche(Shape hitbox) {
    	double[] pointsX = getPointsX();
    	double[] pointsY = getPointsY();
    	for (int i = 0; i < 360; i++) {
            if (hitbox.contains(pointsX[i], pointsY[i])) {
                return true;
            }
        }
        return false;
    }
    
    public int rechercheXExtremPlusProche(Mur mur) {
	    double distance1, distance2;
	    int extremXPlusProche;
	    double dx1 = mur.getX1() - getX();
	    double dy1 = mur.getY1() - getY();
	    double dx2 = mur.getX2() - getX();
	    double dy2 = mur.getY2() - getY();
	    distance1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
	    distance2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
	    if (distance1 < distance2) {
	    	extremXPlusProche = mur.getX1();
	    } else {
	    	extremXPlusProche = mur.getX2();
	    }
    	return extremXPlusProche;
    }
    
    public int rechercheYExtremPlusProche(Mur mur) {
	    double distance1, distance2;
	    int extremYPlusProche;
	    double dx1 = mur.getX1() - getX();
	    double dy1 = mur.getY1() - getY();
	    double dx2 = mur.getX2() - getX();
	    double dy2 = mur.getY2() - getY();
	    distance1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
	    distance2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
	    if (distance1 < distance2) {
	    	extremYPlusProche = mur.getY1();
	    } else {
	    	extremYPlusProche = mur.getY2();
	    }
    	return extremYPlusProche;
    }

}
