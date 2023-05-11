package testPackage;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;

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
        agent = new Agent(position, sortie, v0, rayon, masse, tau);
        vitesse = new Vecteur(0, 0);
        vitesseDesiree = new Vecteur(0.707, 0.707);
        murs = new Segment[] { new Segment(new Point(0, 0), new Point(0, 10)),
                new Segment(new Point(0, 10), new Point(10, 10)),
                new Segment(new Point(10, 10), new Point(10, 0)),
                new Segment(new Point(10, 0), new Point(0, 0)) };
    }

    @Test
    public void testAgent() {
        Agent agent = new Agent(position, sortie, v0, rayon, masse, tau);
        assertEquals("Test 1", position, agent.getPosition(), EPSILON);
        assertEquals("Test 2", sortie, agent.getCible(), EPSILON);
        assertEquals("Test 3", sortie, agent.getSortie(), EPSILON);
        assertEquals("Test 4", v0, agent.getV0(), EPSILON);
        assertEquals("Test 5", rayon, agent.getRayon(), EPSILON);
        assertEquals("Test 6", masse, agent.getMasse(), EPSILON);
        assertEquals("Test 7", tau, agent.getTau(), EPSILON);
        assertEquals("Test 8", vitesse, agent.getVitesse(), EPSILON);
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
        agent.calculerVitesseDesiree();
        Vecteur v = new Vecteur(new Point(0, 0), new Point(0.707, 0.707));
        Vecteur vitesseDesiree = agent.getVitesseDesiree();
        assertEquals(v.getX(), vitesseDesiree.getX(),
                EPSILON);
        assertEquals(v.getY(), vitesseDesiree.getY(),
                EPSILON);

    }

    @Test
    public void testdistanceAgent() {
        Agent agent1 = new Agent(new Point(4, 3), sortie, 2, rayon, masse, tau);
        double distance = Agent.distanceAgent(agent, agent1);
        assertEquals(5, distance, EPSILON);
    }

    @Test
    public void testobstacleProche() {

        Agent agent1 = new Agent(new Point(5, 5), new Vecteur(1, 1));
        Segment direction = new Segment(agent1.getPosition(), agent1.getPosition().translation(new Vecteur(1, 1)));
        Segment obstacleProche = agent1.obstacleProche(murs, direction);
        Point pe1 = obstacleProche.getExtremite1();
        Point pe2 = obstacleProche.getExtremite2();
        Point pem1 = murs[1].getExtremite1();
        Point pem2 = murs[1].getExtremite2();
        assertEquals(pe1.getX(), pem1.getX());
        assertEquals(pe1.getY(), pem1.getY());
        assertEquals(pe2.getX(), pem2.getX());
        assertEquals(pe2.getY(), pem2.getY());

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
