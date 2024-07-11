import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CALCULADORA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // para cerrar el frame
        frame.setContentPane(new Form1().MainPanel);
        frame.setSize(800, 600);
        frame.setPreferredSize(new Dimension(750, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}