import java.io.IOException;
import java.util.ArrayList;

public class Game {

    public static Chessboard chessboard;

    public static void main(String[] args) throws IOException {
        chessboard = new Chessboard();

        resetGameState();
    }

    public static Boolean whiteToMove = true; // flips on successful move

    // ALL game state contained within this array
    //
    // the idea is to be able to take a snapshot of the game state. making it easier to search depths and calculate inCheck()
    // without having to make an unMove method
    //
    // note: this also functions as a lookup-table for individual pieces
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

    // TODO: this is expensive af so maybe just make a class for moves
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