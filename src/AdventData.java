import javax.swing.ImageIcon;

public class AdventData {
    private String[] messages = new String[24];
    private ImageIcon[] images = new ImageIcon[24];

    public AdventData() {
        for (int i = 0; i < messages.length; i++) {
            messages[i] = "Überraschung für den " + (i + 1) + ". Dezember!";
            try {
                images[i] = new ImageIcon("images/door" + (i + 1) + ".jpg");
            } catch (Exception e) {
                images[i] = null; // Falls das Bild nicht geladen werden kann
            }
        }
    }

    public String getMessage(int day) {
        return messages[day - 1];
    }

    public ImageIcon getImage(int day) {
        return images[day - 1];
    }
}
