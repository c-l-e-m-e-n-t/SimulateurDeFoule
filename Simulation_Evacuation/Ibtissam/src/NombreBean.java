package interfaceSimulateur;
import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class NombreBean implements Serializable{

  public final int VALEUR_MIN;
  public final int VALEUR_MAX;
  private int valeur;
  private PropertyChangeSupport propertySupport;
  
  public NombreBean(){
   this.VALEUR_MIN = this.valeur = 0;
   this.VALEUR_MAX = 10;
   this.propertySupport = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(PropertyChangeListener l) {
    propertySupport.addPropertyChangeListener(l);
  }
  
  public void removePropertyChangeListener(PropertyChangeListener l) {
	  propertySupport.removePropertyChangeListener(l);
  }
  
  public void inc(){
    if(valeur < VALEUR_MAX){
      int old = valeur;
      this.valeur++;
      propertySupport.firePropertyChange("valeur",new Integer(old),new Integer(valeur));
    }
  }
  
  public void dec(){
    if(valeur > VALEUR_MIN){
      int old = valeur;
      this.valeur--;
   
      propertySupport.firePropertyChange("valeur",new Integer(old),new Integer(valeur));
    }
  }
  
  public String toString(){
    return Integer.toString(valeur);
  }
  
  public void setValeur(int val){
    if(val >= VALEUR_MIN && val <= VALEUR_MAX){
      int old = this.valeur;
      this.valeur = val;
      propertySupport.firePropertyChange("valeur",new Integer(old),new Integer(val));
    }      
  }
  
  public int getValeur(){
    return valeur;
  }
}
