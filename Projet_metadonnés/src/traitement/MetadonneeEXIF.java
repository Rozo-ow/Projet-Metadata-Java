package traitement ;

public class MetadonneeEXIF {
    private String dimenssions ;
    private String resolution ;
    private String titre ;
    private String description ;
    private String geoloc ;

    /**
	* Constructeur de la Metadonnée
	*
	* 
	* @param dimenssion la dimension de l'image
    * @param resolution la resolution de l'image
    * @param titre le titre de l'image
    * @param description la description de l'image
    * @param geoloc la géolocalisation de l'image
	*/
    
    
    
    
    
    public MetadonneeEXIF(String dimenssions, String resolution, String titre, String description, String geoloc){
        this.dimenssions = dimenssions ;
        this.resolution = resolution ;
        this.titre = titre ;
        this.description = description ;
        this.geoloc = geoloc ;
    }

    public MetadonneeEXIF(String nom){
        ********
    }

    public String getDim(){
        return dimenssions;
    }

    public String GetRes(){
        return resolution ;
    }

    public String GetTitre(){
        return titre ;
    }

    public String GetDes(){
        return description ;
    }

    public String GetGeo(){
        return geoloc ;
    }

    public String toString(){
        return "Metadonnées : \n dimenssions : "+ dimenssions + "\n résolution : " + resolution + "\n titre : " + titre + "\n description : " + description + "\n géolocalisation : " + geoloc ;
    }
}

