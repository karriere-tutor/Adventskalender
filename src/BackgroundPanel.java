import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        this.backgroundImage = loadImage(imagePath);
    }

    private Image loadImage(String path) {
        try {
            return new ImageIcon(Objects.requireNonNull(getClass().getResource(path))).getImage();
        } catch (Exception e) {
            System.err.println("Hintergrundbild konnte nicht geladen werden: " + e.getMessage());
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
