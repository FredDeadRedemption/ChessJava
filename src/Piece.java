import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    int position; // 0-63 bitboard position
    List<Integer> offsets; // to determines move pattern
    List<Integer> moves; // TODO: delete this make a moves class
    String type; // P="white pawn" p="black pawn" N="white knight" k="black king"...
    boolean hasMoved; // for castle & double pawn moves
    boolean hasBeenSlaughtered; // for castle & rendering
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

    public void kill(){
        this.position = 70;
        this.hasBeenSlaughtered = true;
    }

    public void generateMoves(){
        System.out.println("yeehaw");
    }

    // used for rook - bishop - queen
    public List<Integer> generateSlidingMoves(int offset){

        List<Integer> moves = new ArrayList<>();

        // if piece is already on edge based on its offset
        if (Logic.squareOnEdge(offset, this.position)) return moves;

        // generate each sliding moves for offset
        for(int i = 1; i < 8; i++){

            moves.add(this.position + offset * i);

            if (Logic.squareOnEdge(offset, this.position + offset * i)) break; // if move hits edge
            if (Logic.hasOccupant(this.position + offset * i)) break; // if move hits occupied square

        }
        return moves;
    }

    public void move(int targetSquare) {
        // if enemy, kill it
        if (Logic.hasEvilOccupant(targetSquare, this)){
            Piece p = Logic.getPieceFromSquare(targetSquare);
            assert p != null;
            p.kill();
        }
        // make move
        this.position = targetSquare;
        this.hasMoved = true;
    }
}
