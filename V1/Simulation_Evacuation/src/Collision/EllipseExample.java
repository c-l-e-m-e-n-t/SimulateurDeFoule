import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class EllipseExample extends JFrame {

    public EllipseExample() {
        setTitle("Ellipse Example");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Dessiner une ellipse inclinée de 30 degrés vers la droite avec Ellipse2D et une transformation graphique
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(30), 200, 200); // Incliner l'ellipse de 30 degrés autour du point (200, 200)
        Ellipse2D ellipse = new Ellipse2D.Double(150, 100, 150, 100);
        Shape transformedEllipse = transform.createTransformedShape(ellipse);
        g2d.setColor(Color.BLUE);
        g2d.draw(transformedEllipse); // Utiliser la méthode draw() pour dessiner uniquement le contour de l'ellipse inclinée
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EllipseExample());
    }
}
