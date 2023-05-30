package outils;

import javax.swing.JPanel;
import affichage.SimulationData;
import modele.*;

public class Sortie {
    boolean actif;
    public Sortie() {
        this.actif = true;
    }

    /** ajoute une sortie a partir d'un clic de souris
     * @param panel panneau ou on veut ajouter la sortie
     */
    public void ajoutSortie(JPanel panel) {
        //ajouter unmouse listener
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	if (actif) {
	                SimulationData.addSortie((double) e.getX()/SimulationData.NORMALISER, (double) e.getY()/SimulationData.NORMALISER);
                    for (int i = 0; i < SimulationData.agents.length; i++){
                        Point sortieProche = SimulationData.sortie[0];
                        for(Point sortie : SimulationData.sortie){
                            if (Point.distancePoint(SimulationData.agents[i].getPosition(), sortie) <= Point.distancePoint(SimulationData.agents[i].getPosition(), sortieProche)){
                                sortieProche = sortie;
                            }
                        }
                        SimulationData.agents[i].setSortie(sortieProche);
                    }
	                panel.repaint();
            	}
            }
        });
    }

    /** active ou desacticve la création de sorties
     * @param actif true pour activer
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
