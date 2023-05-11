package interfaceSimulateur2;

import java.util.Observable;

public class Nombre extends Observable{
  public final int VALEUR_MIN;
  public final int VALEUR_MAX;
  private int valeur;

  public Nombre(int min, int max){
   this.VALEUR_MIN = this.valeur = min;
   this.VALEUR_MAX = max;
  }

  public void inc(){
    if(valeur < VALEUR_MAX){
      this.valeur++;
      setChanged();
      notifyObservers(valeur);
    }
  }
  
  public void dec(){
    if(valeur > VALEUR_MIN){
      this.valeur--;
      setChanged();
      notifyObservers(valeur);
    }
  }
  
  public String toString(){
    return Integer.toString(valeur);
  }
  
  public void setValeur(int valeur){
    if(valeur >= VALEUR_MIN && valeur <= VALEUR_MAX){
      this.valeur = valeur;
      setChanged();
      notifyObservers(valeur);
    }      
  }
  
  public int getValeur(){
    return valeur;
  }
}
