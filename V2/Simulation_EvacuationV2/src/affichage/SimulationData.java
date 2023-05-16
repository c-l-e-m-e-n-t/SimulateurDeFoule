package affichage;

import java.util.Random;

import modele.*;

/** Données importantes de la simulation.*/
public class SimulationData {
    /** Main data.*/
    public static Agent[] agents;
    public static Segment[] murs;
    public static Point sortie;

    /** Data agents.*/
    public static int N;
    public static double masseMin;
    public static double masseMax;
    public static double rayonMin;
    public static double rayonMax;
    public static double vitesseMin;
    public static double vitesseMax;

    private static final double TAU = 0.5;
    private static final Random random = new Random();

    public static final double NORMALISER = 720 / 20;

    public static void updateAgents() {
        agents = new Agent[N];
        for (int i = 0; i < N; i++) {
            double masse = masseMin + (masseMax - masseMin) * random.nextGaussian();
            double rayon = rayonMin + (rayonMax - rayonMin) * random.nextGaussian();
            double vitesse = vitesseMin + (vitesseMax - vitesseMin) * random.nextGaussian();
            Point position = getRandomPosition(rayon);
            Point sortie = SimulationData.sortie;
            agents[i] = new Agent(position, sortie, vitesse, rayon, masse, TAU);
        }
    }

    private static Point getRandomPosition(double rayon) {
        Point position;
        boolean validPosition;
        do {
            double x = 2*rayon + (18 - 2 * rayon) * random.nextDouble();
            double y = 2*rayon + (18 - 2 * rayon) * random.nextDouble();
            position = new Point((int) x, (int) y);
            validPosition = true;
            for (Agent agent : agents) {
                if (agent != null && Point.distancePoint(position, agent.getPosition()) <= agent.getRayon() + rayon) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);
        return position;
    }

    public static void addMur(Segment mur) {
        //ajouter un mur a la liste des murs
        Segment[] mursTemp = new Segment[murs.length + 1];
        for (int i = 0; i < murs.length; i++) {
            mursTemp[i] = murs[i];
        }
        mursTemp[murs.length] = mur;
        murs = mursTemp;
    }
}
