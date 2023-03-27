import java.lang.reflect.Field;
import javax.swing.JTextField;

public class Vue1Test extends junit.framework.TestCase{

 private Nombre n;
  private Vue1 v1,v2;
  
  protected void setUp(){
    this.n = new Nombre(0,10);
		this.v1 = new Vue1(n);
		this.v2 = new Vue1(n);	
  }
  
  protected void tearDown(){
		this.v1.dispose();
		this.v2.dispose();
  }

	public void testSimple(){
		assertNotNull(v1.getMoins());
		assertNotNull(v1.getPlus());
		
		int v = 3;
		n.setValeur(v);
		try{  // test en boîte blanche...
		  Field field = Vue1.class.getDeclaredField("valeur");
		  field.setAccessible(true);
		  JTextField valeur = (JTextField) field.get(v1);
		  assertEquals(Integer.toString(v), valeur.getText().trim());
    }catch(Exception e){
     fail();
   }
	}
	
	public void testAvecDeuxVues(){

		assertNotNull(v1.getMoins());
		assertNotNull(v1.getPlus());
		
		int v = 3;
		n.setValeur(v);
		try{
		  Field field = Vue1.class.getDeclaredField("valeur");
		  field.setAccessible(true);
		  JTextField valeur = (JTextField) field.get(v1);
		  assertEquals(Integer.toString(v), valeur.getText().trim());
		  
    }catch(Exception e){
     fail();
   }
	}
}


