import java.util.Observable;
import java.util.Observer;

public class NombreTest extends junit.framework.TestCase{
 
	public void testSimple(){
		Nombre nombre1 = new Nombre(0, 10);
		assertEquals(0, nombre1.VALEUR_MIN);
		assertEquals(10, nombre1.VALEUR_MAX);
		
		assertEquals(0, nombre1.getValeur());
		nombre1.inc();
		assertEquals(1, nombre1.getValeur());
		nombre1.dec();
		assertEquals(0, nombre1.getValeur());
		nombre1.setValeur(4);
		assertEquals(4, nombre1.getValeur());
		assertEquals("4", nombre1.toString());
	}
	
	private static class Observateur implements Observer{
	  private Observable obs;
	  private Object arg;
	  
	  public void update(Observable obs, Object arg){
	    this.obs = obs;
	    this.arg = arg;
	  }
	  public Observable getObs(){
	    Observable observateur = this.obs;
	    this.obs = null;
	    return observateur;
	  }
	  public Object getArg(){
	    Object object = this.arg;
	    this.arg = null;
	    return object;
	  }
    }
	
  public void  testObservable(){
	  Nombre nombre = new Nombre(0, 10);
	  
	  Observateur observateur= new Observateur();
	  nombre.addObserver(observateur);
	  nombre.setValeur(1);
	  assertEquals(nombre, observateur.getObs());
	  assertEquals(new Integer(1), observateur.getArg());
	  
  }
}

