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

    public void generateMoves() {

        List<Integer> moves = new ArrayList<>();

        // generate sliding move for all offsets
        for (int offset : this.offsets){
            moves.addAll(this.generateSlidingMoves(offset));
        }

        this.moves = Util.filterMoves(moves, this);
    }
}
