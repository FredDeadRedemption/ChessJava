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

        // TODO: + check if squares between king and rook are contested
        // castling
        if(!this.hasMoved){
            // castle short white
            if (this.isWhite() && !Game.state[23].hasMoved && !Game.state[23].hasBeenSlaughtered && !Logic.hasOccupant(5) && !Logic.hasOccupant(6)){
                moves.add(6);
            }
            // castle long white
            if (this.isWhite() && !Game.state[16].hasMoved && !Game.state[16].hasBeenSlaughtered && !Logic.hasOccupant(1) && !Logic.hasOccupant(2) && !Logic.hasOccupant(3)){
                moves.add(2);
            }
            // castle short black
            if (!isWhite() && !Game.state[7].hasMoved && !Game.state[7].hasBeenSlaughtered && !Logic.hasOccupant(61) && !Logic.hasOccupant(62)){
                moves.add(62);
            }
            // castle long black
            if (!isWhite() && !Game.state[0].hasMoved && !Game.state[0].hasBeenSlaughtered && !Logic.hasOccupant(57) && !Logic.hasOccupant(58)){
                moves.add(58);
            }
        }

        this.moves = Logic.filterMoves(moves, this);
    }

    @Override
    public void move(int targetSquare){

        // move rook after castling
        if(!this.hasMoved){
            switch (targetSquare) {
                case 6 -> Game.state[23].move(5); // castle short white
                case 2 -> Game.state[16].move(3); // castle long white
                case 62 -> Game.state[7].move(61); // castle short black
                case 58 -> Game.state[0].move(59); // castle long black
            }
        }

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
