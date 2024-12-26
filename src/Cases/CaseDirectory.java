package Cases;

import traitement.Repertoire;
// import traitement.Snapshot; 
import java.io.File;
import java.lang.Exception;


public class CaseDirectory extends Case {

    public void directory(String[] args) throws Exception{ 

        if (args.length == 0) {
            throw new Exception("No Arguments Exception");
        }

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
        Repertoire R = new Repertoire(directory);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new Exception("Directory Does Not Exist");
        }
        

        //case there is an option --stats or not
        if (args.length == 3) {
            if (args[2].equals("--stat")) {
                System.out.println(R.nbrImage() + "/n" + R.nbrImagesPNG() + "/n" + R.nbrImagesPNG() + "/n" + R.nbrImagesWEBP());
            } 
            else {
                if(args[2].equals("--list")) {
                    System.out.println("Les documents du répertoire " + args[1] + " :");
                    System.out.println(R);
                }
                else  {
                    if (args[2].equals("--snapshotsave")) {
                        Snapshot s = new Snapshot(args[1]);
                        s.saveSnapshot("snapshot");
                } 
                    else {
                        if(args[2].equals("--snapshotcompare")) {
                            Snapshot s = new Snapshot(args[1]);
                            Snapshot s2 = new Snapshot(args[3]);
                            File file1 = new File(args[1]);
                            File file2 = new File(args[3]);
                            System.out.println(s.compareSnapshot(file1, file2));
                        }
                        else {
                            throw new Exception("Options Does Not Exist");
                        }
                    }
                }
            }
        }
    }
}