import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;

public class AdventController {
    private final AdventFrame frame;
    private final AdventData data;

    public AdventController(AdventFrame frame) {
        this.frame = frame;
        this.data = new AdventData();
        //loadProgress();
    }

    public void updateButtonStates() {
        int today = LocalDate.now().getDayOfMonth();
        for (int i = 0; i < frame.getButtons().length; i++) {
            frame.getButtons()[i].setEnabled(i + 1 <= today && today <= 24);
        }
    }

    public void openDoor(int day) {
        String message = data.getMessage(day);
        ImageIcon image = data.getImage(day);
        ImageIcon scaledImage = scaleImage(image);

        JOptionPane.showMessageDialog(
                frame,
                new JLabel(message, scaledImage, JLabel.CENTER),
                "Türchen " + day,
                JOptionPane.PLAIN_MESSAGE
        );

        frame.getButtons()[day - 1].setEnabled(false);
        saveProgress(day);
    }

    private ImageIcon scaleImage(ImageIcon icon) {
        if (icon == null) return null;

        Image image = icon.getImage();
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();

        if (width > 400 || height > 400) {
            double ratio = Math.min((double) 400 / width, (double) 400 / height);
            width = (int) (width * ratio);
            height = (int) (height * ratio);
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }

        return new ImageIcon(image);
    }

    private void saveProgress(int day) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("progress.txt", true))) {
            writer.println(day);
        } catch (IOException e) {
            System.err.println("Fortschritt konnte nicht gespeichert werden: " + e.getMessage());
        }
    }

    void loadProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader("progress.txt"))) {
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
