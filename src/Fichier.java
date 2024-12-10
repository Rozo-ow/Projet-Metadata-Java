package src ;

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
    public String GetTitre(){
        return titre;
    }

    public String GetPath(){
        return path ;
    }

    public float GetTaille(){
        return taille ;
    }

    public String GetMime(){
        return mime ;
    }

    public void SetTitre(String t){
        titre = t ;
    }
}
