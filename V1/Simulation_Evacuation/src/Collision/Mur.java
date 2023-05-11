import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Mur {
    private int x1, y1, x2, y2;

    public Mur(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public double getTaille() {
    	return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public Shape getHitbox() {
        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;
        int width = (int) getTaille() / 2;
        int height = width;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(centerX, centerY - height);
        path.lineTo(centerX + width, centerY);
        path.lineTo(centerX, centerY + height);
        path.lineTo(centerX - width, centerY);
        path.closePath();
        double angle = Math.atan2(y2 - y1, x2 - x1);
        AffineTransform transform = AffineTransform.getRotateInstance(angle, centerX, centerY);
        Shape transformedPath = transform.createTransformedShape(path);
        return transformedPath;
    }

    public void paintHitbox(Graphics g) {
        Shape hitbox = getHitbox();
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(hitbox);
    }

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
        g.drawLine(x1, y1, x2, y2);;
	}

}
