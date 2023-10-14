import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(int position, String type) {
        super(position, type);
    }

    @Override
    public List<Integer> generateLegalSquares() {

        List<Integer> legalSquares = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            if (this.position % 8 == i) break;
            legalSquares.add(this.position - -7 * (i + 1));

            if (Logic.hasOccupant(i)) break;
        }

        for (int i = 7; i < 14; i++) {
            if (7 - (this.position % 8) == i % 7) break;
            legalSquares.add(this.position - -9 * ((i % 7) + 1));

            if (Logic.hasOccupant(i)) break;
        }

        for (int i = 14; i < 21; i++) {
            if (7 - (this.position % 8) == i % 7) break;
            legalSquares.add(this.position - 7 * ((i % 7) + 1));

            if (Logic.hasOccupant(i)) break;
        }

        for (int i = 21; i < 28; i++) {
            if (this.position % 8 == i % 7) break;
            legalSquares.add(this.position - 9 * ((i % 7) + 1));

            if (Logic.hasOccupant(i)) break;
        }

        return Logic.filterLegalSquares(legalSquares);
    }
}