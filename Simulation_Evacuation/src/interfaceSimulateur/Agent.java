import java.awt.Point;

import java.awt.Color;

public class Agent {
	
	/** Définis si l'agent est sorti*/
	private boolean sortis;
	
	/** Point de l'agnet */
	private Point point;

	/** Position de l'agent. */
	private Pt position;

	/** Vitesse de l'agent. */
	private Vecteur vitesse;

	/** Cible de l'agent. */
	private Pt cible;

	/** Sortie de la simulation. */
	private Pt sortie;

	/** Vitesse désirée en norme de l'agent. */
	private double v0;

	/** Rayon de l'agent. */
	private double rayon;

	/** Masse de l'agent. */
	private double masse;

	/** Temps de réaction de l'agent. */
	private double tau;

	/** Pression subie par l'agent. */
	private double pression;

	/** Couleur de l'agent. */
	private Color couleur;

	/**  Construire un Agent à partir de ses caractéristiques.
	 *  @param position	Position de l'agent
	 *  @param sortie Sortie de la simulation
	 *  @param v0 Vitesse désirée en norme de l'agent
	 *  @param rayon Rayon de l'agent
	 *  @param masse Masse de l'agent
	 *  @param tau Temps de réaction de l'agent
	 */
	public Agent(Pt position, Pt sortie, double v0, double rayon, double masse, double tau) {
		this.position = position;
		this.cible = sortie;
		this.sortie = sortie;
		this.v0 = v0;
		this.rayon = rayon;
		this.masse = masse;
		this.tau = tau;
		this.vitesse = this.calculVitesseDesiree();
		this.point = new Point((int)position.getX(), (int)position.getY());
		this.couleur = Color.RED;
		this.sortis = false;
	}

	/** Verifier si l'agent est sorti
	 * @return sortis True si l'agent est sorti
	 */
	public boolean getSortis() {
		return this.sortis;
	}
	
	/** Obtenir la couleur de l'agent.
	 * @return la couleur de l'agent
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/** Changer la couleur de l'agent.
	 * @param nouvelleCouleur la nouvelle couleur de l'agent
	 */
	public void setCouleur(Color nouvelleCouleur) {
		this.couleur = nouvelleCouleur;
	}

	/** Obtenir le point de l'agent
	 * @return le point de l'agent
	 */
	public Point getPoint() {
		return this.point;
	}

	/** Changer le point de l'agent.
	 * @param le nouveau point de l'agent
	 */
	public void setPoint(Point nouveauPoint) {
		this.point = nouveauPoint;
	}

    /** Obtenir la position de l'agent.
	 * @return la position de l'agent
	 */
	public Pt getPosition() {
		return this.position;
	}
	
	/** Changer si l'agent est sorti
	 * @param sortis la nouvelle valeur de sortis
	 */
	public void setSortis(boolean sortis) {
		this.sortis = sortis;
	}

	/** Changer la position de l'agent.
	 * @param la nouvelle position de l'agent 
	*/
	public void setPosition(Pt nouvellePosition) {
		this.position = nouvellePosition;
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
	public Pt getCible() {
		return this.cible;
	}

	/** Changer la cible de l'agent.
	 * @param la nouvelle cible de l'agent
	 */
	public void setCible(Pt nouvelleCible) {
		this.cible = nouvelleCible;
	}

	/** Obtenir la sortie de la simulation.
	 * @return la sortie de la simulation
	 */
	public Pt getSortie() {
		return this.sortie;
	}

	/** Changer la sortie de la simulation.
	 * @param la nouvelle sortie de la simulation
	 */
	public void setSortie(Pt nouvelleSortie) {
		this.sortie = nouvelleSortie;
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

	/** Obtenir la pression subie par l'agent.
	 * @return la pression subie par l'agent
	 */
	public double getPression() {
		return this.pression;
	}

	/** Changer la pression subie par l'agent.
	 * @param la nouvelle pression subie par l'agent
	 */
	public void setPression(double pression) {
		this.pression = pression;
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
		return Pt.distancePoint(agent1.getPosition(), agent2.getPosition());
	}

	/** Renvoie le mur le plus proche dans le chemin de l'agent.
	 * @param murs Les murs de la simulation
	 * @return le mur le plus proche dans le chemin de l'agent
	 */
	public Segment obstacleProche(Segment[] murs, Segment direction) {
		Segment Obstacle = null;
		double distance = -1;
		for (int i = 0; i < murs.length; i++) {
			// Calcul des murs sur la trajectoire
			Pt intersection = Segment.intersectionDroites(direction, murs[i]);
			if (intersection != null) {
				// Si le mur est sur le chemin de l'agent
				if (Segment.contient(direction, intersection)
						|| Vecteur.cosinus(new Vecteur(direction),
								new Vecteur(direction.getExtremite1(), intersection)) > 0) {
					//Parmi ceux trouvés, celui le plus proche
					if (Pt.distancePoint(intersection, position) < distance || distance == -1) {
						Obstacle = murs[i];
						distance = Pt.distancePoint(intersection, position);
					}
				}
			}
			
		}
		return Obstacle;
	}

	/** Calcule la nouvelle cible de l'agent.
	 * @param murs Les murs de la simulation
	 */
	public void calculCible(Segment[] murs) {
		Segment direction = new Segment(this.position, this.cible);
		Segment obstacle = obstacleProche(murs, direction);
		if (obstacle != null) {
			// Calculer les points de passage potentiel
			Vecteur obstacleVecteur = new Vecteur(obstacle);
			// Marge de 10 cm de distance au mur
			obstacleVecteur = Vecteur.multiplication(obstacleVecteur, (this.rayon + 0.5) / Vecteur.norme(obstacleVecteur));
			Pt d1 = new Pt(obstacle.getExtremite1(), Vecteur.difference(new Vecteur(0, 0), obstacleVecteur));
			Pt d2 = new Pt(obstacle.getExtremite2(), obstacleVecteur);

			// Vérifier si les points sont à l'intérieur de la pièce

			// Calcul de la déviation minimale entre la direction initiale, et celle calculée
			if (Vecteur.cosinus(new Vecteur(direction), new Vecteur(this.position, d1))
					> Vecteur.cosinus(new Vecteur(direction), new Vecteur(this.position, d2))) {
				this.cible = d1;
			} else {
				this.cible = d2;
			}
		}
	    // Une fois la cible temporaire franchie, mettre à jour la nouvelle cible vers la sortie.
		if (Pt.distancePoint(this.position, this.cible) < 0.1) {
			this.cible = this.sortie;
		}
	}


	public String toString() {
		return "Position : " + this.position.toString()
		+ "\n Vitesse : " + this.vitesse.toString()
		+ "\n Cible : " + this.cible.toString()
		+ "\n Vitesse Désirée : " + Double.toString(this.v0) + "m/s"
		+ "\n Rayon : " + Double.toString(this.rayon) + "m"
		+ "\n Masse : " + Double.toString(this.masse) + "kg"
		+ "\n Temps de réaction : " + Double.toString(this.tau) + "s";
	}

	/** Afficher les caractéristiques de l'agent. */
	public void afficher() {
		System.out.print(this);
	}

}
