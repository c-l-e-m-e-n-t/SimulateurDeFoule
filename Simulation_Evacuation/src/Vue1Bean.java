import javax.swing.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import java.beans.*;

public class Vue1Bean extends JFrame implements PropertyChangeListener{

  private JButton     plus;
  private JButton     moins;
  private JTextField  valeur;
  private JSlider     jauge;
  
  public Vue1Bean(NombreBean nombre) {
    super("Vue1 Nombre");
    nombre.addPropertyChangeListener(this);
    
    Container content = getContentPane();
    content.setLayout(new BorderLayout(4,4)); //0,0 par défaut
    this.jauge = new JSlider(JSlider.VERTICAL, 0, nombre.VALEUR_MAX, 0);
    content.add(jauge, BorderLayout.WEST);
    
    JPanel boutons = new JPanel();
    boutons.setLayout(new GridLayout(0,2));
    this.plus = new JButton("+");
    boutons.add(plus);
    this.moins = new JButton("-");
    boutons.add(moins);
   
    this.valeur = new JTextField(nombre.toString(),4);
    this.valeur.setEnabled(false);
    this.valeur.setHorizontalAlignment(JTextField.RIGHT);
    this.valeur.setFont(new Font(valeur.getFont().toString(),Font.BOLD,32));
    content.add(valeur, BorderLayout.CENTER);
    content.add(boutons, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt){
    assert evt.getPropertyName().equals("valeur");
    this.valeur.setText(evt.getNewValue().toString());
    this.jauge.setValue((Integer)evt.getNewValue());
  }
  
  public JButton getPlus(){return plus;}
  public JButton getMoins(){return moins;}
  
   
}