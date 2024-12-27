package Cases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Exception;

public class CaseHelp extends Case {
    
    public void help(){
        try {
            
            InputStream is = getFileAsIOStream("Cases/help.txt");
            //print the document Cases/help.txt
            printFileContent(is);
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }

    
    private InputStream getFileAsIOStream(final String fileName) 
    /*
    @affiche le contenu du fichier help.txt
    //cette méthode permet d'acceder au fichier dans le code lorsqu'il est compressé en jar.
    */
    {
        InputStream ioStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(fileName);
        
        //if the document is not found
        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    private void printFileContent(InputStream is) throws IOException 
    /*
    @affiche le contenu du fichier help.txt
    //cette méthode permet d'afficher toutes les lignes d'un document.
    */
    {
        try (InputStreamReader isr = new InputStreamReader(is); 
                BufferedReader br = new BufferedReader(isr);) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            is.close();
        }
    }
}

    