package interfaceSimulateur;

public class MainNombreMVC{

  public static void main(String[] args){
    Nombre nombre = new Nombre(0,10); // le mod�le
    
    Vue2 vue22 = new Vue2(nombre); 
 ControleVue2 controle22 = new ControleVue2(nombre, vue22);
 
    
    Vue1 vue1 = new Vue1(nombre); 
    Vue1 vue2 = new Vue1(nombre); 
    Vue1 vue3 = new Vue1(nombre); 
    Vue1 vue4 = new Vue1(nombre); 
    Vue1 vue5 = new Vue1(nombre); 
    Vue1 vue6 = new Vue1(nombre); 
    
    ControleVue1 controle1 = new ControleVue1(nombre, vue1);
     ControleVue1 controle2 = new ControleVue1(nombre, vue2);
     ControleVue1 controle3 = new ControleVue1(nombre, vue3);
     ControleVue1 controle4 = new ControleVue1(nombre, vue4);
    
    
//     Vue1 vue1Bis = new Vue1(nombre);     
//     Vue1 vue12 = new Vue1(nombre);     
//     Vue1 vue13 = new Vue1(nombre);     
//     
//     
//     ControleVue1 controle1Bis = new ControleVue1(nombre, vue1Bis);
//  
//    // Vue1 vue1Bis = new Vue1(nombre);       
//     //ControleVue1 controle1Bis = new ControleVue1(nombre, vue1Bis);
//     
// //     
// //     Vue2 vue2 = new Vue2(nombre); 
// //     ControleVue2 controle2 = new ControleVue2(nombre, vue2);
// //     Vue1 vue11 = new Vue1(nombre);
// //     ControleVue1 controle11 = new ControleVue1(nombre, vue11);
// //     
// //     Nombre nombreBis = new Nombre(0,100); // le mod�le
// //     Vue1 vueBis = new Vue1(nombreBis);       
// //     ControleVue1 controle = new ControleVue1(nombreBis, vueBis);
// 
//    // NombreBean nombreTer = new NombreBean(); // le mod�le
//    // Vue1Bean vueTer = new Vue1Bean(nombreTer); 
      // ControleVue1Bean controleTer = new ControleVue1Bean(nombreTer, vueTer);

  }
}
