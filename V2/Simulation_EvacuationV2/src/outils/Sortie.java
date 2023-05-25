package outils;

import javax.swing.JPanel;
import affichage.SimulationData;

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
	                SimulationData.addSortie((double) e.getX(), (double) e.getY());
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
