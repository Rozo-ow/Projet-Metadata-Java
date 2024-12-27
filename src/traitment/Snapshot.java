package traitment;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Cette classe permet de creer un snapshot d'un repertoire et de le comparer à un autre snapshot.
 * 
 * Un snapshot est un fichier texte contenant la liste des fichiers d'un repertoire avec leur date de modification.
 */
public class Snapshot {
   private Repertoire rep;

   // Setter & Getter
    public Repertoire getRep() {
        return rep;
    }

    public void setRep(Repertoire rep) {
        this.rep = rep;
    }


    /**
     * Constructeur par defaut de la classe Snapshot.
     *
     * @param path Le chemin du repertoire à prendre en snapshot.
     */
    public Snapshot(String path) {
        File directory = new File(path);
            if (!directory.exists() || !directory.isDirectory()) {
                System.err.println("Directory doe not exist.");
            }
            rep = new Repertoire(directory);
    }

    /**
     * Methode permettant de sauvegarder un snapshot dans un fichier texte.
     * 
     * Le Snapshot est un fichier texte au format [nom_fichier],[date_modification]. A noter que la date de modification est au format timestamp UNIX.
     * 
     * @param fileName Le nom du fichier dans lequel sauvegarder le snapshot.
     * @throws IOException Si une erreur survient lors de l'ecriture du fichier.
     */
    public void saveSnapshot(String fileName) throws IOException {
        fileName = rep.getNom() + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(rep.toString());
        writer.close();
    }

    /**
     * Methode permettant de comparer deux snapshots et de retourner le nombre de modifications.
     * 
     * @param snap1 Le premier snapshot.
     * @param snap2 Le deuxieme snapshot.
     * @return Le nombre de modifications entre les deux snapshots.
     */
    public static int compareSnapshot(File snap1, File snap2) {
         Map<String, Long> fichier1Map = new HashMap<>();
        Map<String, Long> fichier2Map = new HashMap<>();

        // on lit le premier snapshot en le mappant avec le nom du fichier en tant que clef, et la date de modification en tant que valeur
        try (BufferedReader br = new BufferedReader(new FileReader(snap1))) {
            String line;
            while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            fichier1Map.put(parts[0], Long.parseLong(parts[1]));
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + snap1.getName() + ": " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format de nombre dans le fichier " + snap1.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        // on lit le deuxieme snapshot en le mappant avec le nom du fichier en tant que clef, et la date de modification en tant que valeur
        try (BufferedReader br = new BufferedReader(new FileReader(snap2))) {
            String line;
            while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            fichier2Map.put(parts[0], Long.parseLong(parts[1]));
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + snap2.getName() + ": " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format de nombre dans le fichier " + snap2.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        // on compare les deux maps, en incrémentant le nombre de modifications à chaque fois qu'on trouve une différence.
        int modifications = 0;
        for (Map.Entry<String, Long> entry : fichier1Map.entrySet()) {
            if (!fichier2Map.containsKey(entry.getKey()) || !fichier2Map.get(entry.getKey()).equals(entry.getValue())) {
                modifications++;
            }
        }
        for (Map.Entry<String, Long> entry : fichier2Map.entrySet()) {
            if (!fichier1Map.containsKey(entry.getKey())) {
                modifications++;
            }
        }

        return modifications;
    }

    
    // Exemple d'utilisations avec deux snapshots creees precedemment. Ici j'utilise des chemins absolus, mais on peut aussi utiliser des chemins relatifs.
    public static void main(String[] args) {
        File snap1 = new File("C:\\Users\\robin\\Desktop\\rep\\Projet-Metadata-Java\\algoC.txt");
        File snap2 = new File("C:\\Users\\robin\\Desktop\\rep\\Projet-Metadata-Java\\snapshot.txt");
        int modifications = compareSnapshot(snap1, snap2);
        System.out.println("Nombre de modifications detectees : " + modifications);
    }
    
    

}
