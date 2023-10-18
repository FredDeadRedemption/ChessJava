import java.util.ArrayList;
import java.util.List;

public class ClickHandler {
    public static int startSquareAnimation; // used in Chessboard.paint() if animations are loaded
    public static List<Integer> movesAnimation = new ArrayList<>(); // used in Chessboard.paint() if animations are loaded
    public static Boolean animationsLoaded = false; // tells Chessboard.paint() if animations are loaded
    private static Boolean firstClick = true;
    public static Piece firstClickPiece = null;

    public static void handleClick(int clickedSquare) {

        Piece p = Logic.getPieceFromSquare(clickedSquare);

        if (firstClick) {
            if (p != null && (p.isWhite() == Game.whiteToMove)) {
                firstClickPiece = p;
                startSquareAnimation = p.position;
                p.generateMoves();
                movesAnimation = p.moves;
                animationsLoaded = true;
                Game.chessboard.animate();
                firstClick = false;
            }
        }
        if (!firstClick) {
            if (Logic.hasFriendlyOccupant(clickedSquare, firstClickPiece)) {
                firstClickPiece = p;
                startSquareAnimation = clickedSquare;
                p = Logic.getPieceFromSquare(clickedSquare);
                assert p != null;
                p.generateMoves();
                movesAnimation = p.moves;
                animationsLoaded = true;
                Game.chessboard.animate();
                firstClick = false;
            } else if (firstClickPiece.moves.contains(clickedSquare)) {
                firstClickPiece.move(clickedSquare);
                resetClick();
                firstClickPiece = null;
            } else {
                resetClick();
            }
        }
    }
    private static void resetClick(){
        firstClick = true;
        startSquareAnimation = 9;
        animationsLoaded = false;
        Game.chessboard.animate();
    }
}
