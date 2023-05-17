package testPackage;


import afficheur.Ecran;
import modele.Agent;
import modele.Deplacement;
import modele.Point;
import modele.Segment;

//import javax.swing.*;
import java.awt.Color;


public class Tester_fonctionnalites {

	public static void main(String[] args) {  
		
		// Construire un écran
		Ecran ecran = new Ecran("ExempleEcran", 400, 250, 15);

		Segment mur1 = new Segment(new Point(0, 0), new Point(0, 2));
		Segment mur12 = new Segment(new Point(0, 3), new Point(0, 5));
		Segment mur2 = new Segment(new Point(0, 5), new Point(5, 5));
		Segment mur3 = new Segment(new Point(5, 5), new Point(5, 0));
		Segment mur4 = new Segment(new Point(5, 0), new Point(0, 0));
		Segment mur5 = new Segment(new Point(2, 1), new Point(2, 3));

		ecran.dessinerLigne(0, 0, 0, 5, Color.red);
		ecran.dessinerLigne(0, 5, 5, 5, Color.red);
		ecran.dessinerLigne(5, 5, 5, 0, Color.red);
		ecran.dessinerLigne(5, 0, 0, 0, Color.red);
		ecran.dessinerLigne(2, 1, 2, 3, Color.red);


		Segment[] murs = {mur1, mur12, mur2, mur3, mur4, mur5};
		
		Point sortie = new Point(6, 2.5);
		ecran.dessinerPoint(sortie.getX(), sortie.getY(), Color.green);

		Agent agent1 = new Agent(new Point(4, 2), sortie, 1.5, 0.25, 80, 0.5, Color.BLACK);
		Agent agent2 = new Agent(new Point(3.5, 2), sortie, 1.5, 0.25, 80, 0.5, Color.BLACK);
		//Agent agent3 = new Agent(new Point(1.5, 4), sortie, 1.5, 0.25, 80, 0.5);
		
		Agent[] agents = {agent1}; //agent1, agent2, agent3};
		System.out.println(agent1);

		
		for (int k = 0; k < 500; k++) {
			for (int i = 0; i < agents.length; i++) {
				Point agentI = agents[i].getPosition();
				ecran.dessinerCercle(agentI.getX(), agentI.getY(), agents[i].getRayon(), Color.yellow);
			}
			Deplacement.euler(agents, murs);
			ecran.rafraichir();
			//System.out.println(k);
		}
		
	}

}
