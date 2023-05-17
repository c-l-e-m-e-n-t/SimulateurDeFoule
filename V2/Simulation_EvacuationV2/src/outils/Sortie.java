package outils;

import javax.swing.JPanel;

import affichage.SimulationData;
import modele.Point;

public class Sortie {
    boolean actif;
    public Sortie() {
        this.actif = false;
    }

    /** ajoute une sortie a partir d'un clic de souris
     * @param panel panneau ou on veut ajouter la sortie
     */
    public void ajoutSortie(JPanel panel) {
        //ajouter unmouse listener
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SimulationData.addSortie((double) e.getX(), (double) e.getY());
                panel.repaint();
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
