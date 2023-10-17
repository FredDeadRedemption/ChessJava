import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Chessboard {
    Color movesColor = new Color(135, 206, 235, 80);
    Color attacksColor = new Color(255, 0, 0, 80);
    Color lightSquareColor = new Color(200, 200, 195);
    Color darkSquareColor = new Color(146, 143, 122);
    JPanel panel;
    JFrame frame;
    public Chessboard() throws IOException {
        // Jframe
        frame = new JFrame();
        frame.setBounds(10, 10, 526, 549);
        frame.setTitle("Skak");

        // load spritesheet imgs
        BufferedImage all = ImageIO.read(new File("src/chess.png"));
        Image[] imgs = new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        // override Jpanel paint method
        panel = new JPanel(new GridLayout(8, 8)) {
            @Override
            public void paint(Graphics g) {
                // paint board
                boolean squareIsLight = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (squareIsLight) {
                            g.setColor(lightSquareColor);
                        } else {
                            g.setColor(darkSquareColor);

                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        squareIsLight = !squareIsLight;
                    }
                    squareIsLight = !squareIsLight;
                }
                // paint legalSquares
                if (ClickHandler.movesAnimationLoaded){
                    // initial color
                    g.setColor(movesColor);
                    for (Integer legalSquare : ClickHandler.legalSquares) {
                        // highlight enemy squares
                        if (Logic.hasEvilOccupant(legalSquare, Logic.getPieceFromSquare(legalSquare))) {
                            g.setColor(attacksColor);
                        }

                        // highlight legal moves
                        g.fillRect(animationLookupTable[legalSquare].x * 64, animationLookupTable[legalSquare].y * 64, 64, 64);
                        g.setColor(movesColor);
                    }
                    // highlight selected square
                    g.setColor(attacksColor);
                    g.fillRect(animationLookupTable[ClickHandler.startSquare].x * 64, animationLookupTable[ClickHandler.startSquare].y * 64, 64, 64);
                }
                // paint pieces
                for (int i = 0; i < Game.state.length; i++) {
                    Piece p = Game.state[i];
                    if (!p.hasBeenSlaughtered) {
                        int ind = switch (p.type) {
                            case "q", "Q" -> 1;
                            case "b", "B" -> 2;
                            case "n", "N" -> 3;
                            case "r", "R" -> 4;
                            case "p", "P" -> 5;
                            default -> 0;
                        };
                        if (!p.isWhite()) {
                            ind += 6;
                        }
                        g.drawImage(imgs[ind], animationLookupTable[p.position].x * 64, animationLookupTable[p.position].y * 64, this);
                    }
                }
            }
        };
        // click event grid
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
                buttons[i][j].addActionListener(e -> ClickHandler.handleClick(clickedSquare));
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

    public void animate(){
        panel.repaint();
    }

    // maps bitboard to pixels
    private static final Square[] animationLookupTable = { // TODO make this a function
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
