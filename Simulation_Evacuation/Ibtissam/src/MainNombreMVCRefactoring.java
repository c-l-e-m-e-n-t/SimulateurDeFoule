package interfaceSimulateur;

public class MainNombreMVCRefactoring{

  public static void main(String[] args){
    Nombre nombre = new Nombre(0,10); // le mod�le
    Controle1 controle1 = new Controle1(nombre);
    VueR1 vue1 = new VueR1(nombre, controle1); 
    VueR1 vue2 = new VueR1(nombre, controle1); 
   
    VueR3 vue3 = new VueR3(nombre, controle1); 
  }
}
