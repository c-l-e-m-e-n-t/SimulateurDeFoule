package interfaceSimulateur;

public class Controle{
  private Nombre modele;
  public Controle( Nombre modele){
    this.modele = modele;
  }
  
  public void incrementer(int nombre){
    for(int i = 0; i< nombre; i++){
      modele.inc();
    }
  }
}
