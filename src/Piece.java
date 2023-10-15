import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
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
        return this.type.matches("[kqbnrp]");
    }

    public void kill(){
        this.position = 70;
        this.hasBeenSlaughtered = true;
    }

    public List<Integer> generateLegalSquares(){
        return new ArrayList<>();
    }

    public boolean turnToMove() {
        if(Game.whiteToMove && this.type.matches("[KQBNRP]")){
            return true;
        } else return !Game.whiteToMove && this.type.matches("[kqbnrp]");
    }

    public void move(int targetSquare) {
        // capture
        if (Logic.hasEvilOccupant(targetSquare)){
            Piece p = Logic.getPieceFromSquare(targetSquare);
            assert p != null;
            p.kill();
        }
        // make move
        this.position = targetSquare;
        this.hasMoved = true;
        Game.chessboard.animate();
        Game.whiteToMove = !Game.whiteToMove;
    }
}
