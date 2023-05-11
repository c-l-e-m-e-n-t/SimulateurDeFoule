package interfaceSimulateur2;
import java.util.*;

public class VueConsole implements Observer{

  public VueConsole( Nombre modele){
    modele.addObserver(this);
  }
  
  public void update(Observable src, Object arg){
    System.out.println("update ! : " + arg);
  }
}
