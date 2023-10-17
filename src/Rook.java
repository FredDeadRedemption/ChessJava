import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    public Rook(int position, String type) {
        super(position, type);

        this.offsets.add(8);
        this.offsets.add(-8);
        this.offsets.add(1);
        this.offsets.add(-1);
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
