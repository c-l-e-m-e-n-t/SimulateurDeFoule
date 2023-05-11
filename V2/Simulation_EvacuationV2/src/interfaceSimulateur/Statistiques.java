package interfaceSimulateur;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.pdmodel.font.PDType1Font;



public class Statistiques {
    
    private int nbAgents;
    private int nbSorties;
    private int nbObstacles;
    private int nbAgentsSortis;
    private int nbAgentsBloques;
    private int nbAgentsMorts;
    private int nbAgentsMortsParPression;
    private String titre;
    private String description;

    public Statistiques() {
        this.nbAgents = 0;
        this.nbSorties = 0;
        this.nbObstacles = 0;
        this.nbAgentsSortis = 0;
        this.nbAgentsBloques = 0;
        this.nbAgentsMorts = 0;
        this.nbAgentsMortsParPression = 0;
        this.titre = "Rapport";
        this.description = "Rapport de la simulation";
    }

    /** Ajouter un agent sorti
     * 
     */
    public void addAgentSortis() {
        this.nbAgentsSortis++;
    }

    private void majparam() {
        this.nbAgents = ParamSimulation.getNbAgents();
        this.nbSorties = ParamSimulation.getNbSorties();
        this.nbObstacles = ParamSimulation.getNbObstacles();
        this.nbAgentsBloques = this.nbAgents - this.nbAgentsSortis - this.nbAgentsMorts;

    }

    public void genererRapport() {
        //générer un pdf
        majparam();
        PDDocument document = new PDDocument();
        try {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDType1Font font = PDType1Font.HELVETICA_BOLD;
            int fontSize = 12;
            contentStream.setFont(font, fontSize);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Rapport");
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre d'agents : " + this.nbAgents);
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre de sorties : " + this.nbSorties);
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre d'obstacles : " + this.nbObstacles);
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre d'agents sortis : " + this.nbAgentsSortis);
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre d'agents bloqués : " + this.nbAgentsBloques);
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre d'agents morts : " + this.nbAgentsMorts);
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre d'agents morts par pression : " + this.nbAgentsMortsParPression);
            contentStream.endText();
            contentStream.close();
            document.save(new File("Rapport.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

