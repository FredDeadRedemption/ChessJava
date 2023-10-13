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
        Piece piece = getPieceFromSquare(square);
        String colors;
        if (!Game.whiteToMove) {
            colors = "[kqbnrp]";
        } else colors = "[KGBNRP]";

        if (piece != null) {
            return piece.type.matches(colors);
        }
        return false;
    }

    public static Boolean hasFriendlyOccupant(int square) {
        Piece piece = getPieceFromSquare(square);
        String colors;
        if (!Game.whiteToMove) {
            colors = "[KGBNRP]";
        } else colors = "[kqbnrp]";

        if (piece != null) {
            return piece.type.matches(colors);
        }
        return false;
    }

    public static Boolean hasNoOccupant(int square) {
        Piece piece = getPieceFromSquare(square);

        return piece == null;
    }

    public static Boolean pieceIsInTheWay(int square){
        Piece j = getPieceFromSquare(square);
        return j != null;
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
