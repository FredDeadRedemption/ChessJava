import java.util.List;
import java.util.Objects;

public class Logic {

    public static Piece getPieceFromSquare(int square){
        for (Piece p : Game.arrayOfPieces) {
            if (p.position == square) {return p;}
        }
        return null;
    }

    public static Boolean hasEvilOccupant(int square) {
        Piece p = getPieceFromSquare(square);

        return p != null && !p.turnToMove();
    }

    public static Boolean hasFriendlyOccupant(int square) {
        Piece p = getPieceFromSquare(square);

        return p != null && p.turnToMove();
    }

    public static Boolean hasOccupant(int square) {
        Piece p = getPieceFromSquare(square);

        return p != null;
    }

    public static List<Integer> filterLegalSquares(List<Integer> legalSquares) {

        // Filter values outside the board
        for (int i = legalSquares.size() - 1; i >= 0; i--) {
            int square = legalSquares.get(i);
            if (square < 0 || square > 63 || Logic.hasFriendlyOccupant(square)) {
                legalSquares.remove(i);
            }
        }

        // Filter empty values
        legalSquares.removeIf(Objects::isNull);

        return legalSquares;
    }
}
