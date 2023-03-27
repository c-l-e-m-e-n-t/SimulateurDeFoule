import java.awt.event.*;

public class ControleVue1{
  private Nombre nombre;

  public ControleVue1(Nombre nombre, Vue1 vue){
    this.nombre = nombre;
    vue.getMoins().addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          ControleVue1.this.nombre.dec();
        }
      });

    vue.getPlus().addActionListener(      
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          ControleVue1.this.nombre.inc();
        }
      });

  }
}
