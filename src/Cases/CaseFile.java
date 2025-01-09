package Cases;
import traitment.*;

public class CaseFile extends Case {

    public void file(String[] args) throws Exception{
        //---------------------------------------------------------------------
        //checking if the file or filepath is provided
        //---------------------------------------------------------------------
        //checking if the file or filepath is provided
        if(args.length == 1)
        {
            throw new Exception("No file or filepath Exception");
        }

        //checking if there is too much arguments
        if(args.length > 4)
        {
            throw new Exception("Too much arguments Exception");
        }
        System.out.println(args[2]);

        switch (args[2]) {
            case "--stat":
                MetadataExtractor s = new MetadataExtractor(args[1]);
                System.out.println("titre : " +s.getTitle()+ "\n" + "taille : " + s.getFileSize()+ "\n" + "dimension : " + s.getDimensions());
                break;
            case "--info":
                MetadataExtractor s1 = new MetadataExtractor(args[1]);
                System.out.println("description : " + s1.getDescription() + "\n" + "geolocation : " + s1.getGeolocation()+ "\n" + "type MIME : " + s1.getMime());
                if (args.length == 4){
                    if(args[3]== "--stat"){
                        System.out.println("titre : " +s1.getTitle()+ "\n" + "taille : " + s1.getFileSize()+ "\n" + "dimension : " + s1.getDimensions());
                    }
                }
            default:
                break;
        }
        /*
        if(args[2]== "--stat"){
            System.out.println("oui");
            MetadataExtractor s = new MetadataExtractor(args[1]);
            System.out.println("titre : " +s.getTitle()+ "/n" + "taille : " + s.getFileSize()+ "/n" + "dimension : " + s.getDimensions());
        }
        if(args[2]== "--info"){
            MetadataExtractor s = new MetadataExtractor(args[1]);
            System.out.println("description : " + s.getDescription() + "/n" + "geolocation : " + s.getGeolocation()+ "/n" + "type MIME : " + s.getMime());
            if (args.length == 4){
                if(args[3]== "--stat"){
                    System.out.println("titre : " +s.getTitle()+ "/n" + "taille : " + s.getFileSize()+ "/n" + "dimension : " + s.getDimensions());
                }
            }
        }*/
    }
}
        