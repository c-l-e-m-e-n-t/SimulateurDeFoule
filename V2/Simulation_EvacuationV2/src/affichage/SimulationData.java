package affichage;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.*;
import outils.BureauObstacle;
import outils.ChaiseObstacle;

import java.awt.Color;

/** Données importantes de la simulation.*/
public class SimulationData {
    /** Main data.*/
    public static Agent[] agents;
    public static Segment[] murs;
    public static Point[] sortie;
    public static BureauObstacle[] bureau;
    public static ChaiseObstacle[] chaise;

    /** Data agents.*/
    public static int N;
    public static double masseMin;
    public static double masseMax;
    public static double rayonMin;
    public static double rayonMax;
    public static double vitesseMin;
    public static double vitesseMax;

    public static Color couleur = Color.RED;

    public static  int HAUTEUR = 720;
    public static  int LARGEUR = 1280;

    private static final double TAU = 0.5;
    private static final Random random = new Random();

    public static final double NORMALISER = 720 / 20;

    public static void updateAgents() {
        Agent[] temp = new Agent[N];
        JFrame frame = new JFrame();
        for (int i = 0; i < N; i++) {
            double masse = masseMin + (masseMax - masseMin) * random.nextGaussian();
            double rayon = rayonMin + (rayonMax - rayonMin) * random.nextGaussian();
            double vitesse = vitesseMin + (vitesseMax - vitesseMin) * random.nextGaussian();
            Point position = getRandomPosition(rayon);
            if (sortie.length == 0) {
                JOptionPane.showMessageDialog(frame, "Veuillez placer une sortie.");
            } else{
                Point sortieProche = sortie[0];
                for (Point sortie : SimulationData.sortie) {
                    if (Point.distancePoint(position, sortie) <= Point.distancePoint(position, sortieProche)) {
                        sortieProche = sortie;
                    }
                }
                temp[i] = new Agent(position, sortieProche, vitesse, rayon, masse, TAU, couleur);
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

    /** Retourne une position aléatoire qui ne chevauche pas un autre agent
     * @param rayon rayon de l'agent
     * @return position une position aléatoire aléatoire
     */
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

    /** Ajouter un agent à la liste des agents
     * @param agent l'agent à ajouter
    */
    public static void addAgent(Agent agent){
        Agent[] temp = new Agent[agents.length + 1];
        for (int i = 0; i < agents.length; i++) {
            temp[i] = agents[i];
        }
        temp[agents.length] = agent;
        agents = temp;
    }

    /** Ajouter un mur à la liste des murs
     * @param mur le mur à ajouter
     */
    public static void addMur(Segment mur) {
        //ajouter un mur a la liste des murs
        Segment[] mursTemp = new Segment[murs.length + 1];
        for (int i = 0; i < murs.length; i++) {
            mursTemp[i] = murs[i];
        }
        mursTemp[murs.length] = mur;
        murs = mursTemp;
    }

    /** mettre a jour la liste des agents
     * @param x abscisse de l'agent
     * @param y ordonnée de l'agent
     */
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

    /** Ajouter une sortie à la liste des sorties
     * @param x abscisse de la sortie
     * @param y ordonnée de la sortie
     */
    public static void addSortie(Double x, Double y) {
        Point tempSortie[] = new Point[SimulationData.sortie.length+1];
        for (int i = 0; i < sortie.length; i++){
            tempSortie[i] = SimulationData.sortie[i];
        }
        tempSortie[sortie.length] = new Point(x,y);
        SimulationData.sortie = tempSortie;
    }

    /**ajoueter un bureau
     * @param x abscisse de l'obstacle
     * @param y ordonnée de l'obstacle
     */
    public static void addbureau(double x, double y) {
        BureauObstacle[] tempObstacle;
        if (bureau == null){
            tempObstacle = new BureauObstacle[1];
            tempObstacle[0] = new BureauObstacle(x,y);
        } else {
            tempObstacle = new BureauObstacle[SimulationData.bureau.length+1];
            for (int i = 0; i < bureau.length; i++){
                tempObstacle[i] = SimulationData.bureau[i];
            }
            tempObstacle[bureau.length] = new BureauObstacle(x,y);
        }
        SimulationData.bureau = tempObstacle;
        System.out.println(SimulationData.bureau.length);
    }

    /**ajoueter un chaise
     * @param x abscisse de l'obstacle
     * @param y ordonnée de l'obstacle
     */
    public static void addchaise(double x, double y) {
        ChaiseObstacle[] tempObstacle;
        if (chaise == null){
            tempObstacle = new ChaiseObstacle[1];
            tempObstacle[0] = new ChaiseObstacle(x,y);
        } else {
            tempObstacle = new ChaiseObstacle[SimulationData.chaise.length+1];
            for (int i = 0; i < chaise.length; i++){
                tempObstacle[i] = SimulationData.chaise[i];
            }
            tempObstacle[chaise.length] = new ChaiseObstacle(x,y);
        }
        SimulationData.chaise = tempObstacle;
    }
}
