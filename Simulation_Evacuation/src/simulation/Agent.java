package simulation;

public class Agent {

	/** Position de l'agent. 
	 * Forme : [x, y] */
	public double[] r = new double[2];

	/** Vitesse de l'agent.
	 * Forme : [Vx, Vy] */
	public double[] v = new double[2];

	/** Acceleration de l'agent.
	 * Forme : [Ax, Ay] */
	public double[] a = new double[2];


	/** Cible de l'agent. 
	 * Forme : [x, y] */
	public double[] cible = new double[2];

	/** Direction désirée de l'agent. 
	 * Forme : [e0x, e0y] */
	public double[] e0 = new double[2];

	/** Vitesse désirée de l'agent. 
	 * Forme : [v0x, v0y] */
	public double[] v0 = new double[2];

	/** Vitesse désirée normalisée de l'agent.
	 * Forme : v0 */
	public double v0Norm = 1.5;
	
	
	/** Rayon de l'agent. */
	public double rayon;

	/** Masse de l'agent. */
	public double m;

	/** Temps de réaction de l'agent. */
	public double tau = 0.5;

	/** Niveau de panique de l'agent. */
	public double p;
	

}
