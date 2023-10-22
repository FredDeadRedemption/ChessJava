import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece{
    public Pawn(int position, String type) {
        super(position, type);
    }

    @Override
    public void generateMoves(){

        List<Integer> moves = new ArrayList<>();
        int pawnAttackLeft;
        int pawnAttackRight;
        int pawnMoveForward;
        int fileLeft;
        int fileRight;

        // determine offsets for black / white
        if (Objects.equals(this.type, "P")){
            pawnAttackLeft = 7;
            pawnAttackRight = 9;
            pawnMoveForward = 8;
            fileLeft = 0;
            fileRight = 7;
        } else {
            pawnAttackLeft = -7;
            pawnAttackRight = -9;
            pawnMoveForward = -8;
            fileLeft = 7;
            fileRight = 0;
        }

        //moving forward twice
        if (!this.hasMoved && !Logic.hasOccupant(this.position + pawnMoveForward * 2) && !Logic.hasOccupant(this.position + pawnMoveForward)) {
            moves.add(this.position + pawnMoveForward * 2);
        }
        //moving forward once
        if (!Logic.hasOccupant(this.position + pawnMoveForward)) {
            moves.add(this.position + pawnMoveForward);
        }
        //attacking left
        if (Logic.hasEvilOccupant(this.position + pawnAttackLeft, this) && this.position % 8 != fileLeft) {
            moves.add(this.position + pawnAttackLeft);
        }
        //attacking right
        if (Logic.hasEvilOccupant(this.position + pawnAttackRight, this) && this.position % 8 != fileRight) {
            moves.add(this.position + pawnAttackRight);
        }

        this.moves = Logic.filterMoves(moves, this);
    }
}
