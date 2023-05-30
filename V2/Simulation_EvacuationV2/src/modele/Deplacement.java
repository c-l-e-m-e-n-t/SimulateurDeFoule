package modele;


public class Deplacement {

	/** Constantes. */
	private final static double A = 2*Math.pow(10, 3);
	private final static double B = 0.08;
	private final static double k = 1.2*Math.pow(10, 5);
	private final static double kappa = 2.4*Math.pow(10, 5);

	/** Pas d'intégration. */
	private static double dt = 0.1;


	/** Fonction qui renvoie x si x est positif
	 * et 0 sinon
	 * @param x le nombre à tester
	 * @return x si x est positif et 0 sinon
	 */
	private static double g(double x) {
		double y = 0;
		if (x > 0) {
			y = x;
		}
		return y;
	}

	/** Renvoie la force exercée par l'agent j sur l'agent i.
	 * @param agents les agents de la simulation
	 * @param i numéro de l'agent
	 * @param j numéro de l'agent
	 * @return la force exercée par l'agent j sur l'agent i 
	 */
	private static Vecteur forceEntreDeuxAgents(Agent[] agents, int i, int j) {
		Point positionI = agents[i].getPosition();
		Vecteur vitesseI = agents[i].getVitesse();
		double rayonI = agents[i].getRayon();
		Point positionJ = agents[j].getPosition();
		Vecteur vitesseJ = agents[j].getVitesse();
		double rayonJ = agents[j].getRayon();

		double d = Point.distancePoint(positionI, positionJ);
		Vecteur n = Vecteur.vecteurNormal(positionJ, positionI);
		Vecteur t = Vecteur.vecteurTangent(positionJ, positionI);
		double deltaV = Vecteur.produitScalaire(Vecteur.difference(vitesseJ, vitesseI), t);
		double rayonTotal = rayonI + rayonJ;

		double a = A * Math.exp((rayonTotal - d) / B) + (k * g(rayonTotal - d));
		double b = kappa * g(rayonTotal - d) * deltaV;
		
		// force = a*n + b*t
		Vecteur force = Vecteur.somme(Vecteur.multiplication(n, a), Vecteur.multiplication(t, b));
		if (Vecteur.norme(force) > 600) {
			force = Vecteur.multiplication(force, 600 /Vecteur.norme(force));
		}
		return force;
	}

	/** Renvoie la force exercée par les agents entre eux.
	 * @param agents les agents de la simulation
	 * @return la force exercée par les agents entre eux
	 */
	private static Vecteur[] forceAgents(Agent[] agents) {
		Vecteur[] forceTotale = Vecteur.initialiser(agents.length);
		for (int i = 0; i < agents.length; i++) {
			for (int j = 0; j < i; j++) {
				Vecteur force = forceEntreDeuxAgents(agents, i, j);
				forceTotale[i] = Vecteur.somme(forceTotale[i], force);
				forceTotale[j] = Vecteur.difference(forceTotale[j], force);
			}
		}
		return forceTotale;
	}

	/** Renvoie la force exercée par le mur j sur l'agent i.
	 * @param agents les agents de la simulation
	 * @param murs les murs de la simulation
	 * @param i numéro de l'agent
	 * @param j numéro du mur
	 * @return la force exercée par le mur j sur l'agent i 
	 */
	public static Vecteur forceEntreMurEtAgent(Agent[] agents, Segment[] murs, int i, int j) {

		Point positionI = agents[i].getPosition();
		Vecteur vitesseI = agents[i].getVitesse();
		double rayonI = agents[i].getRayon();
		Segment murJ = murs[j];

		Point pointProche = Segment.pointProche(positionI, murJ);
		double d = Point.distancePoint(positionI, pointProche);
		Vecteur n = Vecteur.vecteurNormal(pointProche, positionI);
		Vecteur t = Vecteur.vecteurTangent(pointProche, positionI);
		double deltaV = - Vecteur.produitScalaire(vitesseI, t);

		double a = A * Math.exp((rayonI - d) / B) + (k * g(rayonI - d));
		double b = kappa * g(rayonI - d) * deltaV;

		// force = a*n + b*t
		Vecteur force = Vecteur.somme(Vecteur.multiplication(n, a), Vecteur.multiplication(t, b));
		
		if (Vecteur.norme(force) > 1500) {
			force = Vecteur.multiplication(force, 1500 /Vecteur.norme(force));
		}

		return force;
	}

