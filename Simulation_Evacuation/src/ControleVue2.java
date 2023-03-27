import java.awt.event.*;

public class ControleVue2{
  private Nombre nombre;
  private Vue2   vue2;

  public ControleVue2(Nombre nombre, Vue2 vue2){
    this.nombre = nombre;
    this.vue2 = vue2;
    
    vue2.getValeur().addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          try{
            int valeur = Integer.parseInt(ControleVue2.this.vue2.getValeur().getText());
            ControleVue2.this.nombre.setValeur(valeur);
          }catch(NumberFormatException nfe){
          }

        }
      });
  }
}
