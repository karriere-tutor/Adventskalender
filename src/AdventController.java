import javax.swing.*;
import java.time.LocalDate;

public class AdventController {
    private AdventFrame frame;
    private AdventData data;

    public AdventController(AdventFrame frame) {
        this.frame = frame;
        this.data = new AdventData();
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

        // Zeige das Bild in einem Dialog an
        JOptionPane.showMessageDialog(
                frame,
                new JLabel(message, image, JLabel.CENTER),
                "Türchen " + day,
                JOptionPane.PLAIN_MESSAGE
        );
    }
}
