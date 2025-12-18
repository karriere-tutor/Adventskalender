import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class AdventFrame extends JFrame {
    private JButton[] buttons = new JButton[24];
    private AdventController controller;

    public AdventFrame() {
        controller = new AdventController(this);

        setTitle("Adventskalender");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Zufällige Anordnung der Tage
        ArrayList<Integer> days = new ArrayList<>();
        for (int i = 1; i <= 24; i++) days.add(i);
        Collections.shuffle(days);

        // Zufällige Größen für die Türchen
        int[] gridwidths = {1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2};
        int[] gridheights = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2};

        int gridx = 0;
        int gridy = 0;

        for (int i = 0; i < 24; i++) {
            int day = days.get(i);
            JButton button = new JButton(String.valueOf(day));
            buttons[day - 1] = button;

            gbc.gridx = gridx;
            gbc.gridy = gridy;
            gbc.gridwidth = gridwidths[i];
            gbc.gridheight = gridheights[i];
            gbc.weightx = gridwidths[i];
            gbc.weighty = gridheights[i];

            int currentDay = day;
            button.addActionListener(e -> controller.openDoor(currentDay));

            add(button, gbc);

            gridx += gridwidths[i];
            if (gridx >= 6) { // 6 Spalten als Maximum
                gridx = 0;
                gridy += 1;
            }
        }

        controller.updateButtonStates();
    }

    public JButton[] getButtons() {
        return buttons;
    }
}
