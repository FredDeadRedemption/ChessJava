public class Controller {

    public static void handleClick(int clickedSquare) {
        // load piece
        Piece clickedPiece = Util.getPieceFromSquare(clickedSquare);
        // handle the bizness
        if (firstClick) {
            handleFirstClick(clickedPiece);
        } else {
            handleSecondClick(clickedPiece, clickedSquare);
        }
    }

    private static Piece firstClickedPiece = null; // stores the first piece that was clicked

    private static boolean firstClick = true; // flips on click

    private static void handleFirstClick(Piece clickedPiece) {
        // if piece clicked is valid & turn to move
        if (clickedPiece != null && (clickedPiece.isWhite() == Game.state.whiteToMove)) {
            // assign first clicked piece
            firstClickedPiece = clickedPiece;
            // generate moves
            clickedPiece.generateMoves();
            // animate moves
            Game.view.animateMoves(clickedPiece);
            // flip click
            firstClick = false;
        }
    }

    private static void handleSecondClick(Piece clickedPiece, int clickedSquare) {
        // if second clicked piece is another friendly piece consider it the new first click
        if (Util.hasFriendlyOccupant(clickedSquare, firstClickedPiece)) {
           handleFirstClick(clickedPiece);
        }
        // if second click is valid execute the move
        else if (firstClickedPiece.moves.contains(clickedSquare)) {
            // move
            firstClickedPiece.move(clickedSquare);
            // reset & switch turn
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
        Game.view.clear();
    }
}
