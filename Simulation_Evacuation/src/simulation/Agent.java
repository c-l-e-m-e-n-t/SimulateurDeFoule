package simulation;

public class Agent {

	/** Position de l'agent. */
	private Point position;

	/** Vitesse de l'agent. */
	private Vecteur vitesse;


	/** Cible de l'agent. */
	private Point cible;

	/** Vitesse désirée en norme de l'agent. */
	private double v0 = 1.5;


	/** Rayon de l'agent. */
	private double rayon;

	/** Masse de l'agent. */
	private double masse;

	/** Temps de réaction de l'agent. */
	private double tau = 0.5;

	/**  Construire un Agent à partir de ses caractéristiques.
	 *  @param position	Position de l'agent
	 *  @param vitesse Vitesse de l'agent
	 *  @param cible Cible de l'agent
	 *  @param v0 Vitesse désirée en norme de l'agent
	 *  @param rayon Rayon de l'agent
	 *  @param masse Masse de l'agent
	 *  @param tau Temps de réaction de l'agent
	 */
	public Agent(Point position, Vecteur vitesse, Point cible, double v0, double rayon, double masse, double tau) {
		this.position = position;
		this.vitesse = vitesse;
		this.cible = cible;
		this.v0 = v0;
		this.rayon = rayon;
		this.masse = masse;
		this.tau = tau;
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
	 * @param agents les agents de la simulation
	 * @return la direction désirée de l'agent
	 */
	public Vecteur calculDirectionDesiree(Agent[] agents) {
		Vecteur e = new Vecteur(this.position, this.cible);
		Vecteur e0 = Vecteur.multiplication(e, 1 / Vecteur.norme(e));
		return e0;
	}

	/** Renvoie la vitesse désirée de l'agent.
	 * @param agents les agents de la simulation
	 * @return la vitesse désirée de l'agent
	 */
	public Vecteur calculVitesseDesiree(Agent[] agents) {
		Vecteur e0 = this.calculDirectionDesiree(agents);
		return Vecteur.multiplication(e0, this.v0);
	}

}
