package affichage;

import java.util.Random;

import org.bouncycastle.asn1.isismtt.x509.AdditionalInformationSyntax;

import modele.*;

import java.awt.Color;

/** Données importantes de la simulation.*/
public class SimulationData {
    /** Main data.*/
    public static Agent[] agents;
    public static Segment[] murs;
    public static Point[] sortie;

    /** Data agents.*/
    public static int N;
    public static double masseMin;
    public static double masseMax;
    public static double rayonMin;
    public static double rayonMax;
    public static double vitesseMin;
    public static double vitesseMax;
    public static Color couleur;
    public static double id;

    private static final double TAU = 0.5;
    private static final Random random = new Random();

    public static final double NORMALISER = 720 / 20;

    public static void updateAgents() {
        Agent[] temp = new Agent[N];
        for (int i = 0; i < min(agents.length,N); i++) {
            temp[i] = agents[i];
        }
        if (N > agents.length) {
            for (int i = agents.length; i < N; i++) {
                double masse = masseMin + (masseMax - masseMin) * random.nextGaussian();
                double rayon = rayonMin + (rayonMax - rayonMin) * random.nextGaussian();
                double vitesse = vitesseMin + (vitesseMax - vitesseMin) * random.nextGaussian();
                Point position = getRandomPosition(rayon);
                Point sortieProche = sortie[0];
                for (Point sortie : SimulationData.sortie) {
                    if (Point.distancePoint(position, sortie) <= Point.distancePoint(position, sortieProche)) {
                        sortieProche = sortie;
                    }
                }
                temp[N-1] = new Agent(position, sortieProche, vitesse, rayon, masse, TAU, couleur);
            }
        }
        agents = temp;
    }

    private static int min(int n1, int n2) {
        if (n1 > n2) {
            return n2;
        }
        return n1;
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

    public static void updateAgents(Double x, Double y) {
        Agent[] temp = new Agent[N];
        Point position;
        for (int i = 0; i < min(agents.length,N); i++) {
            temp[i] = agents[i];
        }
        for (int i = agents.length; i < N; i++) {
            double masse = masseMin + (masseMax - masseMin) * random.nextInt();
            double rayon = rayonMin + (rayonMax - rayonMin) * random.nextInt();
            double vitesse = vitesseMin + (vitesseMax - vitesseMin) * random.nextInt();
            position = new Point(x / NORMALISER, y / NORMALISER);
            Point sortieProche = sortie[0];
            for (Point sortie : SimulationData.sortie) {
                if (Point.distancePoint(position, sortie) <= Point.distancePoint(position, sortieProche)) {
                    sortieProche = sortie;
                }
            }
            temp[N-1] = new Agent(position, sortieProche, vitesse, rayon, masse, TAU, couleur);
        }
        agents = temp;
    }

    public static void addSortie(Double x, Double y) {
        Point[] temp = new Point[sortie.length + 1];
        for (int i = 0; i < sortie.length; i++) {
            temp[i] = sortie[i];
        }
        temp[sortie.length] = new Point(x / NORMALISER, y / NORMALISER);
        sortie = temp;
    }
}
