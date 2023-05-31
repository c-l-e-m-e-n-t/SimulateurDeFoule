package pheno;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import javax.swing.JPanel;

public class Incendie extends JPanel implements Runnable {

private final int largeur = 500;

private final int hauteur = 500;

private final int taille_cellule = 5;

private final int coloration = 255;

private final int[][] cellule = new int[largeur / taille_cellule][hauteur / taille_cellule];

private final Color[] colors = new Color[coloration + 1];

private boolean estEnFeu = false;

private Thread thread;

public Incendie() {

JFrame image = new JFrame("Incendie");

image.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

image.setSize(largeur, hauteur);

image.setResizable(false);

image.setLocationRelativeTo(null);

image.setContentPane(this);

image.addMouseListener(new MouseAdapter() {


@Override

public void mouseClicked(MouseEvent e) {

int x_origine = e.getX() / taille_cellule;

int y_origine = e.getY() / taille_cellule;

if (x_origine >= 0 && x_origine < cellule.length && y_origine >= 0 && y_origine < cellule[0].length) {

if (!estEnFeu) {

estEnFeu = true;

cellule[x_origine][y_origine] = coloration;

thread = new Thread(Incendie.this);

thread.start();

} else {

estEnFeu = false;

cellule[x_origine][y_origine] = 255;

}

repaint();

}

}

});

image.setVisible(true);

initialisation_couleur();

}

private void initialisation_couleur() {

for (int i = 0; i <= coloration; i++) {

colors[i] = new Color(255, i, 0);

}

}


private void propagation_feu() {

for (int x = 0; x < cellule.length; x++) {

for (int y = 0; y < cellule[0].length; y++) {

if (cellule[x][y] > 0) {

int nx = x + (int) (Math.random() * 3) - 1;

int ny = y + (int) (Math.random() * 3) - 1;


if (nx >= 0 && nx < cellule.length && ny >= 0 && ny < cellule[0].length && cellule[nx][ny] < cellule[x][y]) {

cellule[nx][ny] = cellule[x][y] - (int) (Math.random() * 2);

}

}

}

}

}


@Override

public void paintComponent(Graphics g) {

super.paintComponent(g);

for (int i = 0; i < cellule.length; i++) {

for (int j = 0; j < cellule[0].length; j++) {

g.setColor(colors[cellule[i][j]]);

g.fillRect(i * taille_cellule, j * taille_cellule, taille_cellule, taille_cellule);

}

}

}

@Override

public void run() {

while (estEnFeu) {

propagation_feu();

repaint();

try {

Thread.sleep(150);

} catch (InterruptedException e) {

e.printStackTrace();

}

}

}

public static void main(String[] args) {

new Incendie();

}

}
