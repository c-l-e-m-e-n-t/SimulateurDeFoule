package simulation;

/** Point modélise un point géométrique dans un plan équipé d'un
 * repère cartésien.
 */
public class Point {
	private double x;		// abscisse
	private double y;		// ordonnée

	/** Construire un point à partir de son abscisse et de son ordonnée.
	 * @param vx abscisse
	 * @param vy ordonnée
	 */
	public Point(double vx, double vy) {
		this.x = vx;
		this.y = vy;
	}

	/** Construire un point à partir d'un point et un vecteur.
	 * @param p point
	 * @param v vecteur
	 */
	public Point(Point p, Vecteur v) {
		this.x = p.getX() + v.getX();
		this.y = p.getY() + v.getY();
	}

	/** Obtenir l'abscisse du point.
	 * @return abscisse du point
	 */
	public double getX() {
		return this.x;
	}

	/** Obtenir l'ordonnée du point.
	 * @return ordonnée du point
	 */
	public double getY() {
		return this.y;
	}

	/** Changer l'abscisse du point.
	  * @param vx nouvelle abscisse
	  */
	public void setX(double vx) {
		this.x = vx;
	}

	/** Changer l'ordonnée du point.
	  * @param vy nouvelle ordonnée
	  */
	public void setY(double vy) {
		this.y = vy;
	}

	/** Translater le point.
	* @param dx déplacement suivant l'axe des X
	* @param dy déplacement suivant l'axe des Y
	*/
	public void translater(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	/** Distance entre deux points
	 * @param premier point
	 * @param deuxième point
	 * @return distance entre les deux points
	 */
	public static double distancePoint(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
					+ Math.pow(p1.getY() - p2.getY(), 2));
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	/** Afficher le point. */
	public void afficher() {
		System.out.print(this);
	}

}