	/** Renvoie la force exercée par les murs sur les agents.
	 * @param agents les agents de la simulation
	 * @param murs les murs de la simulation
	 * @return la force exercée par les murs sur les agents 
	 */
	private static Vecteur[] forceMurs(Agent[] agents, Segment[] murs) {
		Vecteur[] forceTotale = Vecteur.initialiser(agents.length);
		for (int i = 0; i < agents.length; i++) {
			for (int j = 0; j < murs.length; j++) {
				Vecteur force = forceEntreMurEtAgent(agents, murs, i, j);
				forceTotale[i] = Vecteur.somme(forceTotale[i], force);
			}
			
		}
		return forceTotale;
	}

	/** Renvoie l'accéleration subie par les agents.
	 * @param agents les agents de la simulation
	 * @param murs les murs de la simulation
	 * @return l'accéleration subie par les agents
	 */
	private static Vecteur[] calculAcceleration(Agent[] agents, Segment[] murs) {

		Vecteur[] acceleration = new Vecteur[agents.length];
		Vecteur[] forceAgents = forceAgents(agents);
		Vecteur[] forceMurs = forceMurs(agents, murs);

		for (int i = 0; i < agents.length; i++) {
			agents[i].calculCible(murs);

			agents[i].setPression(Vecteur.norme(forceAgents[i]) + Vecteur.norme(forceMurs[i]));
			if (agents[i].getPression() > 2000) {
				agents[i].estMort = true;
			}

			Vecteur v0e0 = agents[i].calculVitesseDesiree();
			Vecteur v = agents[i].getVitesse();
			double tau = agents[i].getTau();
			double m = agents[i].getMasse();
			

			// (v0e0 - v) / tau
			Vecteur a1 = Vecteur.multiplication(Vecteur.difference(v0e0, v), 1 / tau);
			// forceAgents / m
			Vecteur a2 = Vecteur.multiplication(forceAgents[i], 1 / m);
			// forceMurs / m
			Vecteur a3 = Vecteur.multiplication(forceMurs[i], 1 / m);

			acceleration[i] = Vecteur.somme(Vecteur.somme(a1, a2), a3);
		}
		return acceleration;
	}


	/** Détermine la position des agents à l'instant k+1
	 * suivant le schéma d'euler
	 * @param agents les agents à l'instant k
	 * @param murs les murs à l'instant k
	 */
	public static void euler(Agent[] agents, Segment[] murs) {
		Vecteur[] acceleration = calculAcceleration(agents, murs);
		for (int i = 0; i < agents.length; i++) {
			if (!agents[i].estSorti && !agents[i].estMort) {
				Point positionI = agents[i].getPosition();
				Vecteur vitesseI = agents[i].getVitesse();
				Vecteur a = acceleration[i];

				// v' = v + a * dt = v + v1
				Vecteur v1 = Vecteur.multiplication(a, dt);
				vitesseI.translater(v1.getX(), v1.getY());

				// r' = r + v' * dt = r + r1
				Vecteur r1 = Vecteur.multiplication(vitesseI, dt);
				positionI.translater(r1.getX(), r1.getY());

				if (agents[i].getDistanceSortie() < agents[i].getRayon()+ 0.3) {
					agents[i].estSorti = true;
					agents[i].setPosition(new Point(10000, 10000));
				}

			}
		}

	}

}
