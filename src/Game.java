import java.io.IOException;
import java.util.ArrayList;

public class Game {

    public static Chessboard chessboard;

    public static void main(String[] args) throws IOException {
        resetGameState();

        chessboard = new Chessboard();

        TimeMeasurementTest.run();
    }

    //                     Ladies and Gents,
    //                   A  B  C  D  E  F  G  H
    //                ┌─────────────────────────┐
    //              8 │ 56 57 58 59 60 61 62 63 │ 8
    //              7 │ 48 49 50 51 52 53 54 55 │ 7
    //              6 │ 40 41 42 43 44 45 46 47 │ 6
    //              5 │ 32 33 34 35 36 37 38 39 │ 5
    //              4 │ 24 25 26 27 28 29 30 31 │ 4
    //              3 │ 16 17 18 19 20 21 22 23 │ 3
    //              2 │  8  9 10 11 12 13 14 15 │ 2
    //              1 │  0  1  2  3  4  5  6  7 │ 1
    //                └─────────────────────────┘
    //                   A  B  C  D  E  F  G  H

    // ALL game state contained below |
    //                                V
    //
    // the idea is to be able to take a snapshot of the game state. making it easier to search depths and calculate inCheck()
    // without having to make an unMove method
    //
    // note: state also functions as a lookup-table for individual pieces

    public static boolean whiteToMove = true; // flips on successful move
    public static Piece[] state = new Piece[32];

    public static void resetGameState() {
        // 0-15 Black
        state[0] = new Rook(56, "r");
        state[1] = new Knight(57, "n");
        state[2] = new Bishop(58, "b");
        state[3] = new Queen(59, "q");
        state[4] = new King(60, "k");
        state[5] = new Bishop(61, "b");
        state[6] = new Knight(62, "n");
        state[7] = new Rook(63, "r");
        state[8] = new Pawn(48, "p");
        state[9] = new Pawn(49, "p");
        state[10] = new Pawn(50, "p");
        state[11] = new Pawn(51, "p");
        state[12] = new Pawn(52, "p");
        state[13] = new Pawn(53, "p");
        state[14] = new Pawn(54, "p");
        state[15] = new Pawn(55, "p");
        // 16-32 WHITE
        state[16] = new Rook(0, "R");
        state[17] = new Knight(1, "N");
        state[18] = new Bishop(2, "B");
        state[19] = new Queen(3, "Q");
        state[20] = new King(4, "K");
        state[21] = new Bishop(5, "B");
        state[22] = new Knight(6, "N");
        state[23] = new Rook(7, "R");
        state[24] = new Pawn(8, "P");
        state[25] = new Pawn(9, "P");
        state[26] = new Pawn(10, "P");
        state[27] = new Pawn(11, "P");
        state[28] = new Pawn(12, "P");
        state[29] = new Pawn(13, "P");
        state[30] = new Pawn(14, "P");
        state[31] = new Pawn(15, "P");
    }

    // TODO: this is expensive af so maybe just make a class for moves and make move.unmake()
    public static Piece[] deepCloneState(Piece[] originalState) {
        Piece[] clonedState = new Piece[originalState.length];

        for (int i = 0; i < originalState.length; i++) {
            if (originalState[i] instanceof Rook) {
                clonedState[i] = new Rook(originalState[i].position, originalState[i].type);
            } else if (originalState[i] instanceof Knight) {
                clonedState[i] = new Knight(originalState[i].position, originalState[i].type);
            } else if (originalState[i] instanceof Bishop) {
                clonedState[i] = new Bishop(originalState[i].position, originalState[i].type);
            } else if (originalState[i] instanceof Queen) {
                clonedState[i] = new Queen(originalState[i].position, originalState[i].type);
            } else if (originalState[i] instanceof King) {
                clonedState[i] = new King(originalState[i].position, originalState[i].type);
            } else if (originalState[i] instanceof Pawn) {
                clonedState[i] = new Pawn(originalState[i].position, originalState[i].type);
            }
            clonedState[i].hasMoved = originalState[i].hasMoved;
            clonedState[i].hasBeenSlaughtered = originalState[i].hasBeenSlaughtered;
            clonedState[i].offsets = new ArrayList<>(originalState[i].offsets);
            clonedState[i].moves = new ArrayList<>(originalState[i].moves);
        }

        return clonedState;
    }

}