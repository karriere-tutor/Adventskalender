import javax.swing.*;

public class AdventData {
    private static final String DOOR_IMAGE_PATH_PREFIX = "/images/door";

    private final String[] messages = new String[24];
    private final ImageIcon[] images = new ImageIcon[24];

    public AdventData() {
        for (int i = 0; i < messages.length; i++) {
            messages[i] = "Überraschung für den " + (i + 1) + ". Dezember!";
            images[i] = null;
        }
    }

    public String getMessage(int day) {
        return messages[day - 1];
    }

    public ImageIcon getImage(int day) {
        if (images[day - 1] == null) {
            String imagePath = DOOR_IMAGE_PATH_PREFIX + day + ".jpg";
            java.net.URL imgUrl = getClass().getResource(imagePath);
            if (imgUrl != null) {
                images[day - 1] = new ImageIcon(imgUrl);
            } else {
                System.err.println("Fehler: Bild für Tag " + day + " nicht gefunden! Pfad: " + imagePath);
                return null;
            }
        }
        return images[day - 1];
    }
}
