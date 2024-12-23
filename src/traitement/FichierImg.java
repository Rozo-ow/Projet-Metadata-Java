package traitement ;
import traitement.Fichier;
/**
public class FichierImg extends Fichier{

    private Metadonnee meta ;

    public FichierImg(String titre, String path, float taille, String mime, Metadonnee meta ){
        super(titre, path, taille, mime) ;
        this.meta = new Metadonnee(mime, mime, titre, path, mime) ;
    }

    public String getMeta(){
        return meta.toString();
    }
}
*/