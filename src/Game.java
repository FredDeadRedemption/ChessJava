import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static Chessboard chessboard;

    public static void main(String[] args) throws IOException {
        chessboard = new Chessboard();

        resetGameState();
    }
    public static Boolean whiteToMove = true; // TODO remove
    public static List<Integer> legalSquares = new ArrayList<>(); // TODO make a property of piece
    public static Boolean legalSquaresLoaded = false; // for animating in Chessboard.paint()
    public static int startSquare; // first click / public cuz also used in Chessboard.paint()
    private static Boolean hasClicked = false;
    static List<List<Integer>> blackMoves = new ArrayList<>(15); // array of lists, index corresponding to piece

    private static void resetClick(){
        hasClicked = false;
        startSquare = 9;
        legalSquaresLoaded = false;
        chessboard.animate();
    }

    private static void evilPlay(){
        generateAllBlackMoves();
        System.out.println(blackMoves);

        int randPieceIndex = RNGBot.getRandomNonEmptyListIndex(blackMoves);

        Piece p = pieceLookupTable[randPieceIndex];

        int randomMove = blackMoves.get(randPieceIndex).get(RNGBot.getRandomMove(blackMoves.get(randPieceIndex)));

        p.move(randomMove);

        whiteToMove = !whiteToMove;

        System.out.println("RAND PIECE: " + p + "\nRAND MOVE:" + randomMove);
    }

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
                legalSquaresLoaded = true;
                chessboard.animate();
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
                whiteToMove = !whiteToMove;
                evilPlay();
            }
            // second click invalid
            else resetClick();
        }
    }

    private static void generateAllBlackMoves(){

        blackMoves.clear();

        for(int i = 0; i < 16; i++){ // loop though black pieces

            Piece p = pieceLookupTable[i];

            if (!p.hasBeenSlaughtered){

                blackMoves.add(p.generateLegalSquares()); // add list of all moves for that piece
            } else blackMoves.add(new ArrayList<>()); // add empty list if piece is dead
        }
    }

    // All game state contained within
    public static Piece[] pieceLookupTable = new Piece[32];
    public static void resetGameState() {
        // 0-15 Black
        pieceLookupTable[0] = new Rook(56, "r");
        pieceLookupTable[1] = new Knight(57, "n");
        pieceLookupTable[2] = new Bishop(58, "b");
        pieceLookupTable[3] = new Queen(59, "q");
        pieceLookupTable[4] = new King(60, "k");
        pieceLookupTable[5] = new Bishop(61, "b");
        pieceLookupTable[6] = new Knight(62, "n");
        pieceLookupTable[7] = new Rook(63, "r");
        pieceLookupTable[8] = new Pawn(48, "p");
        pieceLookupTable[9] = new Pawn(49, "p");
        pieceLookupTable[10] = new Pawn(50, "p");
        pieceLookupTable[11] = new Pawn(51, "p");
        pieceLookupTable[12] = new Pawn(52, "p");
        pieceLookupTable[13] = new Pawn(53, "p");
        pieceLookupTable[14] = new Pawn(54, "p");
        pieceLookupTable[15] = new Pawn(55, "p");
        // 16-32 WHITE
        pieceLookupTable[16] = new Rook(0, "R");
        pieceLookupTable[17] = new Knight(1, "N");
        pieceLookupTable[18] = new Bishop(2, "B");
        pieceLookupTable[19] = new Queen(3, "Q");
        pieceLookupTable[20] = new King(4, "K");
        pieceLookupTable[21] = new Bishop(5, "B");
        pieceLookupTable[22] = new Knight(6, "N");
        pieceLookupTable[23] = new Rook(7, "R");
        pieceLookupTable[24] = new Pawn(8, "P");
        pieceLookupTable[25] = new Pawn(9, "P");
        pieceLookupTable[26] = new Pawn(10, "P");
        pieceLookupTable[27] = new Pawn(11, "P");
        pieceLookupTable[28] = new Pawn(12, "P");
        pieceLookupTable[29] = new Pawn(13, "P");
        pieceLookupTable[30] = new Pawn(14, "P");
        pieceLookupTable[31] = new Pawn(15, "P");
    }
}