import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;

public class AdventController {
    private final AdventFrame frame; // Referenz auf das Hauptfenster, um auf die UI-Elemente zuzugreifen.
    private final AdventData data; // Enthält die Daten (Nachrichten und Bilder) für die Türchen.

    public AdventController(AdventFrame frame) {
        this.frame = frame;
        this.data = new AdventData();
    }

    /**
     Aktualisiert die Zustände der Buttons basierend auf dem aktuellen Datum.
     Im Dezember bleiben alle Türchen (1-24) aktiv.
     In anderen Monaten sind nur die Türchen aktiv, deren Tag kleiner oder gleich dem aktuellen Tag ist.
     */
    public void updateButtonStates() {
        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue(); // 12 = Dezember

        for (int i = 0; i < frame.getButtons().length; i++) {
            int day = i + 1;
            boolean isDecember = currentMonth == 12;
            boolean isDoorOpen = isDoorOpen(day);

            frame.getButtons()[i].setEnabled(
                    (isDecember && day <= 24) ||
                            (currentDay >= day && !isDecember && !isDoorOpen)
            );
        }
    }

    /**
     Öffnet ein Türchen und zeigt die Nachricht und das Bild an.
     Speichert den Fortschritt in der Datei "progress.txt".
     */
    public void openDoor(int day) {
        if (isDoorOpen(day)) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Dieses Türchen hast du bereits geöffnet!",
                    "Türchen " + day,
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        String message = data.getMessage(day);
        ImageIcon image = data.getImage(day);
        ImageIcon scaledImage = scaleImage(image, 400, 400);

        JOptionPane.showMessageDialog(
                frame,
                new JLabel(message, scaledImage, JLabel.CENTER),
                "Türchen " + day,
                JOptionPane.PLAIN_MESSAGE
        );

        frame.getButtons()[day - 1].setEnabled(false);
        saveProgress(day);
    }

    /**
     * Prüft, ob ein Türchen bereits geöffnet wurde.
     */
    private boolean isDoorOpen(int day) {
        File progressFile = new File("progress.txt");
        if (!progressFile.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(progressFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    if (Integer.parseInt(line.trim()) == day) {
                        return true;
                    }
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen des Fortschritts: " + e.getMessage());
        }
        return false;
    }

    /**
     * Skaliert ein Bild auf eine maximale Größe.
     */
    private ImageIcon scaleImage(ImageIcon icon, int maxWidth, int maxHeight) {
        if (icon == null) return null;

        Image image = icon.getImage();
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();

        if (width > maxWidth || height > maxHeight) {
            double ratio = Math.min((double) maxWidth / width, (double) maxHeight / height);
            width = (int) (width * ratio);
            height = (int) (height * ratio);
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }

        return new ImageIcon(image);
    }

    /**
     * Speichert den Fortschritt in der Datei "progress.txt".
     */
    private void saveProgress(int day) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("progress.txt", true))) {
            writer.println(day);
        } catch (IOException e) {
            System.err.println("Fortschritt konnte nicht gespeichert werden: " + e.getMessage());
        }
    }

    /**
     * Lädt den Fortschritt aus der Datei "progress.txt".
     */
    void loadProgress() {
        File progressFile = new File("progress.txt");
        if (!progressFile.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(progressFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int day = Integer.parseInt(line.trim());
                    if (day >= 1 && day <= 24) {
                        frame.getButtons()[day - 1].setEnabled(false);
                    }
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            System.err.println("Fortschritt konnte nicht geladen werden: " + e.getMessage());
        }
    }
}

