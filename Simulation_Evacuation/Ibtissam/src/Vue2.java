package interfaceSimulateur;
import javax.swing.*;

import java.awt.*;
import java.util.Observer;
import java.util.Observable;

public class Vue2 extends JFrame implements Observer{

  private JTextField  valeur;
  private JSlider     jauge;
  
  public Vue2(Nombre nombre) {
    super("Vue2 Nombre");
    nombre.addObserver(this);
    Container content = getContentPane();
    //content.setLayout(new BorderLayout()); BorderLayout par d�faut
    this.jauge = new JSlider(JSlider.HORIZONTAL, 0, nombre.VALEUR_MAX, 0);
    content.add(jauge, BorderLayout.NORTH);
    
    this.valeur = new JTextField(nombre.toString(),4);
    this.jauge.setValue(nombre.getValeur());
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
  
//   //public void addActionListener(ActionListener al){
//     valeur.addActionListener(al);
//   }
  public JTextField getValeur(){return valeur;}

   
}