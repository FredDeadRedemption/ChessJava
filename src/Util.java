import java.util.List;

public class Util {

    public static Piece getPieceFromSquare(int square){
        for (Piece p : Game.state.pieces) {
            if (p.position == square) {return p;}
        }
        return null;
    }

    public static boolean hasEvilOccupant(int square, Piece friendlyPiece) {
        Piece pieceOnSquare = getPieceFromSquare(square);

        if (pieceOnSquare != null && friendlyPiece != null) {
            return ((pieceOnSquare.isWhite() && !friendlyPiece.isWhite()) || (!pieceOnSquare.isWhite() && friendlyPiece.isWhite()));
        }
        return false;
    }

    public static boolean hasFriendlyOccupant(int square, Piece friendlyPiece) {
        Piece pieceOnSquare = getPieceFromSquare(square);

        if (pieceOnSquare != null && friendlyPiece != null) {
            return ((pieceOnSquare.isWhite() && friendlyPiece.isWhite()) || (!pieceOnSquare.isWhite() && !friendlyPiece.isWhite()));
        }
        return false;
    }

    public static boolean hasOccupant(int square) {
        Piece pieceOnSquare = getPieceFromSquare(square);

        return pieceOnSquare != null;
    }

    // checks if a square is on the edge based on offset.
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

    // TODO: use these lookup tables to represent board-state instead
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
            if (square < 0 || square > 63 || Util.hasFriendlyOccupant(square, p)) {
                moves.remove(i);
            }
        }
        // TODO: filter out checking your own king
        /*
        how to filter out moves that check the friendly king

        step 1. deep clone the current game state in a snapshot-state

        step 2. make a moves from the piece.moves list

        step 3. update opposing pieces moves (without filtering moves the check their own king)

        step 4. if the king is now in check remove the move that was made from the moves list

        step 5. deep clone the snapshot-state in games current state

        step 6. return filtered moves from this function overriding moves for that piece in current state

        Continue step 1-6 for all the state.pieces.moves
         */

        return moves;
    }
}
