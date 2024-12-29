package Cases;

import traitment.Repertoire;
import traitment.Snapshot; 
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
        if (args.length > 4)
        {
            throw new Exception("Too Much Arguments Exception");
        }

        //case directory does not exists
        File directory = new File(args[2]);
        Repertoire R = new Repertoire(directory);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new Exception("Directory " + args[2] +" Does Not Exist");
        }
        

        //case there is an option --stats or not
        if (args.length == 3) {
            if (args[1].equals("--stat")) {
                System.out.println(R.nbrImage() + "/n" + R.nbrImagesPNG() + "/n" + R.nbrImagesPNG() + "/n" + R.nbrImagesWEBP());
            } 
            else {
                if(args[1].equals("--list")) {
                    System.out.println("Les documents du répertoire " + args[1] + " :");
                    System.out.println(R);
                }
                else  {
                    if (args[1].equals("--snapshotsave")) {
                        Snapshot s = new Snapshot(args[1]);
                        s.saveSnapshot(args[1]);
                } 
                    else {
                        if(args[1].equals("--snapshotcompare")) {
                            Snapshot s = new Snapshot(args[1]);
                            Snapshot s2 = new Snapshot(args[4]);
                            File file1 = new File(args[1]);
                            // a reparer
                            File file2 = new File(args[4]);
                            System.out.println(Snapshot.compareSnapshot(file1, file2));
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