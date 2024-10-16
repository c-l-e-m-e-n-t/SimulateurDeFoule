# Simula7ion

Simula7ion est un projet de simulation de foule développé en Java. Ce projet permet de simuler différents scénarios et de visualiser les résultats à travers une interface graphique.

## Structure du projet

- `docs/` : Documentation du projet.
- `img/` : Images utilisées dans le projet.
- `lib/` : Bibliothèques externes utilisées par le projet.
  - `pdfbox-app-2.0.27.jar` : Bibliothèque pour la manipulation de fichiers PDF.
- `saves/` : Dossiers contenant les fichiers de sauvegarde des simulations.
- `Simula7ion.jar` : Fichier exécutable du projet.
- `src/` : Code source du projet.
  - `affichage/` : Contient les classes pour l'affichage et l'interface utilisateur.
    - `Accueil.java` : Classe pour l'écran d'accueil.
    - `Menu.java` : Classe pour le menu principal.
    - `MenuAgents.java` : Classe pour le menu des agents.
    - `MenuBar.java` : Classe pour la barre de menu.
    - `MenuObstacles.java` : Classe pour le menu des obstacles.
    - `MenuPlacementManuel.java` : Classe pour le menu de placement manuel.
    - `Simulation.java` : Classe principale de simulation.
    - `SimulationData.java` : Classe pour les données de simulation.
  - `design/` : Contient les classes pour le design de l'interface utilisateur.
    - `ButtonDesign.java` : Classe pour le design des boutons.
    - `ButtonDesignPetit.java` : Classe pour le design des petits boutons.
    - `MenuPanel.java` : Classe pour le panneau de menu.
    - `ToggleButtonDesign.java` : Classe pour le design des boutons à bascule.
    - `ToggleButtonDesignPetit.java` : Classe pour le design des petits boutons à bascule.
  - `modele/` : Contient les classes pour le modèle de simulation.
  - `outils/` : Contient les classes utilitaires.
  - `pheno/` : Contient les classes pour les phénomènes simulés.
  - `testPackage/` : Contient les classes de test.

## Prérequis

- Java 8 ou supérieur
- [Apache PDFBox](https://pdfbox.apache.org/) (inclus dans `lib/`)

## Compilation et exécution

Pour compiler et exécuter le projet, utilisez les commandes suivantes :

```sh
javac {argument de compilations} # pour compiler
java {nom que vous avez donné au projet} # pour lancer le projet
```


## Utilisation

1. **Lancer l'application** : Double-cliquez sur `Simula7ion.jar` ou utilisez la commande `java -jar Simula7ion.jar` dans le terminal.
2. **Naviguer dans les menus** : Utilisez les différents menus pour configurer votre simulation.
3. **Démarrer une simulation** : Configurez les paramètres souhaités et cliquez sur le bouton de démarrage pour lancer la simulation.
4. **Visualiser les résultats** : Observez les résultats de la simulation à travers l'interface graphique.

## Auteurs

Cognard Clément  
de Brandois Félix  
El Guerraoui Oussama  
Fresco Alan Mimoun Ibtissam  
Fraine Sofiane  
Murugesapillai Vithursan
