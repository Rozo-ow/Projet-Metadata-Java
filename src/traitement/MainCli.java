package traitement ;

import java.util.Scanner;

public class MainCli{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demander à l'utilisateur de saisir une chaîne
        System.out.print("Veuillez entrer une chaîne de caractères : ");
        String userInput = scanner.nextLine();
        scanner.close();
        if(userInput.contains(".")){
            MetadataExtractor i = new MetadataExtractor(userInput);
            System.out.println(i.getMetadata());
        }
        else {
            Repertoire r = new Repertoire(userInput);
        } 
    }



    
}
