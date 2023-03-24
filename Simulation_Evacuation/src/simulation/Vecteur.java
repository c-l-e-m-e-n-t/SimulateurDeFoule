package simulation;


/** Vecteur modélise un vecteur dans un plan équipé d'un
 * repère cartésien.
 */
public class Vecteur {

	private double x;		// abscisse
	private double y;		// ordonnée

	/** Construire un vecteur à partir de son abscisse et de son ordonnée.
	 * @param vx abscisse
	 * @param vy ordonnée
	 */
	public Vecteur(double vx, double vy) {
		this.x = vx;
		this.y = vy;
	}

	/** Construire un vecteur à partir de deux points.
	 * @param p1 début du vecteur
	 * @param p2 fin du vecteur
	 */
	public Vecteur(Point p1, Point p2) {
		this.x = p2.getX() - p1.getX();
		this.y = p2.getY() - p1.getY();
	}

	/** Construire un vecteur à partir d'un segment.
	 * @param segment le segment
	 */
	public Vecteur(Segment segment) {
		this.x = segment.getExtremite2().getX() - segment.getExtremite1().getX();
		this.y = segment.getExtremite2().getY() - segment.getExtremite1().getY();
	}

	/** Obtenir l'abscisse du vecteur.
	 * @return abscisse du vecteur
	 */
	public double getX() {
		return this.x;
	}

	/** Obtenir l'ordonnée du vecteur.
	 * @return ordonnée du vecteur
	 */
	public double getY() {
		return this.y;
	}

	/** Changer l'abscisse du vecteur.
	  * @param vx nouvelle abscisse
	  */
	public void setX(double vx) {
		this.x = vx;
	}

	/** Changer l'ordonnée du vecteur.
	  * @param vy nouvelle ordonnée
	  */
	public void setY(double vy) {
		this.y = vy;
	}

	/** Modifier le vecteur.
	* @param dx transformation suivant l'axe des X
	* @param dy transformation suivant l'axe des Y
	*/
	public void translater(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	public static Vecteur[] initialiser(int n) {
		Vecteur[] vecteur = new Vecteur[n];
		for (int i = 0; i < n; i++) {
			vecteur[i] = new Vecteur(0, 0);
		}
		return vecteur;
	}

	/** Renvoie la norme du vecteur. */
	public static double norme(Vecteur v) {
		return Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
	}

	/** Renvoie la somme du vecteur v1 avec le vecteur v2.
	 * @param v1 premier vecteur
	 * @param v2 deuxième vecteur
	 * @return v1 + v2
	 */
	public static Vecteur somme(Vecteur v1, Vecteur v2) {
		return new Vecteur(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	/** Renvoie la différence du vecteur v1 avec le vecteur v2.
	 * @param v1 premier vecteur
	 * @param v2 deuxième vecteur
	 * @return v1 - v2
	 */
	public static Vecteur difference(Vecteur v1, Vecteur v2) {
		return new Vecteur(v1.getX() - v2.getX(), v1.getY() - v2.getY());
	}

	/** Renvoie la multiplication de v par un scalaire k.
	 * @param v vecteur
	 * @param k scalaire
	 * @return k * v
	 */
	public static Vecteur multiplication(Vecteur v, double k) {
		return new Vecteur(k * v.getX(), k * v.getY());
	}

	/** Renvoie le produit scalaire entre v1 et v2.
	 * @param v1 premier vecteur
	 * @param v2 deuxième vecteur
	 * @return v1 . v2
	 */
	public static double produitScalaire(Vecteur v1, Vecteur v2) {
		return (v1.getX() * v2.getX()) + (v1.getX() * v2.getY());
	}

	/** Vecteur normal de p1 vers p2.
	 * @param p1 premier point
	 * @param p2 deuxième point
	 * @return le vecteur normal entre p1 et p2
	 */
	public static Vecteur vecteurNormal(Point p1, Point p2) {
		double d = Point.distancePoint(p1, p2);
		return new Vecteur((p2.getX() - p1.getX()) / d, (p2.getY() - p1.getY()) / d);
	}

	/** Vecteur tangent de p1 vers p2.
	 * @param p1 premier point
	 * @param p2 deuxième point
	 * @return le vecteur tangent entre p1 et p2
	 */
	public static Vecteur vecteurTangent(Point p1, Point p2) {
		Vecteur n = Vecteur.vecteurNormal(p1, p2);
		return new Vecteur(-n.getY(), n.getX());
	}

	/** Renvoie le cosinus entre les vecteurs.
	 * @param vec1 le premier vecteur
	 * @param vec2 le deuxième vecteur
	 * @return le cosinus entre les deux vecteurs
	 */
	public static double cosinus(Vecteur vec1, Vecteur vec2) {
		double produitScalaire = Vecteur.produitScalaire(vec1, vec2);
		return produitScalaire / (Vecteur.norme(vec1) + Vecteur.norme(vec2));
	}

	public String toString() {
		return "vec(" + this.x + ", " + this.y + ")";
	}

	/** Afficher le vecteur. */
	public void afficher() {
		System.out.print(this);
	}
}
