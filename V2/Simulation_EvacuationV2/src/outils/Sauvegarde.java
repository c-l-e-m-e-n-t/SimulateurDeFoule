package outils;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;

import javax.swing.*;

import affichage.SimulationData;
import affichage.Menu.DrawingPanel;
import modele.Agent;
import modele.Point;
import modele.Segment;

public class Sauvegarde {
    
    /** Création de la fenetre permettant a rentrer les informations de la sauvegarde*/
    public static void sauvegarder() {
        //déclarations
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton saveButton = new JButton("Sauvegarder");
        JLabel labelSaveName = new JLabel("Nom de la sauvegarde : ");
        JLabel labelSaveDescription = new JLabel("Description de la sauvegarde : ");
        JLabel labelAuteur = new JLabel("Nom de l'auteur :");

        JTextField saveName = new JTextField("");
        JTextArea saveDescription = new JTextArea(" ");
        JTextField auteur = new JTextField("");

        //graphismes de la fenetre
        panel.setLayout(new GridLayout(7, 1));
        panel.add(labelSaveName);
        panel.add(saveName);
        
        panel.add(labelAuteur);
        panel.add(auteur);

        panel.add(labelSaveDescription);
        panel.add(saveDescription);

        panel.add(saveButton);

        frame.getContentPane().add(panel);
        frame.setSize(400, 550);
        frame.setVisible(true);

        // Action listener pour le bouton de sauvegarde
        saveButton.addActionListener(e -> {
            String name = saveName.getText();
            String description = saveDescription.getText();
            String author = auteur.getText();

            if (name.equals("")) {
                JOptionPane.showMessageDialog(frame, "Veuillez remplir le nom du fchier.");
            } else {
                enregistrer(name, description, author);
                frame.dispose();
            }
        });

    }

    /** Enregistrement des données dans le fichier
     * @param name nom du fichier choisi pour l'enregistrement
     * @param description description de la sauvegarde donnée par l'utilisateur
     * @param author nom de l'auteur de la sauvegarde
     */
    private static void enregistrer(String name, String description, String author){
        String path = "./saves/" + name + ".n7";
        try {
            //création du fichier
            FileWriter fileWriter = new FileWriter(path, false);
            BufferedWriter writer = new BufferedWriter (fileWriter);

            //écriture dans le fichier
            writer.write("Auteur :" + author);
            writer.newLine();
            writer.write("Description :" + description);
            writer.newLine();
            writer.write("Agents :");
            writer.newLine();
            //ajouts de tous les agents et de leurs parametres dans le fichier
            for (Agent agent : SimulationData.agents) {
                if (agent != null) {
                    writer.write(agent.getPosition().getX() + ";" + agent.getPosition().getY() + ";" + agent.getSortie().getX() + ";" + agent.getSortie().getY() + ";" + agent.getVitesseDesiree() + ";" + agent.getRayon() + ";" + agent.getMasse() + ";" + agent.getTau() + ";" + agent.getCouleur().getRed() + ";" + agent.getCouleur().getGreen() + ";" + agent.getCouleur().getBlue());
                    writer.newLine();
                }
            }
            writer.write("Murs :");
            writer.newLine();
            //ajouts de tous les murs et de leurs parametres dans le fichier
            for (Segment mur : SimulationData.murs) {
                writer.write(mur.getExtremite1().getX()+ ";" + mur.getExtremite1().getY() + ";" +  mur.getExtremite2().getX() + ";" + mur.getExtremite2().getY());
                writer.newLine();
            }
            writer.write("Sortie :");
            writer.newLine();
            //ajouts de toutes les sorties dans le fichier
            for (Point sortie : SimulationData.sortie) {
                writer.write(sortie.getX() + ";" + sortie.getY());
                writer.newLine();
            }
            writer.close();
        }catch (IOException e) {
        
            e.printStackTrace();
        }
    }

    /** Création de la fenetre permettant a rentrer les informations de la sauvegarde
     * @param DrawingPanel panel de dessin pour pouvoir refresh la page une fois les éléments chargés
    */
    public static void charger(JPanel DrawingPanel) {
        //déclarations
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton loadButton = new JButton("Charger");
        JLabel labelSaveName = new JLabel("Nom de la sauvegarde : ");
        JTextField saveName = new JTextField("");

        //graphismes de la fenetre
        panel.setLayout(new GridLayout(3, 1));
        panel.add(labelSaveName);
        panel.add(saveName);

        panel.add(loadButton);

        frame.getContentPane().add(panel);
        frame.setSize(400, 550);
        frame.setVisible(true);

        // Action listener pour le bouton de chargement
        loadButton.addActionListener(e -> {
            String name = saveName.getText();
            if (name.equals("")) {
                JOptionPane.showMessageDialog(frame, "Veuillez remplir le nom du fchier.");
            } else {
                chargement(name);
                frame.dispose();
                DrawingPanel.repaint();
            }
        });
    }

    /** Chargement des données depuis le fichier
     * @param saveName nom du fichier  de la save à charger
     */
    private static void chargement(String saveName){
        String path = "./saves/" + saveName + ".n7";

        try{
            //lecteur du fichier
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);

            //lecture du fichier
            String line = reader.readLine();
            String[] lineSplit;
            String author;
            String etat  = "";
            while(line != null){
                switch (etat){
                    case "Agents": //ajout des agents
                        if (line.contains("Murs")){
                            break;
                        }
                        lineSplit = line.split(";");
                        Point position = new Point(Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1]));
                        Point sortie = new Point(Double.parseDouble(lineSplit[2]), Double.parseDouble(lineSplit[3]));
                        double vitesse = Double.parseDouble(lineSplit[4]);
                        double rayon = Double.parseDouble(lineSplit[5]);
                        double masse = Double.parseDouble(lineSplit[6]);
                        double tau = Double.parseDouble(lineSplit[7]);
                        Color couleur = new Color(Integer.parseInt(lineSplit[8]), Integer.parseInt(lineSplit[9]), Integer.parseInt(lineSplit[10]));
                        SimulationData.addAgent(new Agent(position, sortie, vitesse, rayon, masse, tau, couleur));
                        break;
                    case "Murs": //ajout des murs
                        if (line.contains("Sortie")){
                            break;
                        }
                        lineSplit = line.split(";");
                        Point p1 = new Point(Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1]));
                        Point p2 = new Point(Double.parseDouble(lineSplit[2]), Double.parseDouble(lineSplit[3]));
                        SimulationData.addMur(new Segment(p1, p2));
                        break;
                    case "Sortie": //ajout des sorties
                        if (line == null){
                            break;
                        }
                        lineSplit = line.split(";");
                        SimulationData.addSortie(Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1]));
                        break;
                    default:
                        break;
                }
                //changement de type de donnée
                if (line.contains("Agents")){
                    etat = "Agents";
                }else if (line.contains("Murs")){
                    etat = "Murs";
                }else if (line.contains("Sortie :")){
                    etat = "Sortie";
                }
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e) {
        
            e.printStackTrace();
        }
    }
}
