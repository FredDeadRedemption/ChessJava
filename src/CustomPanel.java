import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomPanel extends JPanel {
    Color homeColor = new Color(255, 150, 0, 80);
    Color movesColor = new Color(135, 206, 235, 80);
    Color attacksColor = new Color(255, 0, 0, 80);
    Color lightSquareColor = new Color(200, 200, 195);
    Color darkSquareColor = new Color(146, 143, 122);
    Image[] spriteSheet;
    List<Integer> movesAnimation; // list off which squares to paint
    int homeSquareAnimation; // home square to paint
    boolean animationsFlag; // when false, only the board will be painted

    public CustomPanel() throws IOException {

        // set 8x8 layout
        setLayout(new GridLayout(8, 8));

        // load sub-images from spritesheet
        BufferedImage all = ImageIO.read(new File("src/chess.png"));
        spriteSheet = new Image[12];
        int index=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                spriteSheet[index] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                index++;
            }
        }
    }

    public void setAnimationData(Piece p){
        this.movesAnimation = p.moves;
        this.homeSquareAnimation = p.position;
    }

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

        // paint moves
        if (animationsFlag){
            // set initial color
            g.setColor(movesColor);
            for (Integer move : movesAnimation) {
                // highlight enemy occupied squares
                if (Util.hasEvilOccupant(move, Util.getPieceFromSquare(homeSquareAnimation))) {
                    g.setColor(attacksColor);
                }

                // highlight moves
                g.fillRect(animationLookupTable[move].x * 64, animationLookupTable[move].y * 64, 64, 64);
                g.setColor(movesColor);
            }
            // highlight home square
            g.setColor(homeColor);
            g.fillRect(animationLookupTable[homeSquareAnimation].x * 64, animationLookupTable[homeSquareAnimation].y * 64, 64, 64);
        }

        // paint pieces
        for (int i = 0; i < Game.state.pieces.length; i++) {
            Piece p = Game.state.pieces[i];
            assert p != null;
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
                g.drawImage(spriteSheet[ind], animationLookupTable[p.position].x * 64, animationLookupTable[p.position].y * 64, this);
            }
        }
    }

    // maps bitboard to pixels
    private static final Square[] animationLookupTable = { // TODO: make this a static List<Map<Integer, Integer>>>
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
