import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    public Rook(int position, String type) {
        super(position, type);
    }

    @Override
    public List<Integer> generateLegalSquares() {

        List<Integer> legalSquares = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            legalSquares.add(this.position - 8 * (i + 1));

            if (Logic.pieceIsInTheWay(i)) break;
        }

        for (int i = 7; i < 14; i++) {
            legalSquares.add(this.position - -8 * ((i % 7) + 1));

            if (Logic.pieceIsInTheWay(i)) break;
        }
        for (int i = 14; i < 21; i++) {
            if (this.position % 8 == i % 7) break;
            legalSquares.add(this.position - ((i % 7) + 1));

            if (Logic.pieceIsInTheWay(i)) break;
        }

        for (int i = 21; i < 29; i++) {
            if (7 - (this.position % 8) == i % 7) break;
            legalSquares.add(this.position - -1 * ((i % 7) + 1));

            if (Logic.pieceIsInTheWay(i)) break;
        }


        return Logic.filterLegalSquares(legalSquares);
    }
}
