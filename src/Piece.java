import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    int position; // 0-63 bitboard position
    List<Integer> offsets; // to determine move pattern
    List<Integer> moves; // TODO: maybe delete this make a moves class
    String type; // P="white pawn" p="black pawn" N="white knight" k="black king"...
    boolean hasMoved; // for castling rights & double pawn moves
    boolean hasBeenSlaughtered; // for castling rights & rendering // TODO: just delete the piece object itself from the state array maybe
    public Piece(int position, String type){
        this.position = position;
        this.type = type;
        this.hasMoved = false;
        this.hasBeenSlaughtered = false;
        this.offsets = new ArrayList<>();
        this.moves = new ArrayList<>();
    }

    public boolean isWhite(){
        return this.type.matches("[KQBNRP]");
    }

    public abstract void generateMoves();

    public List<Integer> generateSlidingMoves(int offset){ // used for rook - bishop - queen

        List<Integer> moves = new ArrayList<>();

        // if piece is already on edge based on its offset break;
        if (Util.squareOnEdge(offset, this.position)) return moves;

        // generate each sliding moves for in offsets direction
        for(int i = 1; i < 8; i++){

            moves.add(this.position + offset * i);

            if (Util.squareOnEdge(offset, this.position + offset * i)) break; // if move hits edge stop
            if (Util.hasOccupant(this.position + offset * i)) break; // if move hits occupied square stop

        }
        return moves;
    }

    public void move(int targetSquare) {
        // if enemy, kill it
        if (Util.hasEvilOccupant(targetSquare, this)){
            Piece p = Util.getPieceFromSquare(targetSquare);
            assert p != null;
            p.kill();
        }
        // make move
        this.position = targetSquare;
        this.hasMoved = true;
    }

    public void kill(){
        this.position = 99;
        this.hasBeenSlaughtered = true;
    }
}
