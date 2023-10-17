import java.util.ArrayList;
import java.util.List;

public class ClickHandler {

    private static void resetClick(){
        hasClicked = false;
        startSquare = 9;
        movesAnimationLoaded = false;
        Game.chessboard.animate();
    }

    private static Boolean hasClicked = false;

    public static int startSquare; // first click / public cuz also used in Chessboard.paint()

    public static Boolean whiteToMove = true; // TODO remove
    public static List<Integer> legalSquares = new ArrayList<>(); // TODO make a property of piece
    public static Boolean movesAnimationLoaded = false; // for animating in Chessboard.paint()

    public static void handleClick(int clickedSquare){

        int targetSquare;

        // first click
        if(!hasClicked) {
            // load piece
            Piece p = Logic.getPieceFromSquare(clickedSquare);

            // if piece is valid
            if (p != null && p.turnToMove()) {
                // load square, generate moves, animate moves on board
                startSquare = p.position;
                legalSquares = p.generateLegalSquares();
                movesAnimationLoaded = true;
                Game.chessboard.animate();
                hasClicked = true;
            } else resetClick(); // if piece is invalid
        }
        // second click
        if (hasClicked){
            // load piece
            targetSquare = clickedSquare;

            // choose new start square instead if piece is same color as first piece
            if (Logic.hasFriendlyOccupant(targetSquare)){
                startSquare = targetSquare;

                Piece p = Logic.getPieceFromSquare(startSquare);

                assert p != null;
                legalSquares = p.generateLegalSquares();
                movesAnimationLoaded = true;
                Game.chessboard.animate();
                hasClicked = true;
            }
            // second click valid
            else if(legalSquares.contains(targetSquare)){
                // load piece
                Piece p = Logic.getPieceFromSquare(startSquare);

                assert p != null;
                p.move(targetSquare);
                resetClick();
                movesAnimationLoaded = false;
                Game.chessboard.animate();
                whiteToMove = !whiteToMove;
                Game.evilPlay();
            }
            // second click invalid
            else resetClick();
        }
    }
}
