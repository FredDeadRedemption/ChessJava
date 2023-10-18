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
    public static List<Integer> movesAnimation = new ArrayList<>(); // for animating in Chessboard.paint()
    public static Boolean movesAnimationLoaded = false; // for animating in Chessboard.paint()

    public static void handleClick(int clickedSquare){

        int targetSquare;
        Piece p = null;

        // handles first click
        if(!hasClicked) {
            // load piece
            p = Logic.getPieceFromSquare(clickedSquare);

            // if piece is valid
            if (p != null && (p.isWhite() && Game.whiteToMove)) {
                // load square, generate moves, animate moves on board
                startSquare = p.position;
                p.generateMoves();
                System.out.println("MOVES: " + p.moves);
                movesAnimation = p.moves;
                movesAnimationLoaded = true;
                Game.chessboard.animate();
                hasClicked = true;
            } else resetClick(); // if piece is invalid
        }
        // handles second click
        if (hasClicked){
            System.out.println(p);
            // load target square
            targetSquare = clickedSquare;

            // load piece
            p = Logic.getPieceFromSquare(startSquare);

            // choose new start square instead if piece is same color as first piece
            if (Logic.hasFriendlyOccupant(targetSquare, p)){
                System.out.println("FRIENDLY DUDE HERE");
                startSquare = targetSquare;

                p = Logic.getPieceFromSquare(startSquare);

                assert p != null;
                p.generateMoves();
                movesAnimation = p.moves;
                movesAnimationLoaded = true;
                Game.chessboard.animate();
                hasClicked = true;
            }
            // second click valid
            else {
                assert p != null;
                if(p.moves.contains(targetSquare)){
                    // load piece
                    p = Logic.getPieceFromSquare(startSquare);

                    assert p != null;
                    p.move(targetSquare);
                    resetClick();
                    movesAnimationLoaded = false;
                    Game.chessboard.animate();
                    Game.whiteToMove = !Game.whiteToMove;
                    Game.evilPlay();
                }
                // second click invalid
                else resetClick();
            }
        }
    }
}
