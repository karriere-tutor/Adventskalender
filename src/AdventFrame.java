import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class AdventFrame extends JFrame {
    // Konstanten für Layout
    private static final int COLUMNS = 6;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;

    private final JButton[] buttons = new JButton[24]; // Array für die 24 Türchen-Buttons.
    private final AdventController controller; // Controller für die Logik.

    public AdventFrame() {
        controller = new AdventController(this);
        initializeUI();
        controller.loadProgress(); // Lädt den Fortschritt nach der UI-Initialisierung.
    }

    /**
     * Initialisiert die UI-Komponenten.
     */
    private void initializeUI() {
        setTitle("Adventskalender");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Hintergrundpanel mit Weihnachtsbild.
        BackgroundPanel backgroundPanel = new BackgroundPanel(BackgroundPanel.BACKGROUND_IMAGE_PATH);
        backgroundPanel.setLayout(new GridLayout(4, COLUMNS)); // Einfaches GridLayout
        setContentPane(backgroundPanel);

        placeButtons(backgroundPanel); // Platziert die Buttons.
        controller.updateButtonStates(); // Aktualisiert die Button-Zustände.
    }

    /**
     * Platziert die Buttons auf dem Panel.
     */
    private void placeButtons(JPanel panel) {
        ArrayList<Integer> days = new ArrayList<>();
        for (int i = 1; i <= 24; i++) days.add(i);
        Collections.shuffle(days);

        for (int day : days) {
            JButton button = new TransparentButton(String.valueOf(day));
            button.addActionListener(e -> controller.openDoor(day));
            buttons[day - 1] = button;
            panel.add(button);
        }
    }

    /**
     * Gibt das Array der Buttons zurück.
     */
    public JButton[] getButtons() {
        return buttons;
    }
}

