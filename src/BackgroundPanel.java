import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage; // Hintergrundbild

    public BackgroundPanel(String imagePath) {
        try {
            // Hintergrundbild laden
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            backgroundImage = null;
            System.err.println("Hintergrundbild konnte nicht geladen werden: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Hintergrundbild zeichnen (skaliert auf Panel-Größe)
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
