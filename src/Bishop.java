import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(int position, String type) {
        super(position, type);

        this.offsets.add(7);
        this.offsets.add(-7);
        this.offsets.add(9);
        this.offsets.add(-9);
    }

    @Override
    public List<Integer> generateMoves() {

        List<Integer> legalSquares = new ArrayList<>();

        // generate sliding move for all offsets
        for (int offset : this.offsets){
            legalSquares.addAll(this.generateSlidingMove(offset));
        }

        return Logic.filterLegalSquares(legalSquares, this);
    }
}
