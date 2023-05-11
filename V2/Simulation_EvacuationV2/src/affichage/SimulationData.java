package affichage;

import java.util.Random;

import modele.*;

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
            double x = rayon + (600 - 2 * rayon) * random.nextDouble();
            double y = rayon + (600 - 2 * rayon) * random.nextDouble();
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

}
