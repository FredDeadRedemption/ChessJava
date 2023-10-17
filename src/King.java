import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(int position, String type) {
        super(position, type);
    }

    @Override
    public List<Integer> generateLegalSquares() {

        List<Integer> legalSquares = new ArrayList<>();

        // right
        if (this.position % 8 != 7) {
            legalSquares.add(this.position + 9);
            legalSquares.add(this.position + 1);
            legalSquares.add(this.position - 7);
        }
        // left
        if (this.position % 8 != 0) {
            legalSquares.add(this.position + 7);
            legalSquares.add(this.position - 1);
            legalSquares.add(this.position - 9);
        }
        // up
        if (this.position < 56) legalSquares.add(this.position + 8);
        // down
        if (this.position > 8) legalSquares.add(this.position - 8);

        return Logic.filterLegalSquares(legalSquares);
    }
}
