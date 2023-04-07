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
                for (Agent p : agents) {
                    Vecteur direction = new Vecteur(0, 0);
                    direction = p.calculDirectionDesiree();
                    System.out.println(direction);
                    System.out.println(p.getPosition());
                    System.out.println(new Pt( p.getPosition().getX() +  direction.getX()*10, p.getPosition().getY() +  direction.getY()*10));
                    p.setPosition(new Pt((int) p.getPosition().getX() + (int) (direction.getX()*10), (int) p.getPosition().getY() + (int) (direction.getY()*10)));
                    System.out.println(p.getPosition());
                    p.getPoint().setLocation((int) p.getPosition().getX() + (int) direction.getX()*10, (int) p.getPosition().getY() + (int) direction.getY()*10);
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

    public void ajouterPersonne(Agent Agent) {
        Point p = new Point();
        p.setLocation(Agent.getPosition().getX(), Agent.getPosition().getY());
        agents.add(Agent);
        repaint();
    }

    public void ajouterSortie(Point p) {
        sortie = p;
        p.setLocation(p.getX(), p.getY());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Agent p : agents) {
            g.setColor(Color.RED);
            g.fillOval((int) p.getPosition().getX(), (int) p.getPosition().getY(), 10, 10);
        }
        g.setColor(Color.BLACK);
        g.fillOval((int) sortie.getX(), (int) sortie.getY(), 10, 10);
    }
}