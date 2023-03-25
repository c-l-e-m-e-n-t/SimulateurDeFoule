import java.util.*;

public class VueConsole implements Observer{

  public VueConsole( Nombre modèle){
    modèle.addObserver(this);
  }
  
  public void update(Observable src, Object arg){
    System.out.println("update ! : " + arg);
  }
}
