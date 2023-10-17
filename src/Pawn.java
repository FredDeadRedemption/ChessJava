import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece{
    public Pawn(int position, String type) {
        super(position, type);
    }

    @Override
    public List<Integer> generateMoves(){

        List<Integer> legalSquares = new ArrayList<>();
        int pawnAttackLeft;
        int pawnAttackRight;
        int pawnMoveForward;
        int fileLeft;
        int fileRight;

        //offsets for black / white
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
            legalSquares.add(this.position + pawnMoveForward * 2);
        }
        //moving forward once
        if (!Logic.hasOccupant(this.position + pawnMoveForward)) {
            legalSquares.add(this.position + pawnMoveForward);
        }
        //attacking left
        if (Logic.hasEvilOccupant(this.position + pawnAttackLeft, this) && this.position % 8 != fileLeft) {
            legalSquares.add(this.position + pawnAttackLeft);
        }
        //attacking right
        if (Logic.hasEvilOccupant(this.position + pawnAttackRight, this) && this.position % 8 != fileRight) {
            legalSquares.add(this.position + pawnAttackRight);
        }

        return Logic.filterLegalSquares(legalSquares, this);
    }
}
