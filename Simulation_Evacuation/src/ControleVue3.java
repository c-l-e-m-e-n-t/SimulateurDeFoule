import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class ControleVue3{
  private Nombre nombre;
  private Vue3   vue3;

  public ControleVue3(Nombre nombre, Vue3 vue3){
    this.nombre = nombre;
    this.vue3 = vue3;
    
    vue3.getValeur().addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          try{
            int valeur = Integer.parseInt(ControleVue3.this.vue3.getValeur().getText());
            ControleVue3.this.nombre.setValeur(valeur);
          }catch(NumberFormatException nfe){
          }

        }
      });
      
    vue3.getJauge().addChangeListener(
      new ChangeListener(){
        public void stateChanged(ChangeEvent ce){
          JSlider source = (JSlider)ce.getSource();
          int valeur = (int)source.getValue();
          ControleVue3.this.nombre.setValeur(valeur);
        }
      });
  }
}
