import java.io.IOException;
import java.util.List;

public class Game {
    public static Chessboard chessboard;

    public static void main(String[] args) throws IOException {
        chessboard = new Chessboard();

        resetGameState();
    }
    public static Boolean whiteToMove = true;
    public static Boolean legalSquaresLoaded = false; // for animating in Chessboard.java
    public static List<Integer> legalSquares; // for animating in Chessboard.java
    public static int startSquare; // first click / public cuz also used in Chessboard.java
    private static int targetSquare; // second click
    private static Boolean hasClicked = false;

    private static void resetClick(){
        hasClicked = false;
        startSquare = 9;
        targetSquare = 999;
    }

    public static void handleClick(int clickedSquare){
        System.out.println("CLICKED");
        // first click
        if(!hasClicked) {
            // load piece
            Piece p = Logic.getPieceFromSquare(clickedSquare);

            // if piece is valid
            if (p != null && p.turnToMove()) {
                // load square, generate moves, animate moves on board
                startSquare = p.position;
                legalSquares = p.generateLegalSquares();
                legalSquaresLoaded = true;
                chessboard.animate();
                hasClicked = true;
                System.out.println(p.generateLegalSquares());
            } else resetClick(); // if piece is invalid
        }
        // second click
        if (hasClicked){
            // load piece
            targetSquare = clickedSquare;

            // choose new start square instead if piece is same color as first piece
            if (Logic.hasFriendlyOccupant(targetSquare)){
                startSquare = targetSquare;
                targetSquare = 999;

                Piece p = Logic.getPieceFromSquare(startSquare);

                assert p != null;
                legalSquares = p.generateLegalSquares();
                legalSquaresLoaded = true;
                chessboard.animate();
                hasClicked = true;
            }
            // second click valid
            else if(legalSquares.contains(targetSquare)){
                // load piece
                Piece p = Logic.getPieceFromSquare(startSquare);

                assert p != null;
                p.move(targetSquare);
                resetClick();
                legalSquaresLoaded = false;
                chessboard.animate();
            }
            // second click invalid
            else {
                resetClick();
                legalSquaresLoaded = false;
                chessboard.animate();
            }
        }
    }

    // All game state contained within
    public static Piece[] arrayOfPieces = new Piece[32];
    public static void resetGameState() {
        // 0-15 Black
        arrayOfPieces[0] = new Rook(56, "r");
        arrayOfPieces[1] = new Knight(57, "n");
        arrayOfPieces[2] = new Bishop(58, "b");
        arrayOfPieces[3] = new Queen(59, "q");
        arrayOfPieces[4] = new King(60, "k");
        arrayOfPieces[5] = new Bishop(61, "b");
        arrayOfPieces[6] = new Knight(62, "n");
        arrayOfPieces[7] = new Rook(63, "r");
        arrayOfPieces[8] = new Pawn(48, "p");
        arrayOfPieces[9] = new Pawn(49, "p");
        arrayOfPieces[10] = new Pawn(50, "p");
        arrayOfPieces[11] = new Pawn(51, "p");
        arrayOfPieces[12] = new Pawn(52, "p");
        arrayOfPieces[13] = new Pawn(53, "p");
        arrayOfPieces[14] = new Pawn(54, "p");
        arrayOfPieces[15] = new Pawn(55, "p");
        // 16-32 WHITE
        arrayOfPieces[16] = new Rook(0, "R");
        arrayOfPieces[17] = new Knight(1, "N");
        arrayOfPieces[18] = new Bishop(2, "B");
        arrayOfPieces[19] = new Queen(3, "Q");
        arrayOfPieces[20] = new King(4, "K");
        arrayOfPieces[21] = new Bishop(5, "B");
        arrayOfPieces[22] = new Knight(6, "N");
        arrayOfPieces[23] = new Rook(7, "R");
        arrayOfPieces[24] = new Pawn(8, "P");
        arrayOfPieces[25] = new Pawn(9, "P");
        arrayOfPieces[26] = new Pawn(10, "P");
        arrayOfPieces[27] = new Pawn(11, "P");
        arrayOfPieces[28] = new Pawn(12, "P");
        arrayOfPieces[29] = new Pawn(13, "P");
        arrayOfPieces[30] = new Pawn(14, "P");
        arrayOfPieces[31] = new Pawn(15, "P");
    }
}