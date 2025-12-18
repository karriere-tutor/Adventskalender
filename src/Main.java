
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Startet die GUI im Event-Dispatch-Thread (EDT) für Thread-Sicherheit
        SwingUtilities.invokeLater(() -> {
            new AdventFrame().setVisible(true);
        });
    }
}
