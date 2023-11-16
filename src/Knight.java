import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(int position, String type) {
        super(position, type);
    }

    public void generateMoves(boolean filter) {

        List<Integer> moves = new ArrayList<>();

        int factor = 1;
        int index = this.position % 8; // 0 = A file, 1 = B file...

        for (int i = 0; i < 2; i++) {
            moves.add(this.position - 6 * factor);
            moves.add(this.position - 10 * factor);
            moves.add(this.position - 15 * factor);
            moves.add(this.position - 17 * factor);

            factor = factor * -1;
        }
        
        for (int i = moves.size() - 1; i >= 0; i--) {
            if ((index < 2 && moves.get(i) % 8 > index + 2) || (index > 5 && moves.get(i) % 8 < index - 2)) {
                moves.remove(i);
            }
        }

        this.moves = Util.filterMoves(moves, this, filter);
    }
}
