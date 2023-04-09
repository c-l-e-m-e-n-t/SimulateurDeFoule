public class main {
    //cree une fenetre
    public static void main(String[] args) {
        new FenetreSimulation();
        Statistiques stats = new Statistiques();
        stats.genererRapport();
    }
    
}