import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class TransparentButton extends JButton {
    public TransparentButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setForeground(new Color(220, 14, 7));
        setFont(new Font("Arial", Font.BOLD, 32));
        setUI(new TransparentButtonUI());
    }

    private static class TransparentButtonUI extends BasicButtonUI {
        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
        }
    }
}