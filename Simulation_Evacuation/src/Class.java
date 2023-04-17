package interface_CrowdSimulator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simulateur.*;
public class Class extends JPanel implements ContenusDePanneaux  {
    BufferedImage background;
    public int nbPersonnesCourant = 0;

    /*Constructeur qui crée la page du mode classique*/ 
    public Class() {
        super();
        this.elementsdepanneau("crowd.jpg");
        setPreferredSize(new Dimension(2000, 1000));
    }

    public void elementsdepanneau(String nom_image) {
        try {
            this.background  = ImageIO.read(new File(nom_image));
            setLayout(new BorderLayout());

            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setOpaque(false); 
            centerPanel.setPreferredSize(new Dimension(400, 300));
            PanneauSimulation Simulation = new PanneauSimulation();
            JPanel panSimulation = Simulation;
            panSimulation.setPreferredSize(new Dimension(400, 300));

            ParamSimulation parametresSimulation = new ParamSimulation();
            JPanel paramSim = new PanneauDroite();
            JSlider sliderNbPersonnes = ((PanneauDroite) paramSim).getSliderNbPersonnes();
            centerPanel.add(panSimulation); 
            add(centerPanel, BorderLayout.CENTER); 

            add(paramSim, BorderLayout.EAST);

            Point sortie = new Point(100, 100);
            Simulation.ajouterSortie(sortie);

            sliderNbPersonnes.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                	int nbPersonnes = sliderNbPersonnes.getValue();
	                while (nbPersonnesCourant < nbPersonnes) {
	                    int x = (int) (Math.random() * panSimulation.getWidth());
	                    int y = (int) (Math.random() * panSimulation.getHeight());
	                    double vitesse = Math.random() * (parametresSimulation.getVitesseMax() - parametresSimulation.getVitesseMin()) + parametresSimulation.getVitesseMin()+0.01;
	                    double masse = Math.random() * (parametresSimulation.getMasseMax() - parametresSimulation.getMasseMin()) + parametresSimulation.getMasseMin();
	                    double rayon = (Math.random()*(FenetreSimulation.parametresSimulation.getRayonMin()+FenetreSimulation.parametresSimulation.getRayonMax())/2 + FenetreSimulation.parametresSimulation.getRayonMin())/2;
	                    Simulation.ajouterPersonne(new Agent(new Pt(x, y), new Pt(sortie.getX(), sortie.getY()), vitesse, rayon, masse, 0.5));
	                    nbPersonnesCourant++;
	                }
	                while (nbPersonnesCourant > nbPersonnes) {
	                    Simulation.supprimerPersonne();
	                    nbPersonnesCourant--;
	                }
                }
            });
            panSimulation.revalidate();
            panSimulation.repaint();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);
    }
}