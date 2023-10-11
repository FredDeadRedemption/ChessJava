import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Chessboard {

    JPanel panel;
    public Chessboard() throws IOException {
        // init Jframe
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 526, 549);
        frame.setTitle("Skak");

        // load spritesheet imgs
        BufferedImage all= ImageIO.read(new File("src/chess.png"));
        Image[] imgs =new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }

        // override Jpanel paint method to draw chessboard & pieces
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
                    g.drawImage(imgs[ind], animationGrid[p.position].x * 64, animationGrid[p.position].y * 64, this);
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

    // grid to map bitboard values to drawable pixel dimensions on Jpanel
    private static final Square[] animationGrid = {
            new Square(0, 7),
            new Square(1, 7),
            new Square(2, 7),
            new Square(3, 7),
            new Square(4, 7),
            new Square(5, 7),
            new Square(6, 7),
            new Square(7, 7),
            new Square(0, 6),
            new Square(1, 6),
            new Square(2, 6),
            new Square(3, 6),
            new Square(4, 6),
            new Square(5, 6),
            new Square(6, 6),
            new Square(7, 6),
            new Square(0, 5),
            new Square(1, 5),
            new Square(2, 5),
            new Square(3, 5),
            new Square(4, 5),
            new Square(5, 5),
            new Square(6, 5),
            new Square(7, 5),
            new Square(0, 4),
            new Square(1, 4),
            new Square(2, 4),
            new Square(3, 4),
            new Square(4, 4),
            new Square(5, 4),
            new Square(6, 4),
            new Square(7, 4),
            new Square(0, 3),
            new Square(1, 3),
            new Square(2, 3),
            new Square(3, 3),
            new Square(4, 3),
            new Square(5, 3),
            new Square(6, 3),
            new Square(7, 3),
            new Square(0, 2),
            new Square(1, 2),
            new Square(2, 2),
            new Square(3, 2),
            new Square(4, 2),
            new Square(5, 2),
            new Square(6, 2),
            new Square(7, 2),
            new Square(0, 1),
            new Square(1, 1),
            new Square(2, 1),
            new Square(3, 1),
            new Square(4, 1),
            new Square(5, 1),
            new Square(6, 1),
            new Square(7, 1),
            new Square(0, 0),
            new Square(1, 0),
            new Square(2, 0),
            new Square(3, 0),
            new Square(4, 0),
            new Square(5, 0),
            new Square(6, 0),
            new Square(7, 0),
    };

}
