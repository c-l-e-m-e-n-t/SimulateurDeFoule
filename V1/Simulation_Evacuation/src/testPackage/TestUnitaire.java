package testPackage;

import org.junit.*;

import modele.Point;
import modele.Segment;

import static org.junit.Assert.*;

/**
 * Classe de test unitaires.
 * @author	Félix Foucher de Brandois
 */
public class TestUnitaire {

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	Segment segment1;
	Point point1, point2, point3;
	Point extremite1;
	Point extremite2;

	Point a1, a2, b1, b2, c1, c2;
	Segment s1, s2, s3;

	@Before public void setUp() {
		point1 = new Point(3, 1);
		point2 = new Point(6, 1);
		point3 = new Point(8, 1);
		extremite1 = new Point(5, 5);
		extremite2 = new Point(7, 5);
		segment1 = new Segment(extremite1, extremite2);

		a1 = new Point(0, 4);
		a2 = new Point(3, 5);
		b1 = new Point(3, 0);
		b2 = new Point(4, 2);
		c1 = new Point(1, 4);
		c2 = new Point(4, 5);
		s1 = new Segment(a1, a2);
		s2 = new Segment(b1, b2);
		s3 = new Segment(c1, c2);

	}

	/** Vérifier si deux points ont mêmes coordonnées.
	  * @param p1 le premier point
	  * @param p2 le deuxième point
	  */
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}


	@Test public void testDistanceSegment1() {
		memesCoordonnees("Test 1", new Point(5, 5), Segment.distanceAvecSegment(point1, segment1));
	}

	@Test public void testDistanceSegment2() {
		memesCoordonnees("Test 2", new Point(6, 5), Segment.distanceAvecSegment(point2, segment1));
	}

	@Test public void testDistanceSegment3() {
		memesCoordonnees("Test 3", new Point(7, 5), Segment.distanceAvecSegment(point3, segment1));
	}

	@Test public void testIntersectionSegment1() {
		memesCoordonnees("Test 4", new Point(6, 6), Segment.intersectionDroites(s1, s2));
	}

	@Test public void testIntersectionSegment2() {
		assertEquals("Test 5", null, Segment.intersectionDroites(s1, s3));
	}

}
