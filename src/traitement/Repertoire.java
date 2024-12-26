//package traitement;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Repertoire {
    private String nom;
    private List<Repertoire> sousrepertoire;
    private List<File> fichiers;


    public Repertoire(File chemin) {
        this.nom = chemin.getName();
        this.sousrepertoire = new ArrayList<>();
        this.fichiers = new ArrayList<>();
        File[] files = chemin.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    this.sousrepertoire.add(new Repertoire(file));
                } else {
                    this.fichiers.add(file);
                }
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public List<Repertoire> getSousrepertoire() {
        return sousrepertoire;
    }

    public List<File> getFichiers() {
        return fichiers;
    }

    public void addSousrepertoire(Repertoire r) {
        sousrepertoire.add(r);
    }

    public void addFichier(File f) {
        fichiers.add(f);
    }

    public void supSourepertoire(Repertoire r) {
        if (sousrepertoire.contains(r)) {
            sousrepertoire.remove(r);
        }
    }

    public void supFichier(File f) {
        if (fichiers.contains(f)) {
            fichiers.remove(f);
        }
    }

    public int nbrFichier() {
        int totalFichiers = fichiers.size();
        System.out.println("Nombre de fichiers dans " + nom + " : " + totalFichiers);
        for (Repertoire r : sousrepertoire) {
            totalFichiers += r.nbrFichier();
        }
        return totalFichiers;
    }

    // Méthode pour compter le nombre d'images au format PNG dans ce répertoire et ses sous-répertoires
    public int nbrImagesPNG() {
        int count = 0;

        // Comptage des fichiers PNG dans ce répertoire
        for (File fichier : fichiers) {
            if (fichier.getName().toLowerCase().endsWith(".png")) {
                count++;
            }
        }

        // Comptage des fichiers PNG dans les sous-répertoires
        for (Repertoire r : sousrepertoire) {
            count += r.nbrImagesPNG();
        }

        return count;
    }

    // Méthode pour compter le nombre d'images au format JPEG dans ce répertoire et ses sous-répertoires
    public int nbrImagesJPEG() {
        int count = 0;

        // Comptage des fichiers JPEG dans ce répertoire
        for (File fichier : fichiers) {
            String name = fichier.getName().toLowerCase();
            if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
                count++;
            }
        }

        // Comptage des fichiers JPEG dans les sous-répertoires
        for (Repertoire r : sousrepertoire) {
            count += r.nbrImagesJPEG();
        }

        return count;
    }

    // Méthode pour compter le nombre d'images au format WEBP dans ce répertoire et ses sous-répertoires
    public int nbrImagesWEBP() {
        int count = 0;

        // Comptage des fichiers WEBP dans ce répertoire
        for (File fichier : fichiers) {
            if (fichier.getName().toLowerCase().endsWith(".webp")) {
                count++;
            }
        }

        // Comptage des fichiers WEBP dans les sous-répertoires
        for (Repertoire r : sousrepertoire) {
            count += r.nbrImagesWEBP();
        }

        return count;
    }

    // Méthode pour calculer le nombre total d'images dans les formats supportés dans ce répertoire et ses sous-répertoires
    public int nbrImage() {
        return nbrImagesPNG() + nbrImagesJPEG() + nbrImagesWEBP();
    }

    public String stats() {
        return "Nombre total de fichiers : " + nbrFichier() + "\n" +
                "Nombre total d'images PNG : " + nbrImagesPNG() + "\n" +
                "Nombre total d'images JPEG : " + nbrImagesJPEG() + "\n" +
                "Nombre total d'images WEBP : " + nbrImagesWEBP() + "\n" +
                "Nombre total d'images : " + nbrImage();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (File fichier : fichiers) {
            result.append(fichier.getName()).append(",").append(fichier.lastModified()).append("\n");
        }
        for (Repertoire r : sousrepertoire) {
            result.append(r.toString());
        }
        return result.toString();
    }
}
