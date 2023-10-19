import java.util.List;

public class Logic {

    // collection of logical utility methods

    public static Piece getPieceFromSquare(int square){
        for (Piece p : Game.state) {
            if (p.position == square) {return p;}
        }
        return null;
    }

    public static Boolean hasEvilOccupant(int square, Piece p) {
        Piece targetp = getPieceFromSquare(square);

        if (targetp != null && p != null) {
            return ((targetp.isWhite() && !p.isWhite()) || (!targetp.isWhite() && p.isWhite()));
        }
        return false;
    }

    public static Boolean hasFriendlyOccupant(int square, Piece p) {
        Piece targetp = getPieceFromSquare(square);

        if (targetp != null && p != null) {
            return ((targetp.isWhite() && p.isWhite()) || (!targetp.isWhite() && !p.isWhite()));
        }
        return false;
    }

    public static Boolean hasOccupant(int square) {
        Piece p = getPieceFromSquare(square);

        return p != null;
    }

    public static Boolean squareOnEdge(int offset, int square){
        return switch (offset) {
            // NW
            case 7 -> square > 56 || square % 8 == 0; // top edge / left edge
            // NE
            case 9 -> square > 56 || square % 8 == 7; // top edge / right edge
            // SE
            case -7 -> square < 8 || square % 8 == 7; // bottom edge / right edge
            // SW
            case -9 -> square < 8 || square % 8 == 0; // bottom edge / left edge
            // N
            case 8 -> square > 56; // top edge
            // S
            case -8 -> square < 8; // bottom edge
            // E
            case 1 -> square % 8 == 7; // right edge
            // W
            case -1 -> square % 8 == 0; // left edge
            default -> false;
        };
    }

    public static List<Integer> filterMoves(List<Integer> moves, Piece p) {

        // Filter moves outside the board + moves with friendly pieces
        for (int i = moves.size() - 1; i >= 0; i--) {
            int square = moves.get(i);
            if (square < 0 || square > 63 || Logic.hasFriendlyOccupant(square, p)) {
                moves.remove(i);
            }
        }
        return moves;
    }
}
