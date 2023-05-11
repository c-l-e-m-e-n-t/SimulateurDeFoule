import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/** Lancer le simulateur. */
public class Simulateur {

	private JFrame fenetre;
	private boolean accesMenu = true;

	public Simulateur() {
		// Créer une nouvelle fenetre
		fenetre = new JFrame("Simulation d'évacuation");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(1080, 720);
		fenetre.setLocationRelativeTo(null);

		// Créer le container de la fenetre      
		Container contenu = fenetre.getContentPane();   
		contenu.setLayout(new BorderLayout());
		       
		// Ajouter un bandeau en bas de la page pour afficher les auteurs du logiciel    
		JPanel bandeauBas = new JPanel(); 
		bandeauBas.setBackground(Color.GRAY);   
		fenetre.add(bandeauBas, BorderLayout.SOUTH);     
		JLabel auteur = new JLabel("N7 - de Brandois Félix - Cognard Clément - "
				+ "El Guerraoui Oussama - Fresco Alan - Mimoun Ibtissam - "
				+ "Fraine Sofiane - Murugesapillai Vithursan - 2023");   
		bandeauBas.add(auteur);

		// Afficher le logo
		ImageIcon icon = new ImageIcon("Mon projet.jpg");
        JLabel label = new JLabel(icon);
        contenu.add(label, BorderLayout.CENTER);

        // Changer de page si une touche est pressée
        fenetre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	if (accesMenu) {
                    // Supprimer les label contenant l'image
                    contenu.remove(label);
                    // Redessiner le contenu de la fenêtre
                    fenetre.revalidate();
                    fenetre.repaint();
                	// Acceder au menu
                	new MenuSimulateur(fenetre);
                	accesMenu = false;
            	}

            }
        });

		// Rendre la fentre visible   
		fenetre.setVisible(true);
		// Donner le focus à la fenêtre pour détecter les touches pressées
		fenetre.requestFocus();
	}

	public static void main(String[] args) {
		new Simulateur();
	}

}
