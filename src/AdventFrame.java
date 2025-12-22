import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class AdventFrame extends JFrame {
    private static final int COLUMNS = 6;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private static final String BACKGROUND_IMAGE_PATH = "/images/background.jpg";

    private final JButton[] buttons = new JButton[24];
    private final AdventController controller;

    public AdventFrame() {
        controller = new AdventController(this);
        initializeUI();
        controller.loadProgress();
    }

    private void initializeUI() {
        setTitle("Adventskalender");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel(BACKGROUND_IMAGE_PATH);
        backgroundPanel.setLayout(new GridLayout(4, COLUMNS)); // Einfaches GridLayout
        setContentPane(backgroundPanel);

        placeButtons(backgroundPanel);
        controller.updateButtonStates();
    }

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

    public JButton[] getButtons() {
        return buttons;
    }
}

