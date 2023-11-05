import java.util.ArrayList;
import java.util.List;

public class ClickHandler {

    // this is the controller where all the action happens
    public static int startSquareAnimation = 0; // used in Chessboard.paint()
    public static List<Integer> movesAnimation = new ArrayList<>(); // used in Chessboard.paint()
    public static boolean animationsLoaded = false; // tells Chessboard.paint() if animations are loaded

    public static void handleClick(int clickedSquare) {
        // load piece
        Piece clickedPiece = Util.getPieceFromSquare(clickedSquare);
        // handle the bizness
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
        if (clickedPiece != null && (clickedPiece.isWhite() == Game.state.whiteToMove)) {
            // assign first clicked piece
            firstClickedPiece = clickedPiece;
            // animate moves
            clickedPiece.generateMoves();
            movesAnimation = clickedPiece.moves;
            startSquareAnimation = clickedSquare;
            animationsLoaded = true;
            Game.view.animate();
            // flip click
            firstClick = false;
        }
    }

    private static void handleSecondClick(Piece clickedPiece, int clickedSquare) {
        // if second clicked piece is another friendly piece consider it the new first click
        if (Util.hasFriendlyOccupant(clickedSquare, firstClickedPiece)) {
           handleFirstClick(clickedPiece, clickedSquare);
        }
        // if second click is valid execute the move
        else if (firstClickedPiece.moves.contains(clickedSquare)) {
            firstClickedPiece.move(clickedSquare);
            resetClick();
            Game.state.whiteToMove = !Game.state.whiteToMove;
        }
        // if second click is invalid
        else {
            resetClick();
        }
    }

    private static void resetClick() {
        firstClick = true;
        animationsLoaded = false;
        Game.view.animate();
    }
}
