import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

public class Statistiques {
    
    private int nbAgents;
    private int nbSorties;
    private int nbObstacles;

    private int nbAgentsSortis;

    private int nbAgentsBloques;
    private int nbAgentsMorts;
    private int nbAgentsMortsParPression;

    public Statistiques() {
        this.nbAgents = 0;
        this.nbSorties = 0;
        this.nbObstacles = 0;
        this.nbAgentsSortis = 0;
        this.nbAgentsBloques = 0;
        this.nbAgentsMorts = 0;
        this.nbAgentsMortsParPression = 0;
    }

    public void setNbAgents(int nbAgents) {
        this.nbAgents = nbAgents;
    }

    public void setNbSorties(int nbSorties) {
        this.nbSorties = nbSorties;
    }

    public void setNbObstacles(int nbObstacles) {
        this.nbObstacles = nbObstacles;
    }

    public void setNbAgentsSortis(int nbAgentsSortis) {
        this.nbAgentsSortis = nbAgentsSortis;
    }

    private void genererRapport() {
        System.out.println("Nombre d'agents : " + this.nbAgents);
        System.out.println("Nombre de sorties : " + this.nbSorties);
        System.out.println("Nombre d'obstacles : " + this.nbObstacles);
        System.out.println("Nombre d'agents sortis : " + this.nbAgentsSortis);
        System.out.println("Nombre d'agents bloqués : " + this.nbAgentsBloques);
        System.out.println("Nombre d'agents morts : " + this.nbAgentsMorts);
        System.out.println("Nombre d'agents morts par pression : " + this.nbAgentsMortsParPression);

        //générer un pdf
        PDDocument document = new PDDocument();

        try {
            PDPage page = new PDPage();
            document.addPage(page);
            int fontSize = 12;
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Rapport statistiques");
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Nombre d'agents : " + this.nbAgents);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Nombre de sorties : " + this.nbSorties);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Nombre d'obstacles : " + this.nbObstacles);
            contentStream.newLineAtOffset(0, -20);
            document.save(new File("example.pdf"));
            System.out.println("PDF généré avec succès !");
            document.close();
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
