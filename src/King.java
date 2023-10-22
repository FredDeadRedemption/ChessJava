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

        this.moves = Logic.filterMoves(moves, this);
    }
}
