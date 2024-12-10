import org.apache.commons.cli.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Terminal {
    private CommandLine cmd;

    public void optionHandeler(String[] args) {
        /**
         * the parser Object
         */
        CommandLineParser parser = new DefaultParser();
        /**
         * the Options Object
         */
        Options options = new Options();

        // adding options 
        options.addOption("h", "help", false, "affiche les options disponibles");
        options.addOption("f", "file", true, "spécifie le fichier à traiter");
        options.addOption("d", "directory", true, "spécifie le répertoire à traiter");
        options.addOption(Option.builder()
                .longOpt("type")
                .desc("vérifier le type MIME")
                .build());

    }    
}
