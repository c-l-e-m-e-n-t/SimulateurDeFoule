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

	public String toString() {
		return "[" + this.extremite1.toString() + " - " + this.extremite2.toString() + "]";
	}

	/** Afficher le segment. */
	public void afficher() {
		System.out.print(this);
	}

}
