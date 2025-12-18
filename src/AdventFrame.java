import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class AdventFrame extends JFrame {
    private static final int COLUMNS = 6;
    private final JButton[] buttons = new JButton[24];
    private final AdventController controller;

    public AdventFrame() {
        controller = new AdventController(this);
        initializeUI();
        controller.loadProgress();
    }


    private void initializeUI() {
        setTitle("Adventskalender");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/background.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        placeButtons(backgroundPanel);
        controller.updateButtonStates();
    }

    private void placeButtons(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        ArrayList<Integer> days = new ArrayList<>();
        for (int i = 1; i <= 24; i++) days.add(i);
        Collections.shuffle(days);

        int[] gridWidths = {1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2};
        int[] gridHeights = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2};

        int gridX = 0;
        int gridY = 0;

        for (int i = 0; i < 24; i++) {
            int day = days.get(i);
            JButton button = new TransparentButton(String.valueOf(day));
            button.addActionListener(e -> controller.openDoor(day));

            buttons[day - 1] = button;

            gbc.gridx = gridX;
            gbc.gridy = gridY;
            gbc.gridwidth = gridWidths[i];
            gbc.gridheight = gridHeights[i];
            gbc.weightx = gridWidths[i];
            gbc.weighty = gridHeights[i];

            panel.add(button, gbc);

            gridX += gridWidths[i];
            if (gridX >= COLUMNS) {
                gridX = 0;
                gridY += 1;
            }
        }
    }

    public JButton[] getButtons() {
        return buttons;
    }
}
