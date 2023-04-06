import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class PanneauSimulation extends JPanel {

    private ArrayList<Point> personnes;
    private Timer timer;

    public PanneauSimulation() {
        personnes = new ArrayList<>();
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Point p : personnes) {
                    p.setLocation(p.getX() + 1, p.getY() + 1);
                }

                //ici, get la nouvelle position de la souris


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

    public void ajouterPersonne(Point p) {
        personnes.add(p);
        p.setLocation(p.getX(), p.getY());
        System.out.println("aaaaaaaaaaaaa" + personnes.size());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point p : personnes) {
            g.setColor(Color.RED);
            g.fillOval((int) p.getX(), (int) p.getY(), 10, 10);
        }
    }
}