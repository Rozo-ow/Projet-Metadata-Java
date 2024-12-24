package traitement ;

import java.io.File;
import java.util.Scanner;

public class MainCli{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demander à l'utilisateur de saisir une chaîne
        System.out.print("Veuillez entrer une chaîne de caractères : ");
        String userInput = scanner.nextLine();
        scanner.close();
        if(userInput.contains(".")){
            try {
                MetadataExtractor i = new MetadataExtractor(userInput);
                System.out.println(i.getMetadata());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            File directory = new File(userInput);
            if (!directory.exists() || !directory.isDirectory()) {
                System.err.println("Directory doe not exist.");//throw new Exception("Directory Does Not Exist");
            }
            Repertoire r = new Repertoire(directory);
        } 
    }



    
}
