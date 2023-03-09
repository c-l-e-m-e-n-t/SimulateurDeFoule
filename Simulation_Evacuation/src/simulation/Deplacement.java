package simulation;


public class Deplacement {

	/** Constantes. */
	private double A = 2*Math.pow(10,	3);
	private double B = 0.08;
	private double k = 1.2*Math.pow(10, 5);
	private double kap = 2.4*Math.pow(10, 5);

	/** Agents de la simulation. */
	private Agent[] agents;

	/** NB de personnes dans la simulation. */
	private int N;

	/** Obstacles de la simulation. */
	private Mur[] murs;

	/** Pas d'intégration. */
	private double dt;


	public Deplacement(Agent[] agents, Mur[] murs, double dt) {
		this.agents = agents;
		this.N = agents.length;
		this.murs = murs;
		this.dt = dt;
	}


	private double g(double x) {
		double y = 0;
		if (x > 0) {
			y = x;
		}
		return y;
	}

	public void calculDirectionDesiree() {
		for (int i = 0; i < this.N; i++) {
			this.agents[i].e0 = new double[] {0, 0};
            // e0[i, :] = (self.sortie - r[i, :])/ np.linalg.norm(self.sortie - r[i, :])
		}
	}

	public void calculVitesseDesiree() {
		this.calculDirectionDesiree();
		for (int i = 0; i < this.N; i++) {
			this.agents[i].e0 = new double[] {0, 0};
            // e0[i, :] = (self.sortie - r[i, :])/ np.linalg.norm(self.sortie - r[i, :])
			/*
		    #Calcul de la vitesse desiree des agents
		    def vit_des_helbings(self, r):
		        v0e0 = np.zeros((len(r), 2))
		        e0 = self.dir_des(r)
		        for i in range(len(r)):
		            v0e0[i, :] = self.v0[i]*e0[i, :]
		        return v0e0
		*/
		}
	}

	public double[][] vecteursEntreAgents (int i, int j) {
		double xi = this.agents[i].r[0];
		double yi = this.agents[i].r[1];
		double xj = this.agents[j].r[0];
		double yj = this.agents[j].r[1];

		double[] d = {Math.sqrt((xi - xj) * (xi - xj) + (yi - yj) * (yi - yj))};		
		double[] n = {(xi - xj) / d[0], (yi - yj) / d[0]};
		double[] t = {- n[0], n[1]};
		double[][] vecteurs = {d, n, t};

		return vecteurs;
	}

	private double[] forceEntreDeuxAgents (int i, int j) {
		double[][] vecteurs = this.vecteursEntreAgents(i, j);
		double d = vecteurs[0][0];
		double[] n = vecteurs[1];
		double[] t = vecteurs[2];
		double rayonTotal = this.agents[i].rayon + this.agents[j].rayon;
		double dv_t = 0;
		//dv_t = np.dot(v[j, :] - v[i, :], t)

		double a = this.A * Math.exp((rayonTotal - d) / this.B) + (this.k * this.g(rayonTotal - d));
		double b = this.kap * this.g(rayonTotal - d) * dv_t;
		
		double[] force = {(a * n[0]) + (b * t[0]), (a * n[1]) + (b * t[1])};
		return force;
	}

	private double[] forceAgents (int i) {
		double[] forceTotale = {0, 0};
		for (int j = 0; j < this.N; j++) {
			if (j != i) {
				double[] force = this.forceEntreDeuxAgents(i, j);
				forceTotale[0] += force[0];
				forceTotale[1] += force[1];
			}
		}
		return forceTotale;
	}


	public double[][] distanceAvecMur (int i, int j) {
		// temp = np.dot(r[i, :] - seg[0, :], seg[1, :] - seg[0, :]) / (np.linalg.norm(seg[1, :] - seg[0, :]))**2
		double temp = 0;
		temp = Math.min( Math.max( 0, temp), 1);
		double ppX = this.murs[j].debut[0] + (this.murs[j].fin[0] - this.murs[j].debut[0]) * temp;
		double ppY = this.murs[j].debut[1] + (this.murs[j].fin[1] - this.murs[j].debut[1]) * temp;
		double[] pointProche = {ppX, ppY};
		double xi = this.agents[i].r[0];
		double yi = this.agents[i].r[1];

		double[] d = {Math.sqrt((xi - ppX) * (xi - ppX) + (yi - ppY) * (yi - ppY))};
		double[] n = {(xi - ppX) / d[0], (yi - ppY) / d[0]};
		double[] t = {- n[0], n[1]};

		double[][] vecteurs = {d, n, t, pointProche};

		return vecteurs;
	}


	private double[] forceEntreMurEtAgent (int i, int j) {
		double[][] vecteurs = this.distanceAvecMur(i, j);
		double d = vecteurs[0][0];
		double[] n = vecteurs[1];
		double[] t = vecteurs[2];
		double dv_t = 0;
		//dv_t = np.dot(v[j, :] - v[i, :], t)

		double a = this.A * Math.exp((this.agents[i].rayon - d) / this.B) + (this.k * this.g(this.agents[i].rayon - d));
		double b = this.kap * this.g(this.agents[i].rayon - d) * dv_t;
		
		double[] force = {(a * n[0]) + (b * t[0]), (a * n[1]) + (b * t[1])};
		return force;
	}

	private double[] forceMurs (int i) {
		double[] forceTotale = {0, 0};
		for (int j = 0; j < this.murs.length; j++) {
			if (j != i) {
				double[] force = this.forceEntreMurEtAgent(i, j);
				forceTotale[0] += force[0];
				forceTotale[1] += force[1];
			}
		}
		return forceTotale;
	}


	public void calculAcceleration() {
		for (int i = 0; i < this.N; i++) {
			double[] forceAgents = this.forceAgents(i);
			double[] forceMurs = this.forceMurs(i);

			double[] vitesseDesiree = this.agents[i].v0;
			double[] v = this.agents[i].v;
			double tau = this.agents[i].tau;
			double m = this.agents[i].m;
			
			double aX, aY;
			aX = ((vitesseDesiree[0] - v[0]) / tau) + (forceAgents[0] / m) + ((forceMurs[0]) / m);
			aY = ((vitesseDesiree[1] - v[1]) / tau) + (forceAgents[1] / m) + ((forceMurs[1]) / m);
			this.agents[i].a = new double[] {aX, aY};
		}
	}


	public void euler () {
		/*
        a1 = np.zeros((len(r), 2))
        v1 = np.zeros((len(r), 2))
        r1 = np.zeros((len(r), 2))

        evacue = []

        a1 = self.f_final_Helbings(r, v)
        v1 = v + self.dt * a1
        r1 = r + self.dt * v1

        for i in range(len(r1)):
            if np.linalg.norm(r1[i, :] - self.sortie) < 2*self.ray[i]:
                r1[i, :] = np.random.rand(1, 2) * 10**6
                evacue.append(i)
                return r1, v1, evacue
		 */
	}


}
        		