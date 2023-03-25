import java.awt.event.*;

public class ControleVue1Bean{
  private NombreBean nombre;

  public ControleVue1Bean(NombreBean nombre, Vue1Bean vue){
    this.nombre = nombre;
    vue.getMoins().addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          ControleVue1Bean.this.nombre.dec();
        }
      });

    vue.getPlus().addActionListener(      
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          ControleVue1Bean.this.nombre.inc();
        }
      });

  }
}
