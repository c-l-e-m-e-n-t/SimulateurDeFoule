import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class PanneauSimulation extends JPanel {

    private ArrayList<Point> points;
    private Timer timer;

    public PanneauSimulation() {
        points = new ArrayList<>();
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Point p : points) {
                    p.setLocation(p.getX() + 1, p.getY() + 1);
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

    public void ajouterPoint(Point p) {
        points.add(p);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point p : points) {
            g.setColor(Color.RED);
            g.fillOval((int) p.getX(), (int) p.getY(), 10, 10);
        }
    }
}