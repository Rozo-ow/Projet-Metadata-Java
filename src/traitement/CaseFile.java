package traitement;
import java.io.File;

public class CaseFile extends Case {

    public void file(String[] args) throws Exception{
        //---------------------------------------------------------------------
        //Cas d'erreurs
        //---------------------------------------------------------------------
        //checking if the file or filepath is provided
        if(args.length == 1)
        {
            throw new Exception("No file or filepath Exception");
        }

        //checking if there is too much arguments
        if(args.length > 5)
        {
            throw new Exception("Too much arguments Exception");
        }

                //checking if the file exists
       }
}
        