package simulation;

public class Segment {

	/** Début du segment. */
	private Point extremite1;

	/** Fin du segment. */
	private Point extremite2;


	/**  Construire un Segment à partir de ses deux points extrémités.
	 *  @param ext1	le premier point extrémité
	 *  @param ext2	le deuxième point extrémité
	 */
	public Segment(Point ext1, Point ext2) {
		this.extremite1 = ext1;
		this.extremite2 = ext2;
	}

	/** Obtenir la première extrémité du segment.
	 * @return la première extrémité du segment
	 */
	public Point getExtremite1() {
		return this.extremite1;
	}

	/** Obtenir la deuxième extrémité du segment.
	 * @return la dexième extrémité du segment
	 */
	public Point getExtremite2() {
		return this.extremite2;
	}

   /** Translater le segment.
	* @param dx déplacement suivant l'axe des X
	* @param dy déplacement suivant l'axe des Y
	*/
	public void translater(double dx, double dy) {
		this.extremite1.translater(dx, dy);
		this.extremite2.translater(dx, dy);
	}

	/** Obtenir la longueur du segment.
	 * @return la longueur du segment
	 */
	public double longueur() {
		return Point.distancePoint(extremite2, extremite1);
	}

	/** Renvoie le point du segment le plus proche du point en entrée.
	 * @param p Le point
	 * @return Le point du segment le plus proche du point en entrée
	 */
	public static Point distanceAvecSegment(Point p, Segment segment) {
		// v1 = AP
		Vecteur v1 = new Vecteur(segment.getExtremite1(), p);
		// v2 = AB
		Vecteur v2 = new Vecteur(segment.getExtremite1(), segment.getExtremite2());

		// temp = AP.AB / ||AB|| ^ 2
		double temp = Vecteur.produitScalaire(v1, v2) / Math.pow(Vecteur.norme(v2), 2);
		temp = Math.min( Math.max( 0, temp), 1);
		Vecteur v3 = Vecteur.multiplication(v2, temp);
		Point pointProche = new Point(segment.getExtremite1(), v3);
		return pointProche;
	}

	/** Renvoie le point d'intersection des droites droite1 et droite2.
	 * @param droite1 la première droite
	 * @param droite2 la deuxième droite
	 * @return le point d'intersection des droites droite1 et droite2
	 */
	public static Point intersectionDroites(Segment droite1, Segment droite2) {
		// A1A2
		Vecteur a1a2 = new Vecteur(droite1);
		// B1B2
		Vecteur b1b2 = new Vecteur(droite2);
		// B1A1
		Vecteur b1a1 = new Vecteur(droite2.getExtremite1(), droite1.getExtremite1());
		// A1A2 tangent
		Vecteur a1a2T = new Vecteur(- a1a2.getY(), a1a2.getX());

		// temp = A1A2T.B1A1 / A1A2T.B1B2
		double temp = Vecteur.produitScalaire(a1a2T, b1a1) / Vecteur.produitScalaire(a1a2T, b1b2);

		return new Point(droite2.getExtremite1(), Vecteur.multiplication(b1b2, temp));
	}

	/** Renvoie true si le point p appartient au segment.
	 * @param segment le segment considéré
	 * @param p le point considéré
	 * @return true si le point p appartient au segment
	 */
	public static boolean contient(Segment segment, Point p) {
		double dtot = Point.distancePoint(segment.getExtremite1(), segment.getExtremite2());
		double d1 = Point.distancePoint(segment.getExtremite1(), p);
		double d2 = Point.distancePoint(segment.getExtremite2(), p);
		return dtot - d1 - d2 < 0.0001;
	}

	/** Renvoie le cosinus entre les deux segments.
	 * @param seg1 le premier segment
	 * @param seg2 le deuxième segment
	 * @return le cosinus entre les deux segments
	 */
	public static double cosinus(Segment seg1, Segment seg2) {
		Vecteur vec1 = new Vecteur(seg1);
		Vecteur vec2 = new Vecteur(seg2);
		double produitScalaire = Vecteur.produitScalaire(vec1, vec2);
		return produitScalaire / (Vecteur.norme(vec1) + Vecteur.norme(vec2));
	}


	public String toString() {
		return "[" + this.extremite1.toString() + " - " + this.extremite2.toString() + "]";
	}

	/** Afficher le segment. */
	public void afficher() {
		System.out.print(this);
	}

}
