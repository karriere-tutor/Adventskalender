import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class AdventFrame extends JFrame {
    private JButton[] buttons = new JButton[24]; // Array für die 24 Türchen-Buttons
    private AdventController controller; // Controller für die Logik

    public AdventFrame() {
        controller = new AdventController(this);

        // Fenster-Eigenschaften
        setTitle("Adventskalender");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Zentriert das Fenster

        // Hintergrundpanel mit Weihnachtsbild
        BackgroundPanel backgroundPanel = new BackgroundPanel("images/background.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        // GridBagLayout für flexible Positionierung der Buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Tage 1-24 mischen für zufällige Anordnung
        ArrayList<Integer> days = new ArrayList<>();
        for (int i = 1; i <= 24; i++) days.add(i);
        Collections.shuffle(days);

        // Zufällige Größen für die Türchen (1x1, 2x1, 2x2)
        int[] gridwidths = {1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2};
        int[] gridheights = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2};

        int gridx = 0; // Aktuelle Spalte
        int gridy = 0; // Aktuelle Zeile

        // Schleife zum Erstellen der 24 Buttons
        for (int i = 0; i < 24; i++) {
            int day = days.get(i); // Gemischter Tag (1-24)
            JButton button = new TransparentButton(String.valueOf(day));

            // Button-Stil
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            button.setForeground(new Color(220, 14, 7)); // Rot
            button.setFont(new Font("Arial", Font.BOLD, 32)); // Große, fette Schrift
            buttons[day - 1] = button;

            // Position und Größe des Buttons festlegen
            gbc.gridx = gridx;
            gbc.gridy = gridy;
            gbc.gridwidth = gridwidths[i];
            gbc.gridheight = gridheights[i];
            gbc.weightx = gridwidths[i];
            gbc.weighty = gridheights[i];

            // ActionListener für das Öffnen der Türchen
            int currentDay = day;
            button.addActionListener(e -> controller.openDoor(currentDay));

            backgroundPanel.add(button, gbc); // Button zum Panel hinzufügen

            // Nächste Position berechnen
            gridx += gridwidths[i];
            if (gridx >= 6) { // Maximal 6 Spalten
                gridx = 0;
                gridy += 1;
            }
        }

        controller.updateButtonStates(); // Buttons je nach Datum aktivieren/deaktivieren
    }

    // Getter für die Buttons (für den Controller)
    public JButton[] getButtons() {
        return buttons;
    }
}
