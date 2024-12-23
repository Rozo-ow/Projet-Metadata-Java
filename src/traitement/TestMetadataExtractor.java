package traitement ;

public class TestMetadataExtractor {
    public static void main(String[] args) {
        // Exemple de chemin de fichier à analyser
        String filePath = "src/traitement/fleurs.jpg";

        try {
            // Initialisation de l'extracteur avec un fichier
            MetadataExtractor extractor = new MetadataExtractor(filePath);

            // Affichage du chemin du fichier
            System.out.println("Fichier analysé : " + extractor.getFilePath());

            // Affichage des métadonnées
            System.out.println("\n=== Métadonnées ===");
            for (String metadata : extractor.getMetadata()) {
                System.out.println(metadata);
            }

            // Affichage du contenu extrait
            System.out.println("\n=== Contenu extrait (si disponible) ===");
            System.out.println(extractor.getContent());

        } catch (Exception e) {
            System.err.println("Erreur lors de l'analyse du fichier : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
