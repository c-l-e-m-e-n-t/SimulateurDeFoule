package simulation;

public class Agent {

	/** Position de l'agent. */
	private Point position;

	/** Vitesse de l'agent. */
	private Vecteur vitesse;

	/** Cible de l'agent. */
	private Point cible;

	/** Vitesse désirée en norme de l'agent. */
	private double v0;


	/** Rayon de l'agent. */
	private double rayon;

	/** Masse de l'agent. */
	private double masse;

	/** Temps de réaction de l'agent. */
	private double tau;

	/**  Construire un Agent à partir de ses caractéristiques.
	 *  @param position	Position de l'agent
	 *  @param cible Cible de l'agent
	 *  @param v0 Vitesse désirée en norme de l'agent
	 *  @param rayon Rayon de l'agent
	 *  @param masse Masse de l'agent
	 *  @param tau Temps de réaction de l'agent
	 */
	public Agent(Point position, Point cible, double v0, double rayon, double masse, double tau) {
		this.position = position;
		this.cible = cible;
		this.v0 = v0;
		this.rayon = rayon;
		this.masse = masse;
		this.tau = tau;
		this.vitesse = this.calculVitesseDesiree();
	}

	/** Obtenir la position de l'agent.
	 * @return la position de l'agent
	 */
	public Point getPosition() {
		return this.position;
	}

	/** Obtenir la vitesse de l'agent.
	 * @return la vitesse de l'agent
	 */
	public Vecteur getVitesse() {
		return this.vitesse;
	}

	/** Obtenir la cible de l'agent.
	 * @return la cible de l'agent
	 */
	public Point getCible() {
		return this.cible;
	}

	/** Changer la cible de l'agent.
	 * @param la nouvelle cible de l'agent
	 */
	public void setCible(Point nouvelleCible) {
		this.cible = nouvelleCible;
	}

	/** Obtenir la vitesse désirée de l'agent.
	 * @return la vitesse désirée de l'agent
	 */
	public double getVitesseDesiree() {
		return this.v0;
	}

	/** Obtenir le rayon de l'agent.
	 * @return le rayon de l'agent
	 */
	public double getRayon() {
		return this.rayon;
	}

	/** Obtenir la masse de l'agent.
	 * @return la masse de l'agent
	 */
	public double getMasse() {
		return this.masse;
	}

	/** Obtenir le temps de réaction de l'agent.
	 * @return le temps de réaction de l'agent
	 */
	public double getTau() {
		return this.tau;
	}

	/** Renvoie la direction désirée de l'agent.
	 * @return la direction désirée de l'agent
	 */
	public Vecteur calculDirectionDesiree() {
		Vecteur e = new Vecteur(this.position, this.cible);
		Vecteur e0 = Vecteur.multiplication(e, 1 / Vecteur.norme(e));
		return e0;
	}

	/** Renvoie la vitesse désirée de l'agent.
	 * @return la vitesse désirée de l'agent
	 */
	public Vecteur calculVitesseDesiree() {
		Vecteur e0 = this.calculDirectionDesiree();
		return Vecteur.multiplication(e0, this.v0);
	}

	/** Renvoie la distance entre deux agents.
	 * @param agent1 le premier agent
	 * @param agent2 le deuxième agent
	 * @return la distance entre deux agents
	 */
	public static double distanceAgents(Agent agent1, Agent agent2) {
		return Point.distancePoint(agent1.getPosition(), agent2.getPosition());
	}

	public String toString() {
		return "Position : " + this.position.toString()
		+ "\n Vitesse : " + this.vitesse.toString()
		+ "\n Cible : " + this.cible.toString()
		+ "\n Vitesse Désirée : " + Double.toString(this.v0)
		+ "\n Rayon : " + Double.toString(this.rayon)
		+ "\n Masse : " + Double.toString(this.masse)
		+ "\n Temps de réaction : " + Double.toString(this.tau);
	}

	/** Afficher les caractéristiques de l'agent. */
	public void afficher() {
		System.out.print(this);
	}

}
