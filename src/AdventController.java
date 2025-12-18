import javax.swing.*;
import java.time.LocalDate;

public class AdventController {
    private AdventFrame frame; // Referenz auf das Hauptfenster
    private AdventData data; // Daten für die Türchen-Inhalte

    public AdventController(AdventFrame frame) {
        this.frame = frame;
        this.data = new AdventData();
    }

    // Aktualisiert die Button-Zustände (aktiv/deaktiviert) basierend auf dem aktuellen Datum
    public void updateButtonStates() {
        int today = LocalDate.now().getDayOfMonth();
        for (int i = 0; i < frame.getButtons().length; i++) {
            // Button nur aktivieren, wenn der Tag bereits erreicht ist (und <= 24)
            frame.getButtons()[i].setEnabled(i + 1 <= today && today <= 24);
        }
    }

    // Wird aufgerufen, wenn ein Türchen geöffnet wird
    public void openDoor(int day) {
        String message = data.getMessage(day); // Nachricht für den Tag
        ImageIcon image = data.getImage(day); // Bild für den Tag

        // Dialog mit Nachricht und Bild anzeigen
        JOptionPane.showMessageDialog(
                frame,
                new JLabel(message, image, JLabel.CENTER),
                "Türchen " + day,
                JOptionPane.PLAIN_MESSAGE
        );
    }
}
