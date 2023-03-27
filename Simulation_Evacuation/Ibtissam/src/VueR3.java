package interfaceSimulateur;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

public class VueR3 extends JFrame implements Observer{

  private JTextField  valeur;
  private JSlider     jauge;
  private ControleurI  controleur;
  
  public VueR3(Nombre nombre, ControleurI controleur) {
    super("Vue3 refactoring Nombre");
    nombre.addObserver(this);
    this.controleur = controleur;
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
    
    this.valeur.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          try{
            int valeur = Integer.parseInt(VueR3.this.valeur.getText());
            VueR3.this.controleur.setValeur(valeur);
          }catch(NumberFormatException nfe){
          }

        }
      });
      
    this.jauge.addChangeListener(
      new ChangeListener(){
        public void stateChanged(ChangeEvent ce){
          JSlider source = (JSlider)ce.getSource();
          int valeur = (int)source.getValue();
          VueR3.this.controleur.setValeur(valeur);
        }
      });
      
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
  
}