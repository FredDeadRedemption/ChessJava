import java.util.Objects;

public class Piece {
    int position; // 0-63 bitboard
    String type; // P="white pawn" p="black pawn" N="white knight" k="black king"...
    boolean hasMoved; // for castle & double pawn moves
    boolean hasBeenSlaughtered; // for castle
    public Piece(int position, String type){
        this.position = position;
        this.type = type;
        this.hasMoved = false;
        this.hasBeenSlaughtered = false;
    }

    public Boolean isWhite(){
        return this.type.matches("k|q|b|n|r|p");
    }

    public static Piece[] arrayOfPieces = {
            // 0-15 BLACK
            new Piece(56, "r"),
            new Piece(57, "n"),
            new Piece(58, "b"),
            new Piece(59, "q"),
            new Piece(60, "k"),
            new Piece(61, "b"),
            new Piece(62, "n"),
            new Piece(63, "r"),
            new Piece(48, "p"),
            new Piece(49, "p"),
            new Piece(50, "p"),
            new Piece(51, "p"),
            new Piece(52, "p"),
            new Piece(53, "p"),
            new Piece(54, "p"),
            new Piece(55, "p"),
            // 16-32 WHITE
            new Piece(0, "R"),
            new Piece(1, "N"),
            new Piece(2, "B"),
            new Piece(3, "Q"),
            new Piece(4, "K"),
            new Piece(5, "B"),
            new Piece(6, "N"),
            new Piece(7, "R"),
            new Piece(8, "P"),
            new Piece(9, "P"),
            new Piece(10, "P"),
            new Piece(11, "P"),
            new Piece(12, "P"),
            new Piece(13, "P"),
            new Piece(14, "P"),
            new Piece(15, "P"),

    };

}
