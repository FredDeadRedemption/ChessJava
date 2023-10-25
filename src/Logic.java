import java.util.List;

public class Logic {

    // collection of logical utility methods

    public static Piece getPieceFromSquare(int square){
        for (Piece p : Game.state) {
            if (p.position == square) {return p;}
        }
        return null;
    }

    public static boolean hasEvilOccupant(int square, Piece p) {
        Piece targetp = getPieceFromSquare(square);

        if (targetp != null && p != null) {
            return ((targetp.isWhite() && !p.isWhite()) || (!targetp.isWhite() && p.isWhite()));
        }
        return false;
    }

    public static boolean hasFriendlyOccupant(int square, Piece p) {
        Piece targetp = getPieceFromSquare(square);

        if (targetp != null && p != null) {
            return ((targetp.isWhite() && p.isWhite()) || (!targetp.isWhite() && !p.isWhite()));
        }
        return false;
    }

    public static boolean hasOccupant(int square) {
        Piece p = getPieceFromSquare(square);

        return p != null;
    }

    // int to boolean casting not permitted in Java.
    public static boolean squareOnEdge(int offset, int square){
        return switch (offset) {
            // NW
            case 7:
                yield (TOP_EDGE[square] == 1 || LEFT_EDGE[square] == 1); // top edge / left edge
            // NE
            case 9:
                yield (TOP_EDGE[square] == 1 || RIGHT_EDGE[square] == 1); // top edge / right edge
            // SE
            case -7:
                yield (BOTTOM_EDGE[square] == 1 || RIGHT_EDGE[square] == 1);// bottom edge / right edge
            // SW
            case -9:
                yield (BOTTOM_EDGE[square] == 1 || LEFT_EDGE[square] == 1);// bottom edge / left edge
            // N
            case 8:
                yield TOP_EDGE[square] == 1;// top edge
            // S
            case -8:
                yield BOTTOM_EDGE[square] == 1;// bottom edge
            // E
            case 1:
                yield RIGHT_EDGE[square] == 1;// right edge
            // W
            case -1:
                yield LEFT_EDGE[square] == 1;// left edge
            default:
                throw new IllegalStateException("Unexpected offset value: " + offset);
        };
    }

    // 8-bit Lookup Tables for instant edge detection without any comparisons.

    public static final byte[] BOTTOM_EDGE = {
            1, 1, 1, 1, 1, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
    };

    public static final byte[] TOP_EDGE = {
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            1, 1, 1, 1, 1, 1, 1, 1,
    };

    public static final byte[] LEFT_EDGE = {
            1, 0, 0, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0,
    };

    public static final byte[] RIGHT_EDGE = {
            0, 0, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
    };


    public static List<Integer> filterMoves(List<Integer> moves, Piece p) {

        // Filter moves outside the board + moves with friendly pieces
        for (int i = moves.size() - 1; i >= 0; i--) {
            int square = moves.get(i);
            if (square < 0 || square > 63 || Logic.hasFriendlyOccupant(square, p)) {
                moves.remove(i);
            }
        }

        // filter moves that uncheck
        Piece[] snapshot = Game.deepCloneState(Game.state);

        return moves;
    }
}
