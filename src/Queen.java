import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    public Queen(int position, String type) {
        super(position, type);

        this.offsets.add(7);
        this.offsets.add(-7);
        this.offsets.add(9);
        this.offsets.add(-9);
        this.offsets.add(8);
        this.offsets.add(-8);
        this.offsets.add(1);
        this.offsets.add(-1);
    }

    @Override
    public void generateMoves() {

        List<Integer> moves = new ArrayList<>();

        // generate sliding move for all offsets
        for (int offset : this.offsets){
            moves.addAll(this.generateSlidingMoves(offset));
        }

        this.moves = Logic.filterMoves(moves, this);
    }
}
