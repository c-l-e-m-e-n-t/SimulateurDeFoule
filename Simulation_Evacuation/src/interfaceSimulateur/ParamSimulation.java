public class ParamSimulation {

    private int masseMin;
    private int masseMax;
    private int vitesseMin;
    private int vitesseMax;
    private int rayonMin;
    private int rayonMax;

    public ParamSimulation() {
        this.masseMin = 0;
        this.masseMax = 100;
        this.vitesseMin = 2;
        this.vitesseMax = 8;
        this.rayonMin = 5;
        this.rayonMax = 50;
    }

    /** Obtenir la masse minimale
     * @return la masse minimale
     */
    public int getMasseMin() {
        return masseMin;
    }

    /** Obtenir la masse maximale
     * @return la masse maximale
     */
    public int getMasseMax() {
        return masseMax;
    }

    /** Obtenir la vitesse minimale
     * @return la vitesse minimale
     */
    public int getVitesseMin() {
        return vitesseMin;
    }

    /** Obtenir la vitesse maximale
     * @return la vitesse maximale
     */
    public int getVitesseMax() {
        return vitesseMax;
    }

    /** Obtenir le rayon minimale
     * @return le rayon minimale
     */
    public int getRayonMin() {
        return rayonMin;
    }

    /** Obtenir le rayon maximale
     * @return le rayon maximale
     */
    public int getRayonMax() {
        return rayonMax;
    }

    /** Redéginir la masse minimale
     * @param masseMin
     */
    public void setMasseMin(int masseMin) {
        this.masseMin = masseMin;
    }

    /** Redéfinir la masse maximale
     * @param masseMax
     */
    public void setMasseMax(int masseMax) {
        this.masseMax = masseMax;
    }

    /** Redéfinir la vitesse minimale
     * @param vitesseMin
     */
    public void setVitesseMin(int vitesseMin) {
        this.vitesseMin = vitesseMin;
    }

    /** Redéfinir la vitesse maximale
     * @param vitesseMax
     */
    public void setVitesseMax(int vitesseMax) {
        this.vitesseMax = vitesseMax;
    }

    /** Redéfinir le rayon minimale
     * @param rayonMin
     */
    public void setRayonMin(int rayonMin) {
        this.rayonMin = rayonMin;
    }

    /** Redéfinir le rayon maximale
     * @param rayonMax
     */
    public void setRayonMax(int rayonMax) {
        this.rayonMax = rayonMax;
    }

    
}
