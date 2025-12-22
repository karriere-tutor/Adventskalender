import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    static final String BACKGROUND_IMAGE_PATH = "/images/background.jpg";
    private final Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        this.backgroundImage = loadImage(imagePath);
    }

    private Image loadImage(String path) {
        java.net.URL imgUrl = getClass().getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl).getImage();
        } else {
            System.err.println("Hintergrundbild nicht gefunden: " + path);
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

