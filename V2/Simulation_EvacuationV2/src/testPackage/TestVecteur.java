package testPackage;

import org.junit.*;
import static org.junit.Assert.*;

import modele.Vecteur;
import modele.Point;
import modele.Segment;


public class TestVecteur {
    public final static double EPSILON = 0.001;

    Vecteur v1, v2, v3;
    Segment s1;

    Point p1;
    Point p2;

    @Before
    public void setUp() {
    	s1 = new Segment(new Point(-2, 4), new Point(3, 5));
        v1 = new Vecteur(3, 4);
        v2 = new Vecteur(new Point(1, 1), new Point(7, 9));
        v3 = new Vecteur(s1);
    }

    @Test
    public void testVecteur() {
        assertEquals(3, v1.getX(), EPSILON);
        assertEquals(4, v1.getY(), EPSILON);
    }

    @Test
    public void testVecteurPointPoint() {
        assertEquals(6, v2.getX(), EPSILON);
        assertEquals(8, v2.getY(), EPSILON);
    }
    
    @Test
    public void testVecteurSegment() {
        assertEquals(5, v3.getX(), EPSILON);
        assertEquals(1, v3.getY(), EPSILON);
    }

    @Test
    public void testTranslater() {
    	// TODO
    }

    @Test
    public void testNorme() {
        assertEquals(5, Vecteur.norme(v1), EPSILON);
    }

    @Test
    public void testSomme() {
        Vecteur v = Vecteur.somme(v1, v2);
        assertEquals(9, v.getX(), EPSILON);
        assertEquals(12, v.getY(), EPSILON);
    }

    @Test
    public void testDifference() {
        Vecteur v = Vecteur.difference(v2, v1);
        assertEquals(3, v.getX(), EPSILON);
        assertEquals(4, v.getY(), EPSILON);
    }

    @Test
    public void testMultiplication() {
    	Vecteur v = Vecteur.multiplication(v1, 2);
        assertEquals(6, v.getX(), EPSILON);
        assertEquals(8, v.getY(), EPSILON);
    }

    @Test
    public void testProduitScalaire() {
        double v = Vecteur.produitScalaire(v1, v3);
        // TODO
    }

    @Test
    public void testvecteurNormal() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 1);
        double d = Point.distancePoint(p1, p2);
        Vecteur v = Vecteur.vecteurNormal(p1, p2);
        assertEquals((p2.getX() - p1.getX()) / d, v.getX(), EPSILON);
        assertEquals((p2.getY() - p1.getY()) / d, v.getY(), EPSILON);
    }

    @Test
    public void testvecteurTangent() {
        Vecteur vn = Vecteur.vecteurNormal(p1, p2);
        Vecteur vt = Vecteur.vecteurTangent(p1, p2);

        assertEquals(-vn.getY(), vt.getX(), EPSILON);
        assertEquals(vn.getX(), vt.getY(), EPSILON);
    }

    @Test
    public void testcosinus() {
        assertEquals(0.9262, Vecteur.cosinus(v1, v2), EPSILON);
    }

}
