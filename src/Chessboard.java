import javax.swing.*;
import java.awt.*;

public class Chessboard {

    JPanel panel;
    public void init(){
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 526, 549);
        frame.setTitle("Skak");
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean lightSquare = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (lightSquare) {
                            g.setColor(new Color(235, 235, 208));
                        } else {
                            g.setColor(new Color(119, 148, 85));

                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        lightSquare = !lightSquare;
                    }
                    lightSquare = !lightSquare;
                }
            }
        };
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void animate(){
        panel.repaint();
    }
}
