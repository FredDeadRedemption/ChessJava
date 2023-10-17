import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(int position, String type) {
        super(position, type);
    }

    @Override
    public List<Integer> generateMoves() {
        List<Integer> legalSquares = new ArrayList<>();

        int factor = 1;
        int index = this.position % 8;

        for (int i = 0; i < 2; i++) {
            legalSquares.add(this.position - 6 * factor);
            legalSquares.add(this.position - 10 * factor);
            legalSquares.add(this.position - 15 * factor);
            legalSquares.add(this.position - 17 * factor);

            factor = factor * -1;
        }

        for (int i = legalSquares.size() - 1; i >= 0; i--) {
            if ((index < 2 && legalSquares.get(i) % 8 > index + 2) || (index > 5 && legalSquares.get(i) % 8 < index - 2)) {
                legalSquares.remove(i);
            }
        }

        return Logic.filterLegalSquares(legalSquares, this);
    }
}
