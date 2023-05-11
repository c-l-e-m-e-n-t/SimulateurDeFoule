import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CercleMouvant extends JFrame {
    private int x;
    private int y;
    private int xArrivee;
    private int yArrivee;
    private final int rayon = 10;
    private Timer timer;

    public CercleMouvant() {
        x = 50; // Point A (coordonnée x)
        y = 50; // Point A (coordonnée y)
        xArrivee = 300; // Point B (coordonnée x)
        yArrivee = 200; // Point B (coordonnée y)

        // Configuration de la fenêtre
        setTitle("Cercle Mouvant");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Calcul des distances horizontale et verticale entre les points A et B
        int deltaX = xArrivee - x;
        int deltaY = yArrivee - y;

        // Calcul du pas horizontal et vertical pour le déplacement du cercle
        double pasX = (double) deltaX / 100.0; // 100 étapes pour la trajectoire
        double pasY = (double) deltaY / 100.0; // 100 étapes pour la trajectoire

        // Configuration du Timer pour déplacer le cercle
        timer = new Timer(10, new ActionListener() {
            int etape = 0; // Compteur d'étapes

            @Override
            public void actionPerformed(ActionEvent e) {
                // Mise à jour des coordonnées du cercle
                x += pasX;
                y += pasY;
                etape++;

                if (etape >= 100) {
                    x = xArrivee;
                    y = yArrivee;
                    timer.stop(); // Arrêt du Timer une fois que le point B est atteint
                }
                repaint(); // Redessine la fenêtre
            }
        });
        timer.start(); // Démarrage du Timer pour commencer l'animation
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillOval((int) x, (int) y, rayon * 2, rayon * 2); // Dessine le cercle
    }

    public static void main(String[] args) {
        CercleMouvant cercleMouvant = new CercleMouvant();
        cercleMouvant.setVisible(true);
    }
}
