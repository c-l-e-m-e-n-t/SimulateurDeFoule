package affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import modele.*;
import javax.swing.Timer;
import design.ButtonDesign;

/** Classe qui gère la simulation de l'évacuation.*/
public class Simulation {

    private Timer timer;
    private static JLabel nbAgents;
    private static JLabel nbMort;
    private static JLabel tempsSortie;
    private static JLabel AgentsSortis;
    private static JLabel PressionMax;
    private static JLabel TempsMoyen;
    public static int nbMorts = 0;
    public static int nbAgentsSortis = 0;
    public static double tps = 0;
    public static double pMax = 0;
    public static double[] temps = new double[SimulationData.agents.length];
    public static double tpsMoyen = 0;
    public static int nbt = 0;

    public Simulation(JFrame frame, JPanel drawingPanel, JPanel menuPanel) {

        // Create the menu panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1));

        // Bouton Pause
        ButtonDesign pauseButton = new ButtonDesign("Pause");
        pauseButton.addActionListener(e -> {
            timer.stop();
        });
        panel.add(pauseButton);

        // Bouton Play
        ButtonDesign playButton = new ButtonDesign("Play");
        playButton.addActionListener(e -> {
            timer.start();
        });
        panel.add(playButton);

        // Bouton Retour
        ButtonDesign backButton = new ButtonDesign("Retour");
        backButton.addActionListener(e -> {
            timer.stop();
            frame.getContentPane().remove(panel);
            frame.getContentPane().add(menuPanel, BorderLayout.EAST);
            frame.revalidate();
            frame.repaint();
        });

        for (int i=0 ; i<temps.length ; i++) {
            temps[i] = 0;
        }

        //ajout de stats 
        nbAgents = new JLabel("Nombre d'agents : " + SimulationData.N);
        tempsSortie = new JLabel("Temps de sortie : " + tps);
        AgentsSortis = new JLabel("Agents sortis : " + nbAgentsSortis);
        nbMorts = 0;
        nbMort = new JLabel("Nombre de morts : " + 0);
        PressionMax = new JLabel("Pression maximale : " + 0);
        TempsMoyen = new JLabel("Temps de sortie moyen : " + 0);

        panel.add(nbAgents);
        panel.add(AgentsSortis);
        panel.add(tempsSortie);
        panel.add(PressionMax);
        panel.add(nbMort);
        panel.add(TempsMoyen);

        panel.add(backButton);

        // Définir la disposition du panneau et l'ajouter au frame
        frame.getContentPane().add(panel, BorderLayout.EAST);
        frame.getContentPane().add(drawingPanel, BorderLayout.WEST);
        frame.revalidate();

        frame.update(frame.getGraphics()); // Ne pas toucher, ca marche comme ca
    }

    public void run(JFrame frame) {
        // Time between each iteration (in ms)
        int sleep = 30;
        timer = new Timer(sleep, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Update the position of the agents
                Deplacement.euler(SimulationData.agents, SimulationData.murs);
                Simulation.tps += 0.03;
                Simulation.nbMorts = 0;
                Simulation.nbAgentsSortis = 0;
                //System.out.println(SimulationData.agents[0].getPosition());
                for (int i=0 ; i<SimulationData.agents.length ; i++) {
                    if (SimulationData.agents[i].estMort) {
                        nbMorts++;
                    }
                    if (SimulationData.agents[i].estSorti) {
                        nbAgentsSortis++;
                        if (temps[i] <= 0.02) {
                            temps[i] = Simulation.tps;
                            nbt++;
                        }
                    }
                    if (SimulationData.agents[i].getPression() > pMax) {
                        pMax = SimulationData.agents[i].getPression();
                    }
                }
                for (int i=0 ; i<temps.length ; i++) {
                    tpsMoyen += temps[i];
                }
                //System.out.printf("et aussi\n%f avec deux decimales : %.2f\n", d, d);
                if (nbt != 0) tpsMoyen = tpsMoyen / nbt;
                nbAgents.setText("Nombre d'agents : " + SimulationData.N);
                AgentsSortis.setText("Agents sortis : " + nbAgentsSortis);
                tempsSortie.setText(String.format("Temps de sortie : %.2f", Simulation.tps));
                PressionMax.setText(String.format("Pression maximale : %.2f", pMax));
                TempsMoyen.setText(String.format("Temps de sortie moyen : %.2f", tpsMoyen));
                nbMort.setText("Nombre de morts : " + nbMorts);


                // Repaint the drawing panel
                frame.repaint(); // Ne pas toucher, ca marche comme ca (a cause du actionperformed)

                if (agentsSortis()) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private boolean agentsSortis() {
        // Voir si les agents sont sortis
        for (Agent agent : SimulationData.agents) {
            if (!agent.estSorti && !agent.estMort) {
                return false;
            }
        }
        return true;
    }
}
