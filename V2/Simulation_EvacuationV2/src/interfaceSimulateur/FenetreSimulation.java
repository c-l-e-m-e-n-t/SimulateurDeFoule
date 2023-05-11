package interfaceSimulateur;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modele.*;



public class FenetreSimulation extends JFrame {
	JButton boutonLancerSimu = new JButton("Lancer la simulation");
    private int nbPersonnesCourant = 0;
    public static ParamSimulation parametresSimulation = new ParamSimulation();
    private JPanel paramSimulation = new PanneauDroite(this.boutonLancerSimu);
    PanneauSimulation Simulation = new PanneauSimulation();
    private JSlider sliderNbPersonnes = ((PanneauDroite) paramSimulation).getSliderNbPersonnes();

    public FenetreSimulation() {
        super("Simulation d'évacuation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        final JPanel panSimulation = Simulation;
        getContentPane().add(panSimulation, BorderLayout.CENTER);

        getContentPane().add(paramSimulation, BorderLayout.EAST);

        final Point sortie = new Point(100, 100);
        Simulation.ajouterSortie(sortie);

        // Ajouter un ChangeListener au JSlider pour écouter les changements de valeur
        sliderNbPersonnes.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int nbPersonnes = sliderNbPersonnes.getValue();
                while (nbPersonnesCourant < nbPersonnes) {
                    int x = (int) (Math.random() * panSimulation.getWidth());
                    int y = (int) (Math.random() * panSimulation.getHeight());
                    double vitesse = Math.random() * (parametresSimulation.getVitesseMax() - parametresSimulation.getVitesseMin()) + parametresSimulation.getVitesseMin()+0.01;
                    double masse = Math.random() * (parametresSimulation.getMasseMax() - parametresSimulation.getMasseMin()) + parametresSimulation.getMasseMin();
                    double rayon = (Math.random()*(FenetreSimulation.parametresSimulation.getRayonMin()+FenetreSimulation.parametresSimulation.getRayonMax())/2 + FenetreSimulation.parametresSimulation.getRayonMin())/2;
                    Simulation.ajouterPersonne(new Agent(new Point(x, y), sortie);
                }
                while (nbPersonnesCourant > nbPersonnes) {
                    Simulation.supprimerPersonne();
                    nbPersonnesCourant--;
                }
                
            }
        });

        setVisible(true);
    }
    
    public JButton start() {
    	System.out.println("bbbb");
    	this.boutonLancerSimu.addActionListener(e -> Simulation.lancer());
    	return this.boutonLancerSimu;
    }
}
