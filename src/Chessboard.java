import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Chessboard {

    JPanel panel;
    public void init() throws IOException {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 526, 549);
        frame.setTitle("Skak");

        BufferedImage all= ImageIO.read(new File("src/chess.png"));
        Image[] imgs =new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
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
                for (int i = 0; i < Piece.arrayOfPieces.length; i++) {
                    Piece p = Piece.arrayOfPieces[i];
                    int ind = switch (p.type) {
                        case "q", "Q" -> 1;
                        case "b", "B" -> 2;
                        case "n", "N" -> 3;
                        case "r", "R" -> 4;
                        case "p", "P" -> 5;
                        default -> 0;
                    };
                    if(p.isWhite()) { ind += 6;}
                    g.drawImage(imgs[ind], p.position * 64, p.position * 64, this);
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

    public static {}[] animationGrid[]
}
