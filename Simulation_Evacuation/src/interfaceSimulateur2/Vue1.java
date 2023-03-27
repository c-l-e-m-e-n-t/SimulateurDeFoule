package interfaceSimulateur2;
import javax.swing.*;

import java.awt.*;
import java.util.Observer;
import java.util.Observable;

public class Vue1 extends JFrame implements Observer{

  private JButton     plus;
  private JButton     moins;
  private JTextField  valeur;
  private JSlider     jauge;
  
  public Vue1(Nombre nombre) {
    super("Vue1 Nombre");
    nombre.addObserver(this);
    Container content = getContentPane();
    content.setLayout(new BorderLayout(4,4)); //0,0 par d�faut
    this.jauge = new JSlider(JSlider.VERTICAL, 0, nombre.VALEUR_MAX, 0);
    content.add(jauge, BorderLayout.WEST);
    
    JPanel boutons = new JPanel();
    boutons.setLayout(new GridLayout(0,2));
    this.plus = new JButton("+");
    boutons.add(plus);
    this.moins = new JButton("-");
    boutons.add(moins);
   
    this.valeur = new JTextField(nombre.toString(),4);
    this.jauge.setValue(nombre.getValeur());
    this.valeur.setEnabled(false);
    this.valeur.setHorizontalAlignment(JTextField.RIGHT);
    this.valeur.setFont(new Font(valeur.getFont().toString(),Font.BOLD,32));

    content.add(valeur, BorderLayout.CENTER);
    content.add(boutons, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }
  
  @Override
  public void update(Observable obs, Object arg){
    if(obs instanceof Nombre){
      Nombre n = (Nombre)obs;
      this.valeur.setText(n.toString());
      this.jauge.setValue(n.getValeur());
    } 
  }
  
  public JButton getPlus(){return plus;}
  public JButton getMoins(){return moins;}
   
}