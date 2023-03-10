package simulation;


public class Deplacement {

	/** Constantes. */
	private double A = 2*Math.pow(10,	3);
	private double B = 0.08;
	private double k = 1.2*Math.pow(10, 5);
	private double kap = 2.4*Math.pow(10, 5);

	/** Agents de la simulation. */
	private Agent[] agents;

	/** NB de personnes dans la simulation. */
	private int N;

	/** Obstacles de la simulation. */
	private Segment[] murs;

	/** Pas d'intégration. */
	private double dt;


	/**  Mettre en place une épate de déplacement .
	 *  @param agents Les agents de la simulation
	 *  @param murs	Les murs de la simulation
	 *  @param dt le pas d'intégration
	 */
	public Deplacement(Agent[] agents, Segment[] murs, double dt) {
		this.agents = agents;
		this.N = agents.length;
		this.murs = murs;
		this.dt = dt;
	}

	/** Fonction qui renvoie x si x est positif
	 * et 0 sinon
	 * @param x le nombre à tester
	 * @return x si x est positif et 0 sinon
	 */
	private double g(double x) {
		double y = 0;
		if (x > 0) {
			y = x;
		}
		return y;
	}

	/** Renvoie la force exercée par l'agent j sur l'agent i.
	 * @param i numéro de l'agent
	 * @param j numéro de l'agent
	 * @return la force exercée par l'agent j sur l'agent i 
	 */
	private Vecteur forceEntreDeuxAgents (int i, int j) {
		Point positionI = this.agents[i].getPosition();
		Vecteur vitesseI = this.agents[i].getVitesse();
		double rayonI = this.agents[i].getRayon();
		Point positionJ = this.agents[j].getPosition();
		Vecteur vitesseJ = this.agents[j].getVitesse();
		double rayonJ = this.agents[j].getRayon();

		double d = Point.distancePoint(positionI, positionJ);
		Vecteur n = Vecteur.vecteurNormal(positionJ, positionI);
		Vecteur t = Vecteur.vecteurTangent(positionJ, positionI);
		double dv_t = Vecteur.produitScalaire(Vecteur.difference(vitesseJ, vitesseI), t);
		double rayonTotal = rayonI + rayonJ;

		double a = this.A * Math.exp((rayonTotal - d) / this.B) + (this.k * this.g(rayonTotal - d));
		double b = this.kap * this.g(rayonTotal - d) * dv_t;
		
		// force = a*n + b*t
		Vecteur force = Vecteur.somme(Vecteur.multiplication(n, a), Vecteur.multiplication(t, b));
		return force;
	}

	/** Renvoie la force exercée par les agents sur l'agent i.
	 * @param i numéro de l'agent
	 * @return la force exercée par les agents sur l'agent i 
	 */
	private Vecteur forceAgents (int i) {
		Vecteur forceTotale = new Vecteur(0, 0);
		for (int j = 0; j < this.N; j++) {
			if (j != i) {
				Vecteur force = this.forceEntreDeuxAgents(i, j);
				forceTotale = Vecteur.somme(force, forceTotale);
			}
		}
		return forceTotale;
	}

	/** Renvoie la force exercée par le mur j sur l'agent i.
	 * @param i numéro de l'agent
	 * @param j numéro du mur
	 * @return la force exercée par le mur j sur l'agent i 
	 */
	private Vecteur forceEntreMurEtAgent (int i, int j) {

		Point positionI = this.agents[i].getPosition();
		Vecteur vitesseI = this.agents[i].getVitesse();
		double rayonI = this.agents[i].getRayon();
		Segment murJ = this.murs[j];

		Point pointProche = Segment.distanceAvecSegment(positionI, murJ);
		double d = Point.distancePoint(positionI, pointProche);
		Vecteur n = Vecteur.vecteurNormal(pointProche, positionI);
		Vecteur t = Vecteur.vecteurTangent(pointProche, positionI);
		double dv_t = - Vecteur.produitScalaire(vitesseI, t);

		double a = this.A * Math.exp((rayonI - d) / this.B) + (this.k * this.g(rayonI - d));
		double b = this.kap * this.g(rayonI - d) * dv_t;

		// force = a*n + b*t
		Vecteur force = Vecteur.somme(Vecteur.multiplication(n, a), Vecteur.multiplication(t, b));
		return force;
	}

	/** Renvoie la force exercée par les murs sur l'agent i.
	 * @param i numéro de l'agent
	 * @return la force exercée par les murs sur l'agent i 
	 */
	private Vecteur forceMurs (int i) {
		Vecteur forceTotale = new Vecteur(0, 0);
		for (int j = 0; j < this.murs.length; j++) {
			if (j != i) {
				Vecteur force = this.forceEntreMurEtAgent(i, j);
				forceTotale = Vecteur.somme(force, forceTotale);
			}
		}
		return forceTotale;
	}


	/** Renvoie l'accéleration subie par l'agent i.
	 * @param i numéro de l'agent
	 * @return l'accéleration subie par l'agent i
	 */
	public Vecteur calculAcceleration(int i) {

		Vecteur forceAgents = this.forceAgents(i);
		Vecteur forceMurs = this.forceMurs(i);
		Vecteur v0e0 = this.agents[i].calculVitesseDesiree(this.agents);
		Vecteur v = this.agents[i].getVitesse();
		double tau = this.agents[i].getTau();
		double m = this.agents[i].getMasse();

		// (v0e0 - v) / tau
		Vecteur a1 = Vecteur.multiplication(Vecteur.difference(v0e0, v), 1 / tau);
		// forceAgents / m
		Vecteur a2 = Vecteur.multiplication(forceAgents, 1 / m);
		// forceMurs / m
		Vecteur a3 = Vecteur.multiplication(forceMurs, 1 / m);
		
		return Vecteur.somme(Vecteur.somme(a1, a2), a3);
	}


	/** Met à jour la position des agents
	 * suivant le schéma d'euler. */
	public void euler () {
		for (int i = 0; i < this.N; i++) {
			Point positionI = this.agents[i].getPosition();
			Vecteur vitesseI = this.agents[i].getVitesse();

			Vecteur a = this.calculAcceleration(i);
			// v' = v + a * dt
			Vecteur v = Vecteur.somme(vitesseI, Vecteur.multiplication(a, dt));
			// r' = r + v1 * dt
			Vecteur v1 = Vecteur.multiplication(v, dt);
			positionI.translater(v1.getX(), v1.getY());
		}
	}

}
