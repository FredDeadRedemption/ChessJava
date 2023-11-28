package main;
import javax.swing.*;

public class View {
    CustomPanel panel;
    JFrame frame;
    public View(){
        // Jframe
        frame = new JFrame();
        frame.setBounds(10, 10, 526, 549);
        frame.setTitle("Skak");

        // Custom Jpanel
        panel = new CustomPanel();
        // add 64 buttons + action-listeners to Jpanel
        JButton[][] buttons = new JButton[8][8];
        int x = 63;
        for (int i = 0; i < 8; i++) {
            int row = 0;
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new JButton();
                panel.add(buttons[i][j]);
                buttons[i][j].setOpaque(false);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setBorderPainted(false);

                row = i + 1;
                final int clickedSquare = x + j - 7;
                buttons[i][j].addActionListener(e -> Controller.handleClick(clickedSquare));
            }
            // retard math
            if (row != 7) {
                x = x - 8;
            } else {
                x = 7;
            }
        }
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void animateMoves(Piece p){
        panel.setAnimationData(p);
        panel.animationsFlag = true;
        panel.repaint();
    }

    public void clear(){
        panel.animationsFlag = false;
        panel.repaint();
    }
}
