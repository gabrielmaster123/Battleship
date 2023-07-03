import java.awt.*;
import javax.swing.*;



public class SwingIO extends JPanel{
    public static void setup(JFrame frame, JPanel panel, int sizeX, int sizeY) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);



        frame.add(panel);

        frame.setVisible(true);
    }    

    public static void addText(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        panel.add(label);
    }

    public static void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        panel.add(button);
    }
}