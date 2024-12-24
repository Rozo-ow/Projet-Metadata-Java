package traitement;


import java.io.File;
import java.lang.Exception;

public class CaseDirectory extends Case {

    public void directory(String[] args) throws Exception{ 

        //case directory is not provided
        if (args.length == 1)
        {
            throw new Exception("Directory Is Not Provided");
        }

        //case directory is provided and there is more than one option
        if (args.length > 3)
        {
            throw new Exception("Too Much Arguments Exception");
        }

        //case directory does not exists
        File directory = new File(args[1]);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new Exception("Directory Does Not Exist");
        }
        Repertoire R = new Repertoire(directory);

        //case there is an option --stats or not
        if (args.length == 3) {
            if (!(args[2].equals("--stat")))
            {
                throw new Exception("Options Does Not Exist");
            }

        }
        

        //si -d, directory fourni et existant, et aucune options :


        System.out.println("Les documents du répertoire " + args[1] + " :");
        System.out.println(R);

        if(args.length == 3) {
            System.out.println(R.stats());
        }

        {
            throw new Exception("File or filepath does not exist");
        }

    }

}