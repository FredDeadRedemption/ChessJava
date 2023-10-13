import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        Chessboard chessboard = new Chessboard();

        resetGameState();

        chessboard.animate();

    }

    public static void handleClick(int square){
        Piece choosenPiece = Logic.getPieceFromSquare(square);

        if (choosenPiece != null) {
            System.out.println(choosenPiece.type);
            System.out.println(choosenPiece.position);
        }
    }

    // Static methods / vars
    public static Piece[] arrayOfPieces = new Piece[32];
    public static void resetGameState() {
        /*
        for (Piece p : arrayOfPieces) {
            p.position = p.initPos;
            p.type = p.initType;
            p.hasMoved = false;
            p.hasBeenSlaughtered = false;
        }
        */
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