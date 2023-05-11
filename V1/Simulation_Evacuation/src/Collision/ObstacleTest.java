package obstacle;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ObstacleleTest extends JFrame implements MouseListener, MouseMotionListener {
    private ArrayList<BureauObstacle> obstacles = new ArrayList<>();
    private ArrayList<ChaiseObstacle> obstacles2 = new ArrayList<>();
    private BureauObstacle currentObstacle;
    private ChaiseObstacle currentObstacle2;
    private int offsetX, offsetY;
    private JButton addBureauButton = new JButton("Ajouter un bureau");
    private JButton addChaiseButton = new JButton("Ajouter une chaise");
    private JPanel drawingPanel = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            for (BureauObstacle obstacle : obstacles) {
                obstacle.paint(g2d);
            }
            for (ChaiseObstacle obstacle : obstacles2) {
                obstacle.paint(g2d);
            }
        }
    };

    public ObstacleTest() {
       
        setTitle("Bureau Obstacle Test");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel.setBackground(Color.WHITE);
        drawingPanel.addMouseListener(this);
        drawingPanel.addMouseMotionListener(this);

        addBureauButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addObstacle();
            }
        });

        addChaiseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addChaise();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBureauButton);
        buttonPanel.add(addChaiseButton);

        add(drawingPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addObstacle() {
        int x = 20;
        int y = 30;
        int width = 40;
        int height = 40;
        BureauObstacle obstacle = new BureauObstacle(x, y, width, height);
        obstacles.add(obstacle);
        drawingPanel.repaint();
    }

    public void addChaise() {
        int x = 10;
        int y = 10;
        int taille = 10;
        ChaiseObstacle obstacle = new ChaiseObstacle(x, y, taille, Color.RED);
        obstacles2.add(obstacle);
        drawingPanel.repaint();
    }

    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        for (BureauObstacle obstacle : obstacles) {
            if (obstacle.getHitbox().contains(point)) {
                currentObstacle = obstacle;
                offsetX = point.x - obstacle.x;
                offsetY = point.y - obstacle.y;
                return;
            }
        }
        for (ChaiseObstacle obstacle : obstacles2) {
            if (obstacle.getHitbox().contains(point)) {
                currentObstacle2 = obstacle;
                offsetX = point.x - obstacle.x;
                offsetY = point.y - obstacle.y;
                return;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (currentObstacle != null) {
            Point point = e.getPoint();
            currentObstacle.x = point.x - offsetX;
            currentObstacle.y = point.y - offsetY;
            drawingPanel.repaint();
        }
        if (currentObstacle2 != null) {
            Point point = e.getPoint();
            currentObstacle2.x = point.x - offsetX;
            currentObstacle2.y = point.y - offsetY;
            drawingPanel.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        currentObstacle = null;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public static void main(String[] args) {

        ObstacleTest test = new ObstacleTest();
        test.setVisible(true);
    }
}
