import javax.swing.ImageIcon;

public class AdventData {
    private final String[] messages = new String[24]; // Nachrichten für jeden Tag
    private final ImageIcon[] images = new ImageIcon[24]; // Bilder für jeden Tag

    public AdventData() {
        for (int i = 0; i < messages.length; i++) {
            // Standardnachricht für jeden Tag
            messages[i] = "Überraschung für den " + (i + 1) + ". Dezember!";
            try {
                // Bild für jeden Tag laden (z. B. "images/door1.jpg")
                images[i] = new ImageIcon("images/door" + (i + 1) + ".jpg");
            } catch (Exception e) {
                images[i] = null; // Falls das Bild nicht geladen werden kann
            }
        }
    }

    // Gibt die Nachricht für einen Tag zurück
    public String getMessage(int day) {
        return messages[day - 1];
    }

    // Gibt das Bild für einen Tag zurück
    public ImageIcon getImage(int day) {
        return images[day - 1];
    }
}
