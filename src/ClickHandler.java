import java.util.ArrayList;
import java.util.List;

public class ClickHandler {
    public static int startSquareAnimation = 0; // used in Chessboard.paint() if animations are loaded
    public static List<Integer> movesAnimation = new ArrayList<>(); // used in Chessboard.paint() if animations are loaded
    public static Boolean animationsLoaded = false; // tells Chessboard.paint() if animations are loaded
    private static Boolean firstClick = true;
    public static Piece firstClickedPiece = null;

    public static void handleClick(int clickedSquare) {

        // load piece clicked
        Piece clickedPiece = Logic.getPieceFromSquare(clickedSquare);

        // handle first click
        if (firstClick) {
            // if piece exists and turn to move
            if (clickedPiece != null && (clickedPiece.isWhite() == Game.whiteToMove)) {
                firstClickedPiece = clickedPiece;
                clickedPiece.generateMoves();
                startSquareAnimation = clickedSquare;
                movesAnimation = clickedPiece.moves;
                animationsLoaded = true;
                Game.chessboard.animate();
                firstClick = false;
            }
        }
        // handle second click
        if (!firstClick) {
            // if second clicked square has friendly occupant, consider that the new first click
            if (Logic.hasFriendlyOccupant(clickedSquare, firstClickedPiece)) {
                firstClickedPiece = clickedPiece;
                clickedPiece = Logic.getPieceFromSquare(clickedSquare);
                assert clickedPiece != null;
                clickedPiece.generateMoves();
                startSquareAnimation = clickedSquare;
                movesAnimation = clickedPiece.moves;
                animationsLoaded = true;
                Game.chessboard.animate();
                firstClick = false;
            }
            // if second click is valid
            else if (firstClickedPiece.moves.contains(clickedSquare)) {
                firstClickedPiece.move(clickedSquare);
                resetClick();
                firstClickedPiece = null;
            } else {
                resetClick();
            }
        }
    }
    private static void resetClick(){
        firstClick = true;
        animationsLoaded = false;
        Game.chessboard.animate();
    }
}
