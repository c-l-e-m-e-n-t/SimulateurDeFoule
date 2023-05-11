import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MurDemo extends JPanel {
    private Mur mur;

    public MurDemo() {
        mur = new Mur(50, 50, 150, 150);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Dessiner le mur en diagonale
        g2d.drawLine(mur.getX1(), mur.getY1(), mur.getX2(), mur.getY2());

        // Dessiner la hitbox ellipsoïdale du mur (à des fins de débogage)
        g2d.draw(mur.getHitbox());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MurDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(new MurDemo());
        frame.setVisible(true);
    }
}
