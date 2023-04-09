import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.Point;


public class PanneauSimulation extends JPanel {

    private Point sortie;
    private Timer timer;
    private ArrayList<Agent> agents;

    public PanneauSimulation() {
        agents = new ArrayList<>();
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indAgents = 0;
                ArrayList<Integer> aSupprimer = new ArrayList<>();
                for (Agent p : agents) {
                    Vecteur direction = new Vecteur(0, 0);
                    direction = p.calculDirectionDesiree();
                    p.setPosition(new Pt((int) p.getPosition().getX() + (int) (direction.getX()*p.getVitesseDesiree()), (int) p.getPosition().getY() + (int) (direction.getY()*p.getVitesseDesiree())));
                    p.getPoint().setLocation((int) p.getPosition().getX() + (int) direction.getX()*p.getVitesseDesiree(), (int) p.getPosition().getY() + (int) direction.getY()*p.getVitesseDesiree());
                    if (p.getPoint().distance(sortie) < 10) {
                        p.setCouleur(Color.WHITE);
                    }
                    indAgents++;
                }
                for (int i : aSupprimer) {
                    agents.remove(i);
                }
                repaint();
            }
        });
        JButton boutonDemarrer = new JButton("Démarrer");
        boutonDemarrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
            // a relier a l'autre bouton, j'avais un peu la flemme
        });
        JButton boutonArreter = new JButton("Arrêter");
        boutonArreter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        this.add(boutonDemarrer);
        this.add(boutonArreter);
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
    }

    /** Ajouter un agent a la fenetre
     * @param Agent Agent a ajouter
     */
    public void ajouterPersonne(Agent Agent) {
        Point p = new Point();
        Agent.setCouleur(Color.RED);
        p.setLocation(Agent.getPosition().getX(), Agent.getPosition().getY());
        agents.add(Agent);
        repaint();
    }

    /** Supprimer un agent de la fenetre
     *
     */
    public void supprimerPersonne() {
        agents.remove(agents.size() - 1);
        repaint();
    }

    /** Supprimer un agent de la fenetre
     * @param indice Indice de l'agent a supprimer
     */
    public void supprimerPersonne(int indice) {
        agents.remove(indice);
    }

    /** Ajouter une sortie a la fenetre
     * @param p Point de la sortie
     */
    public void ajouterSortie(Point p) {
        sortie = p;
        p.setLocation(p.getX(), p.getY());
        repaint();
    }

    /** Afficher les composants de la fenetre
     *  @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //affiche les personnes
        for (Agent p : agents) {
            g.setColor(p.getCouleur());
            g.fillOval((int) p.getPosition().getX(), (int) p.getPosition().getY(),(int) p.getRayon(),(int) p.getRayon());
        }

        //affiche la sortie
        g.setColor(Color.BLACK);
        g.fillOval((int) sortie.getX(), (int) sortie.getY(), 10, 10);
    }
}