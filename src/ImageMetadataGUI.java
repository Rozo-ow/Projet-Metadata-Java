import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;

public class ImageMetadataGUI {

    private JFrame frame;
    private JTextArea textArea;
    private JLabel imageLabel;
    private JFileChooser fileChooser;
    private Snapshot snapshot;

    public ImageMetadataGUI() {
        snapshot = new Snapshot();
        initialize();
    }

    private void initialize() {
        // Frame principale
        frame = new JFrame("Image Metadata Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        // Barre de menu
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem openFileItem = new JMenuItem("Ouvrir un fichier");
        JMenuItem openDirItem = new JMenuItem("Ouvrir un répertoire");
        JMenuItem saveSnapshotItem = new JMenuItem("Sauvegarder l'état");
        JMenuItem restoreSnapshotItem = new JMenuItem("Restaurer un état");

        fileMenu.add(openFileItem);
        fileMenu.add(openDirItem);
        fileMenu.add(saveSnapshotItem);
        fileMenu.add(restoreSnapshotItem);
        menuBar.add(fileMenu);

        JMenu searchMenu = new JMenu("Recherche");
        JMenuItem searchByNameItem = new JMenuItem("Recherche par nom");
        JMenuItem searchByDimensionsItem = new JMenuItem("Recherche par dimensions");
        JMenuItem searchByDateItem = new JMenuItem("Recherche par date");

        searchMenu.add(searchByNameItem);
        searchMenu.add(searchByDimensionsItem);
        searchMenu.add(searchByDateItem);
        menuBar.add(searchMenu);

        frame.setJMenuBar(menuBar);

        // Zone de texte pour afficher les métadonnées
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Zone pour afficher l'image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane imageScrollPane = new JScrollPane(imageLabel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, imageScrollPane);
        splitPane.setDividerLocation(400);

        frame.add(splitPane, BorderLayout.CENTER);

        // Actions des menus
        openFileItem.addActionListener(e -> openFileAction());

        openDirItem.addActionListener(e -> openDirectoryAction());

        saveSnapshotItem.addActionListener(e -> saveSnapshotAction());

        restoreSnapshotItem.addActionListener(e -> restoreSnapshotAction());

        searchByNameItem.addActionListener(e -> searchByNameAction());

        searchByDimensionsItem.addActionListener(e -> searchByDimensionsAction());

        searchByDateItem.addActionListener(e -> searchByDateAction());

        frame.setVisible(true);
    }

    private void openFileAction() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "webp");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            displayFileMetadata(selectedFile);
        }
    }

    private void openDirectoryAction() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            displayDirectoryContents(selectedDirectory);
        }
    }

    private void saveSnapshotAction() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            snapshot.saveSnapshot(file.getAbsolutePath());
            JOptionPane.showMessageDialog(frame, "Snapshot sauvegardé avec succès.");
        }
    }

    private void restoreSnapshotAction() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            snapshot.restoreSnapshot(file.getAbsolutePath());
            JOptionPane.showMessageDialog(frame, "Snapshot restauré avec succès.");
        }
    }

    private void searchByNameAction() {
        String name = JOptionPane.showInputDialog(frame, "Entrez le nom ou une partie du nom du fichier :");
        if (name != null) {
            textArea.setText("Recherche par nom : " + name + "\n(Fonctionnalité à implémenter)");
        }
    }

    private void searchByDimensionsAction() {
        String dimensions = JOptionPane.showInputDialog(frame, "Entrez les dimensions (ex: 800x600) :");
        if (dimensions != null) {
            textArea.setText("Recherche par dimensions : " + dimensions + "\n(Fonctionnalité à implémenter)");
        }
    }

    private void searchByDateAction() {
        String date = JOptionPane.showInputDialog(frame, "Entrez l'année ou la date (ex: 2024) :");
        if (date != null) {
            textArea.setText("Recherche par date : " + date + "\n(Fonctionnalité à implémenter)");
        }
    }

    private void displayFileMetadata(File file) {
        try {
            MetadataExtractor extractor = new MetadataExtractor(file.getAbsolutePath());

            textArea.setText("Métadonnées de l'image:\n" +
                    "Titre: " + extractor.getTitle() + "\n" +
                    "Dimensions: " + extractor.getDimensions() + "\n" +
                    "Taille: " + extractor.getFileSize() + " octets\n" +
                    "Description: " + extractor.getDescription() + "\n" +
                    "Géolocalisation: " + extractor.getGeolocation());

            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                imageLabel.setIcon(new ImageIcon(image));
            } else {
                imageLabel.setIcon(null);
                JOptionPane.showMessageDialog(frame, "Impossible de charger l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'extraction des métadonnées.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayDirectoryContents(File directory) {
        Repertoire rep = new Repertoire(directory);
        textArea.setText("Contenu du répertoire:\n");
        textArea.append("Nombre total de fichiers image: " + rep.nbrImage() + "\n");
        textArea.append("Nombre d'images PNG: " + rep.nbrImagesPNG() + "\n");
        textArea.append("Nombre d'images JPEG: " + rep.nbrImagesJPEG() + "\n");
        textArea.append("Nombre d'images WEBP: " + rep.nbrImagesWEBP() + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageMetadataGUI::new);
    }
}
