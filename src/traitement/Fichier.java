package traitement ;

public class Fichier {
    private String titre ;
    private String path ;
    private float taille ;
    private String mime ;

     /**
	* Constructeur de la classe Fichier
	*
	* @param titre le titre de l'image
	* @param path le chemin de l'image
    * @param taille la taille de l'image
    * @param mime le type d'extension de l'image
	*/
    
    public Fichier(String titre, String path, float taille, String mime){
        this.titre = titre ;
        this.path = path ;
        this.taille = taille ;
        this.mime = mime ;
    }
    public String getTitre(){
        return titre;
    }

    public String getPath(){
        return path ;
    }

    public float getTaille(){
        return taille ;
    }

    public String getMime(){
        return mime ;
    }

    public void setTitre(String t){
        titre = t ;
    }
}
