package Simulation;


public class Deplacement {
	
	/** Position de l'agent. */
	private double[] r;

	/** Vitesse de l'agent. */
	private double[] v;

	/** Accelération de l'agent. */
	private double[] a;


	/** Cible de l'agent. */
	private double[] cible;

	/** Direction désirée de l'agent. */
	private double[] e0; // normalize(cible - r)

	/** Vitesse désirée de l'agent. */
	private double[] v0;


	/** Rayon de l'agent. */
	private double rayon;

	/** Masse de l'agent. */
	private double m;

	/** Temps d'acceleration. */
	private double tau;

	/** Temps d'acceleration. */
	private double ee;

}
