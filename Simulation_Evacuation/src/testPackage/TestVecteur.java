package testPackage;

import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

import modele.Vecteur;
import modele.Point;
import modele.Segment;

public class TestVecteur {
    public final static double EPSILON = 0.001;
    double x;
    double y;
    Vecteur vecteur1;
    Vecteur vecteur2;
    Point p1;
    Point p2;

    
    public void setUp() {
        x = 3;
        y = 1;
        vecteur1 = new Vecteur(x, y);
        vecteur2 = new Vecteur(1, 2);
        p1 = new Point(0, 0);
        p2 = new Point(2, 1);
    }

    @Test
    public void testVecteur() {

        assertEquals(x, vecteur1.getX(), EPSILON);
        assertEquals(y, vecteur1.getY(), EPSILON);
    }

    @Test
    public void testVecteurPointPoint() {
        Vecteur vecteur = new Vecteur(p1, p2);
        assertEquals(x, vecteur.getX(), EPSILON);
        assertEquals(y, vecteur.getY(), EPSILON);
    }

    @test
    public void testTranslation() {

        Point point = new Point(1, 2);
        Vecteur v = Vecteur.translater(vecteur1, point);
        assertEquals(x + 1, v.getX());
        assertEquals(y + 2, v.getY());
    }

    @test
    public void testsommee() {
        Vecteur v = Vecteur.somme(vecteur1, vecteur2);
        assertEquals(x + 1, v.getX(), EPSILON);
        assertEquals(y + 2, v.getY(), EPSILON);
    }

    @test
    public void testDifference() {
        Vecteur v = Vecteur.difference(vecteur1, vecteur2);
        assertEquals(x - 1, v.getX(), EPSILON);
        assertEquals(y - 2, v.getY(), EPSILON);
    }

    @test
    public void testProduitScalaire() {
        Vecteur v = Vecteur.produitScalaire(vecteur1, 2);
        assertEquals(x * 1, v.getX(), EPSILON);
        assertEquals(y * 2, v.getY(), EPSILON);
    }

    @Test
    public void testGetNorme() {
        Vecteur vecteur = new Vecteur(x, y);
        assertEquals(Math.sqrt(x * x + y * y), Vecteur.norme(vecteur), EPSILON);
    }

    @test
    public void testvecteurNormal() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 1);
        double d = p1.distancePoint(p2);
        Vecteur v = Vecteur.vecteurNormal(p1, p2);
        assertEquals((p2.getX() - p1.getX()) / d, v.getX(), EPSILON);
        assertEquals((p2.getY() - p1.getY()) / d, v.getY(), EPSILON);
    }

    @test
    public void testvecteurTangetiel() {
        Vecteur vn = Vecteur.vecteurNormal(p1, p2);
        Vecteur vt = Vecteur.vecteurTangentiel(p1, p2);

        assertEquals(-vn.getY(), vt.getX(), EPSILON);
        assertEquals(vn.getX(), vt.getY(), EPSILON);
    }

    @test
    public void testcosinus() {
        assertEquals(0.9262, Vecteur.cosinus(vecteur1, vecteur2), EPSILON);
    }

}
