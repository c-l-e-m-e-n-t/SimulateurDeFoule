import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CercleAnimation extends JPanel {
    private Sortie sortie; // Sortie représentée par un cercle rouge
    private Agent agent; // Agent représenté par un cercle noir
    private Mur mur; // Création d'un mur diagonal

    public CercleAnimation() {
        sortie = new Sortie(450, 600, 60); // Crée une sortie
        agent = new Agent(0, 0, 26); // Crée un agent
        mur = new Mur(250, 500, 500, 100); // Crée un mur
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        mur.paintComponent(g);
        // mur.paintHitbox(g);
        sortie.paintComponent(g);
        agent.paintComponent(g);
        agent.paintCollisionEllipse(g);
    }

    public void animate() {
        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	boolean contournement = false;
                int targetX = sortie.getX() + sortie.getTaille() / 2; // Coordonnée x du centre du cercle rouge
                int targetY = sortie.getY() + sortie.getTaille() / 2; // Coordonnée y du centre du cercle rouge

                int deltaX = targetX - agent.getX(); // Calcul du déplacement en x
                int deltaY = targetY - agent.getY(); // Calcul du déplacement en y

                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Calcul de la distance entre les deux points

                // Calcul du déplacement proportionnel à la distance
                double ratio = 0; // Facteur d'échelle pour la distance
                if (distance > 0) {
                    ratio = Math.min(1.0, 5.0 / distance); // On limite le facteur d'échelle à 1 et on utilise un facteur d'échelle maximum de 5 pour éviter des déplacements trop rapides
                }

                int stepX = (int) (deltaX * ratio); // Déplacement en x proportionnel à la distance
                int stepY = (int) (deltaY * ratio); // Déplacement en y proportionnel à la distance

             // Vérifie si la nouvelle position de l'agent est à l'intérieur du mur
                if (agent.tropProche(mur.getHitbox())) {
                    targetX = agent.rechercheXExtremPlusProche(mur) - agent.getTailleAgent();
                    targetY = agent.rechercheYExtremPlusProche(mur) + agent.getTailleAgent();
                    
                    deltaX = targetX - agent.getX(); // Calcul du déplacement en x
                    deltaY = targetY - agent.getY(); // Calcul du déplacement en y

                    distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Calcul de la distance entre les deux points

                    // Calcul du déplacement proportionnel à la distance
                    ratio = 0; // Facteur d'échelle pour la distance
                    if (distance > 0) {
                        ratio = Math.min(1.0, 5.0 / distance); // On limite le facteur d'échelle à 1 et on utilise un facteur d'échelle maximum de 5 pour éviter des déplacements trop rapides
                    }

                    stepX = (int) (deltaX * ratio); // Déplacement en x proportionnel à la distance
                    stepY = (int) (deltaY * ratio); // Déplacement en y proportionnel à la distance
                    
                    contournement = true;
                }


                agent.setX(agent.getX() + stepX); // Mise à jour de la position x du cercle noir
                agent.setY(agent.getY() + stepY); // Mise à jour de la position y du cercle noir

                repaint();

                // Arrête l'animation lorsque le cercle noir atteint le centre du cercle rouge
                if (agent.getX() == targetX && agent.getY() == targetY && !contournement) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cercle Animation");
        CercleAnimation cercleAnimation = new CercleAnimation();
        frame.add(cercleAnimation);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        cercleAnimation.animate();
    }
}