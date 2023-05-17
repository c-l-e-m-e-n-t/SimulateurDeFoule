package testPackage;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

import modele.Agent;
import modele.Point;
import modele.Vecteur;
import modele.Segment;

public class TestAgent {

    // précision pour les comparaisons réelle
    public final static double EPSILON = 0.001;

    Point position;
    Point sortie;
    double v0;
    double rayon;
    double masse;
    double tau;
    Agent agent;
    Vecteur vitesse;
    Vecteur vitesseDesiree;
    Vecteur directionDesiree;
    Segment[] murs;

    @Before
    public void setUp() {
        position = new Point(0, 0);
        sortie = new Point(10, 10);
        v0 = 1;
        rayon = 1;
        masse = 70;
        tau = 1;
        agent = new Agent(position, sortie, v0, rayon, masse, tau, Color.BLACK);
        vitesse = new Vecteur(0, 0);
        vitesseDesiree = new Vecteur(0.707, 0.707);
        murs = new Segment[] { new Segment(new Point(0, 0), new Point(0, 10)),
                new Segment(new Point(0, 10), new Point(10, 10)),
                new Segment(new Point(10, 10), new Point(10, 0)),
                new Segment(new Point(10, 0), new Point(0, 0)) };
    }


	/** Vérifier si deux points ont mêmes coordonnées.
	  * @param p1 le premier point
	  * @param p2 le deuxième point
	  */
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}


    @Test
    public void testAgent() {
        Agent agent = new Agent(position, sortie, v0, rayon, masse, tau, Color.BLACK);
        memesCoordonnees("Test 1", position, agent.getPosition());
        memesCoordonnees("Test 2", sortie, agent.getCible());
        memesCoordonnees("Test 3", sortie, agent.getSortie());
        assertEquals("Test 4", v0, agent.getVitesseDesiree(), EPSILON);
        assertEquals("Test 5", rayon, agent.getRayon(), EPSILON);
        assertEquals("Test 6", masse, agent.getMasse(), EPSILON);
        assertEquals("Test 7", tau, agent.getTau(), EPSILON);
        
        assertEquals("Test 8 (x)", agent.calculVitesseDesiree().getX(), agent.getVitesse().getX(), EPSILON);
		assertEquals("Test 8 (y)", agent.calculVitesseDesiree().getY(), agent.getVitesse().getY(), EPSILON);
    }

    @Test
    public void testcalculDirectionDesiree() {
        directionDesiree = agent.calculDirectionDesiree();
        Vecteur v = new Vecteur(new Point(0, 0), new Point(0.707, 0.707));
        assertEquals(v.getX(), directionDesiree.getX(), EPSILON);
        assertEquals(v.getY(), directionDesiree.getY(), EPSILON);
    }

    @Test
    public void testcalculerVitesseDesiree() {
        agent.calculVitesseDesiree();
        Vecteur v = new Vecteur(new Point(0, 0), new Point(0.707, 0.707));
        Vecteur vitesseDesiree = agent.getVitesse();
        assertEquals(v.getX(), vitesseDesiree.getX(),
                EPSILON);
        assertEquals(v.getY(), vitesseDesiree.getY(),
                EPSILON);

    }

    @Test
    public void testdistanceAgent() {
        Agent agent1 = new Agent(new Point(4, 3), sortie, 2, rayon, masse, tau, Color.BLACK);
        double distance = Agent.distanceAgents(agent, agent1);
        assertEquals(5, distance, EPSILON);
    }

    @Test
    public void testobstacleProche() {

    	Agent agent1 = new Agent(position, sortie, v0, rayon, masse, tau, Color.BLACK);
        Segment direction = new Segment(agent1.getPosition(), new Point(agent1.getPosition(), new Vecteur(1, 1)));
        Segment obstacleProche = agent1.obstacleProche(murs, direction);
        Point pe1 = obstacleProche.getExtremite1();
        Point pe2 = obstacleProche.getExtremite2();
        Point pem1 = murs[1].getExtremite1();
        Point pem2 = murs[1].getExtremite2();
        assertEquals(pe1.getX(), pem1.getX(), EPSILON);
        assertEquals(pe1.getY(), pem1.getY(), EPSILON);
        assertEquals(pe2.getX(), pem2.getX(), EPSILON);
        assertEquals(pe2.getY(), pem2.getY(), EPSILON);
    }

    @Test
    public void testcalculeCible() {
        Point cible = new Point(5, 5);
        agent.calculCible(murs);

        // Vérifier que la cible a été déviée pour éviter le mur
        Point nouvelleCible = agent.getCible();
        Assert.assertNotEquals(nouvelleCible.getX(), cible.getX());
        Assert.assertNotEquals(nouvelleCible.getY(), cible.getY());
    }
}
