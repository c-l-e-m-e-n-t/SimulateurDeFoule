
public class Controle{
  private Nombre modèle;
  public Controle( Nombre modèle){
    this.modèle = modèle;
  }
  
  public void incrémenter(int nombre){
    for(int i = 0; i< nombre; i++){
      modèle.inc();
    }
  }
}
