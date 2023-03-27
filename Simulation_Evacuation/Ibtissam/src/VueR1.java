package interfaceSimulateur;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

public class VueR1 extends JFrame implements Observer{

  private JButton     plus;
  private JButton     moins;
  private JTextField  valeur;
  private JSlider     jauge;
  private ControleurI controleur;
  
  public VueR1(Nombre nombre, ControleurI controleur) {
    super("Vue refactoring Nombre");
    nombre.addObserver(this);
    this.controleur = controleur;
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
    
    this.moins.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          VueR1.this.controleur.dec();
        }
      });

    this.plus.addActionListener(      
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          VueR1.this.controleur.inc();
        }
      });
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
  
   
}