import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class TransparentButton extends JButton {
    public TransparentButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setForeground(Color.WHITE);
        setUI(new TransparentButtonUI());
    }

    private static class TransparentButtonUI extends BasicButtonUI {
        @Override
        public void paint(Graphics g, JComponent c) {
            // Kein Hintergrund wird gezeichnet
            super.paint(g, c);
        }
    }
}
