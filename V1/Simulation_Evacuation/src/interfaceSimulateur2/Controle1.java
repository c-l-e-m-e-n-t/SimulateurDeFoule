package interfaceSimulateur2;

public class Controle1 implements ControleurI{
  private Nombre nombre;

  public Controle1(Nombre nombre){
    this.nombre = nombre;
  }
  public void inc(){
    nombre.inc();
  }
  public void dec(){
    nombre.dec();
  }
  
  public void setValeur(int i){
    nombre.setValeur(i);
  }
}
