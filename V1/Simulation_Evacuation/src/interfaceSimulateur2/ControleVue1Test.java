package interfaceSimulateur2;

public class ControleVue1Test extends junit.framework.TestCase{
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
		ControleVue1 controle1 = new ControleVue1(n, v1);
		v1.getPlus().doClick();
		assertEquals(1,n.getValeur());
		v1.getPlus().doClick();
		assertEquals(2,n.getValeur());
		v1.getMoins().doClick();
		assertEquals(1,n.getValeur());
	}
	
	public void testAvecDeuxVuesUnControleur(){
		ControleVue1 controle1 = new ControleVue1(n, v1);
		v1.getPlus().doClick();
		assertEquals(1,n.getValeur());
		v2.getPlus().doClick(); // pas de cont�leur associ�, aucun effet
		assertEquals(1,n.getValeur());
		v1.getPlus().doClick();
		assertEquals(2,n.getValeur());
		v1.getMoins().doClick();
		assertEquals(1,n.getValeur());
	}
	
	public void testAvecDeuxVuesDeuxControleurs(){
		ControleVue1 controle1 = new ControleVue1(n, v1);
		ControleVue1 controle2 = new ControleVue1(n, v2);
		v1.getPlus().doClick();
		assertEquals(1,n.getValeur());
		v2.getPlus().doClick(); 
		assertEquals(2,n.getValeur());
		v1.getPlus().doClick();
		assertEquals(3,n.getValeur());
		v1.getMoins().doClick();
		assertEquals(2,n.getValeur());
	}
}

