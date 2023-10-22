import java.util.ArrayList;
import java.util.List;

public class ClickHandler {

    public static int startSquareAnimation = 0; // used in Chessboard.paint()
    public static List<Integer> movesAnimation = new ArrayList<>(); // used in Chessboard.paint()
    public static boolean animationsLoaded = false; // tells Chessboard.paint() if animations are loaded

    public static void handleClick(int clickedSquare) {
        // load piece
        Piece clickedPiece = Logic.getPieceFromSquare(clickedSquare);
        // handle the business
        if (firstClick) {
            handleFirstClick(clickedPiece, clickedSquare);
        } else {
            handleSecondClick(clickedPiece, clickedSquare);
        }
    }

    private static Piece firstClickedPiece = null; // keeps track on the first piece that was clicked

    private static boolean firstClick = true;

    private static void handleFirstClick(Piece clickedPiece, int clickedSquare) {
        // if piece clicked is valid
        if (clickedPiece != null && (clickedPiece.isWhite() == Game.whiteToMove)) {
            // assign first clicked piece
            firstClickedPiece = clickedPiece;
            // animate moves
            clickedPiece.generateMoves();
            movesAnimation = clickedPiece.moves;
            startSquareAnimation = clickedSquare;
            animationsLoaded = true;
            Game.chessboard.animate();
            // flip click
            firstClick = false;
        }
    }

    private static void handleSecondClick(Piece clickedPiece, int clickedSquare) {
        // if second clicked piece is another friendly piece consider it the new first click
        if (Logic.hasFriendlyOccupant(clickedSquare, firstClickedPiece)) {
           handleFirstClick(clickedPiece, clickedSquare);
        }
        // if second click is valid execute the move
        else if (firstClickedPiece.moves.contains(clickedSquare)) {
            firstClickedPiece.move(clickedSquare);
            resetClick();
            Game.whiteToMove = !Game.whiteToMove;
        }
        // if second click is invalid
        else {
            resetClick();
        }
    }

    private static void resetClick() {
        firstClick = true;
        animationsLoaded = false;
        Game.chessboard.animate();
    }
}
