package outils;

import javax.swing.JFrame;

import affichage.Menu;
import affichage.SimulationData;
import modele.Agent;
import modele.Point;

public class Supprimer {
    public static void suppression(JFrame frame, java.awt.event.MouseEvent e) {
        for (Agent agent : SimulationData.agents) {
            if (agent != null){
                if (Point.distancePoint(agent.getPosition(), new Point(e.getX()/SimulationData.NORMALISER, e.getY()/SimulationData.NORMALISER)) < agent.getRayon()) {
                    Agent[] tempAgent = new Agent[SimulationData.agents.length];
                    for (int i = 0; i < SimulationData.agents.length; i++) {
                        if (SimulationData.agents[i] != agent) {
                            tempAgent[i] = SimulationData.agents[i];
                        }
                    }
                    SimulationData.agents = tempAgent;
                    frame.repaint();
                    break;
                }
            }
        }
    
        for (Point sortie : SimulationData.sortie) {
            if (Point.distancePoint(sortie, new Point(e.getX()/SimulationData.NORMALISER, e.getY()/SimulationData.NORMALISER)) < 10) {
                Point[] tempSortie = new Point[SimulationData.sortie.length];
                for (int i = 0; i < SimulationData.sortie.length; i++) {
                    if (SimulationData.sortie[i] != sortie) {
                        tempSortie[i] = SimulationData.sortie[i];
                    }
                }
                SimulationData.sortie = tempSortie;
                frame.repaint();
            }
        }
    }
}
