package interfaceSimulateur2;
import javax.swing.*;

import java.awt.*;
import java.util.Observer;
import java.util.Observable;

public class Vue3 extends JFrame implements Observer{

  private JTextField  valeur;
  private JSlider     jauge;
  
  public Vue3(Nombre nombre) {
    super("Vue2 Nombre");
    nombre.addObserver(this);
    Container content = getContentPane();
    //content.setLayout(new BorderLayout()); BorderLayout par d�faut
    this.jauge = new JSlider(JSlider.HORIZONTAL, 0, nombre.VALEUR_MAX, 0);
    this.jauge.setMajorTickSpacing(10);
    this.jauge.setMinorTickSpacing(1);
    this.jauge.setValue(nombre.getValeur());
    this.jauge.setEnabled(true);
    content.add(jauge, BorderLayout.NORTH);
    
    this.valeur = new JTextField(nombre.toString(),4);
    this.valeur.setEnabled(true);
    this.valeur.setHorizontalAlignment(JTextField.RIGHT);
    this.valeur.setFont(new Font(valeur.getFont().toString(),Font.BOLD,32));

    content.add(valeur, BorderLayout.CENTER);
    pack();
    setVisible(true);
  }
  
  public void update(Observable obs, Object arg){
    if(obs instanceof Nombre){
      Nombre n = (Nombre)obs;
      this.valeur.setText(n.toString());
      this.jauge.setValue(n.getValeur());
    } 
  }
  
  public JTextField getValeur(){return valeur;}
  
  public JSlider getJauge(){return jauge;}

   
}