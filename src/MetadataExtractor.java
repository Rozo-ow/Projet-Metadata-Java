import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

public class MetadataExtractor {
    private File file;
    private Metadata metadata;
    private String content;

    // Nouveaux attributs spécifiques
    private String dimensions;
    private String title;
    private long fileSize;
    private String description;
    private String geolocation;

    /**
     * Constructeur pour initialiser avec un fichier spécifique.
     *
     * @param filePath Le chemin complet du fichier.
     * @throws Exception Si le fichier n'existe pas ou si une erreur survient lors de l'extraction.
     */
    public MetadataExtractor(String filePath) throws Exception {
        this.file = new File(filePath);
        if (!this.file.exists()) {
            throw new IllegalArgumentException("Le fichier spécifié n'existe pas : " + filePath);
        }

        // Extraire les métadonnées
        extractMetadata();
    }

    /**
     * Méthode pour extraire les métadonnées et le contenu du fichier.
     */
    private void extractMetadata() throws Exception {
        this.metadata = new Metadata();
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();

        // Extraire le contenu et les métadonnées de base
        try (FileInputStream inputStream = new FileInputStream(this.file)) {
            parser.parse(inputStream, handler, metadata);
            this.content = handler.toString();
        }

        // Extraire les informations spécifiques au fichier
        extractSpecificAttributes();
    }

    /**
     * Méthode pour extraire les informations spécifiques comme la dimension, le titre, la taille, etc.
     */
    private void extractSpecificAttributes() {
        // Extraire les métadonnées de base
        this.title = metadata.get("Title");
        if (title == null) {
            this.title = this.file.getName();  // Si aucun titre, utiliser le nom du fichier
        }
        this.fileSize = this.file.length();
        this.description = metadata.get("Description");  // Tika peut fournir cette information si présente
        this.geolocation = metadata.get("geolocation");  // La géolocalisation pourrait être présente si applicable

        // Si le fichier est une image, on extrait les dimensions
        if (isImageFile()) {
            try {
                BufferedImage image = null;
                image = ImageIO.read(file);
                if (image != null) {
                    this.dimensions = image.getWidth() + "x" + image.getHeight();  // Exemple de format pour dimensions
                }
            } catch (Exception e) {
                this.dimensions = "Non disponible";
            }
        }
    }

    /**
     * Vérifie si le fichier est une image basée sur son extension.
     *
     * @return true si le fichier est une image.
     */
    private boolean isImageFile() {
        String[] imageExtensions = {"jpg", "jpeg", "png", "bmp", "gif"};
        String fileName = file.getName().toLowerCase();

        for (String ext : imageExtensions) {
            if (fileName.endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Récupère les métadonnées extraites sous forme de tableau clé-valeur.
     *
     * @return Un tableau contenant toutes les métadonnées.
     */
    public String[] getMetadata() {
        StringBuilder result = new StringBuilder();
        for (String name : metadata.names()) {
            result.append(name).append(" : ").append(metadata.get(name)).append("\n");
        }
        return result.toString().split("\n");
    }

    /**
     * Récupère le contenu extrait du fichier (si applicable).
     *
     * @return Le contenu textuel du fichier.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Récupère le chemin du fichier analysé.
     *
     * @return Le chemin complet du fichier.
     */
    public String getFilePath() {
        return this.file.getAbsolutePath();
    }

    // Méthodes getter pour les nouveaux attributs

    public String getDimensions() {
        return dimensions;
    }

    public String getTitle() {
        return title;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getDescription() {
        return description;
    }

    public String getGeolocation() {
        return geolocation;
    }
}
