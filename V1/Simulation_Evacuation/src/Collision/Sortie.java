import java.awt.*;

public class Sortie {
    private int tailleSortie;
    private int x; // Coordonnée x du cercle rouge
    private int y; // Coordonnée y du cercle rouge

    public Sortie(int x, int y, int taille) {
        this.tailleSortie = taille;
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, tailleSortie, tailleSortie);
    }

    // Getters et setters pour les attributs x et y
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

    public int getTaille() {
        return this.tailleSortie;
    }
}
