package traitment ;
import Cases.* ;
import Cases.CaseFile;


public class MainCli {
    public static final void main(String[] args) {
        // Vérification si des arguments ont été fournis
        if (args.length == 0) {
            System.out.println("Veuillez fournir au moins un argument.");
            System.out.println("Pour obtenir de l'aide, utilisez : java cases.Main help");
            return;
        }

        // Récupération du premier argument (commande)
        String command = args[0];

        // Traitement des différentes commandes possibles
        switch (command) {
            case "-h":
            case "--help":
                CaseHelp ch = new CaseHelp();
                ch.help() ;
                System.out.println("Liste des commandes disponibles :");
                System.out.println("- help : Affiche cette aide.");
                // ... ajouter d'autres commandes et leurs descriptions
                break;
            case "-d":
            case "--directory":
            try {
                CaseDirectory caseDirectory = new CaseDirectory();
                caseDirectory.directory(args);
            } catch (Exception e) {
                System.out.println("An error occurred while processing the directory: " + e.getMessage());
                e.printStackTrace();
            }
                break;
            case "-f":
            case "--file":
                try {
                    CaseFile caseFile = new CaseFile();
                    caseFile.file(args);

                } catch (Exception e) {
                    System.out.println("An error occurred while processing the file: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Commande inconnue : " + command);
                break;
        }
    }
}