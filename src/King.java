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
        if (this.position % 8 != 7) {
            moves.add(this.position + 9);
            moves.add(this.position + 1);
            moves.add(this.position - 7);
        }
        // left
        if (this.position % 8 != 0) {
            moves.add(this.position + 7);
            moves.add(this.position - 1);
            moves.add(this.position - 9);
        }
        // up
        if (this.position < 56) moves.add(this.position + 8);
        // down
        if (this.position > 8) moves.add(this.position - 8);

        this.moves = Logic.filterMoves(moves, this);
    }
}
