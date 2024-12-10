import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Snapshot {
   private String path ;

    public void saveSnapshot(String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier de sauvegarde");
        }
    }

    public Snapshot restoreSnapshot(String path) {
        try {
            BufferedReader br = new BufferedReader (new FileReader(path));
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du fichier de sauvegarde");
        }
        return this;
    }

}
