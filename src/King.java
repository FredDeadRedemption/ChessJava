import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(int position, String type) {
        super(position, type);
    }

    @Override
    public void generateMoves() {

        List<Integer> moves = new ArrayList<>();

        // right
        if (Logic.RIGHT_EDGE[this.position] != 1) {
            moves.add(this.position + 9);
            moves.add(this.position + 1);
            moves.add(this.position - 7);
        }
        // left
        if (Logic.LEFT_EDGE[this.position] != 1) {
            moves.add(this.position + 7);
            moves.add(this.position - 1);
            moves.add(this.position - 9);
        }
        // up
        if (Logic.TOP_EDGE[this.position] != 1) moves.add(this.position + 8);
        // down
        if (Logic.BOTTOM_EDGE[this.position] != 1) moves.add(this.position - 8);

        // TODO: rewrite + check if squares between king and rook are contested
        /*
        if (piece.color == "white" && !king_white.hasMoved && !rook_white2.hasMoved && !rook_white2.hasBeenCaptured && hasNoOccupance(5) && hasNoOccupance(6)) {
            legalSquares[8] = piece.position + 2;
        }
        //castle long white
        if (piece.color == "white" && !king_white.hasMoved && !rook_white.hasMoved && !rook_white.hasBeenCaptured && hasNoOccupance(1) && hasNoOccupance(2) && hasNoOccupance(3)) {
            legalSquares[9] = piece.position - 2;
        }
        //castle short black
        if (piece.color == "black" && !king_black.hasMoved && !rook_black2.hasMoved && !rook_black2.hasBeenCaptured && hasNoOccupance(61) && hasNoOccupance(62)) {
            legalSquares[10] = piece.position + 2;
        }
        //castle long black
        if (piece.color == "black" && !king_black.hasMoved && !rook_black.hasMoved && !rook_black.hasBeenCaptured && hasNoOccupance(57) && hasNoOccupance(58) && hasNoOccupance(59)) {
            legalSquares[11] = piece.position - 2;
        }
        */

        this.moves = Logic.filterMoves(moves, this);
    }

    @Override
    public void move(int targetSquare){

        // TODO: move rook pos inside here
        /*
        if(!this.hasMoved){
            if(this.isWhite()){
                if(targetSquare == 6) {
                    // castle right white
                } else if(targetSquare == 2){
                    // castle left white
                }
            } else {
                if(targetSquare == 62){
                    // castle right black
                } else if (targetSquare == 58){
                    // castle left black
                }
            }
        }
        */

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
