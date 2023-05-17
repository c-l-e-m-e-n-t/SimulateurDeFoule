package modele;

/** Point modélise un Point géométrique dans un plan équipé d'un
 * repère cartésien.
 */
public class Point {
	private double x;		// abscisse
	private double y;		// ordonnée

	/** Construire un Point à partir de son abscisse et de son ordonnée.
	 * @param vx abscisse
	 * @param vy ordonnée
	 */
	public Point(double vx, double vy) {
		this.x = vx;
		this.y = vy;
	}

	/** Construire un Point à partir d'un Point et un vecteur.
	 * @param p Point
	 * @param v vecteur
	 */
	public Point(Point p, Vecteur v) {
		this.x = p.getX() + v.getX();
		this.y = p.getY() + v.getY();
	}

	/** Obtenir l'abscisse du Point.
	 * @return abscisse du Point
	 */
	public double getX() {
		return this.x;
	}

	/** Obtenir l'ordonnée du Point.
	 * @return ordonnée du Point
	 */
	public double getY() {
		return this.y;
	}

	/** Changer l'abscisse du Point.
	  * @param vx nouvelle abscisse
	  */
	public void setX(double vx) {
		this.x = vx;
	}

	/** Changer l'ordonnée du Point.
	  * @param vy nouvelle ordonnée
	  */
	public void setY(double vy) {
		this.y = vy;
	}

	/** Translater le Point.
	* @param dx déplacement suivant l'axe des X
	* @param dy déplacement suivant l'axe des Y
	*/
	public void translater(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	/** Distance entre deux Points
	 * @param premier Point
	 * @param deuxième Point
	 * @return distance entre les deux Points
	 */
	public static double distancePoint(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
					+ Math.pow(p1.getY() - p2.getY(), 2));
	}

	/** Distance entre deux Points
	 * @param premier Point
	 * @return distance entre les deux Points
	 */
	public double distance(Point p) {
		return Math.sqrt(Math.pow(this.x - p.getX(), 2)
					+ Math.pow(this.y - p.getY(), 2));
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	/** Afficher le Point. */
	public void afficher() {
		System.out.print(this);
	}



}
