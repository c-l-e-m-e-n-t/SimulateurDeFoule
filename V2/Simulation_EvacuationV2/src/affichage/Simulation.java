package affichage;

import javax.swing.JPanel;

import modele.*;


public class Simulation {
    private Agent[] agents;
    private Segment[] murs;
    private Point sortie;
    private JPanel drawingPanel;

    public Simulation(Agent[] agents, Segment[] murs, Point sortie, JPanel drawingPanel) {
        this.agents = agents;
        this.murs = murs;
        this.sortie = sortie;
        this.drawingPanel = drawingPanel;
    }

    public void start() {
        // Temps entre chaque itérations (in ms)
        long sleep = 1000;

        // Boucle principale
        while (!agentsSortis()) {

            // Mettre à jour la position des agents
            Deplacement.euler(agents, murs);
            drawingPanel.repaint();

            // Pause entre les itérations
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean agentsSortis() {
        // regarde si tous les agents ont atteint la sortie
        for (Agent agent : agents) {
            if (!agent.getEstSorti()) {
                return false;
            }
        }
        return true;
    }
}
