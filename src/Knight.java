import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(int position, String type) {
        super(position, type);
    }

    @Override
    public void generateMoves() {

        List<Integer> moves = new ArrayList<>();

        int factor = 1;
        int index = this.position % 8;

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

        this.moves = Logic.filterMoves(moves, this);
    }
}
