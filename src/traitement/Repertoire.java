package traitement;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Repertoire {
    private String nom ;
    private List<Repertoire> sousrepertoire ;
    private List<File> fichiers ;

    public Repertoire(String nom){
        this.nom = nom ;
        this.sousrepertoire = new ArrayList<>();
        this.fichiers = new ArrayList<>();
    }

    public String getNom(){
        return nom ;
    }

    public List<Repertoire> getSousrepertoire(){
        return sousrepertoire ;
    }

    public List<File> getFichiers(){
        return fichiers ;
    }

    public void addSousrepertoire(Repertoire r){
        sousrepertoire.add(r);
    }

    public void addFichier(File f){
        fichiers.add(f);
    }

    public void supSourepertoire(Repertoire r){
        if(sousrepertoire.contains(r)){
            sousrepertoire.remove(r);
        }
    }

    public void supFichier(Fichier f){
        if (fichiers.contains(f)){
            fichiers.remove(f);
        }
    }

    public int nbrFichier(){
        return fichiers.size();
    }

    public int nbrImage(){
    
    }
}
