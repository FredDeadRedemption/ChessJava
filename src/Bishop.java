import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(int position, String type) {
        super(position, type);
    }

    @Override
    public List<Integer> generateLegalSquares() {

        List<Integer> legalSquares = new ArrayList<>();

        if (this.position > 8) { // if not on lowe edge
            if (this.position % 8 != 7) { // right edge
                for (int i = 1; i < 8; i++) {

                    legalSquares.add(this.position - 7 * i);

                    if ((this.position - (7 * i)) % 8 == 7) break;
                    if ((this.position - (7 * i)) < 8) break;
                    if ((this.position - (7 * i)) > 55) break;
                    if (Logic.hasOccupant(this.position - 7 * i)) break;
                }
            }

            if (this.position % 8 != 0) { // left edge
                for (int i = 1; i < 8; i++) {

                    legalSquares.add(this.position - 9 * i);

                    if ((this.position - 9 * i) % 8 == 0) break;
                    if ((this.position - 9 * i) < 8) break;
                    if ((this.position - 9 * i) > 55) break;
                    if (Logic.hasOccupant(this.position - 9 * i)) break;
                }
            }
        }

        if (this.position < 56) { // if not on upper edge
            if (this.position % 8 != 0) { // left edge
                for (int i = 1; i < 8; i++) {

                    legalSquares.add(this.position + 7 * i);

                    if ((this.position + 7 * i) % 8 == 0) break;
                    if ((this.position + 7 * i) < 8) break;
                    if ((this.position + 7 * i) > 55) break;
                    if (Logic.hasOccupant(this.position + 7 * i)) break;
                }
            }

            if (this.position % 8 != 7) { // right edge
                for (int i = 1; i < 8; i++) {

                    legalSquares.add(this.position + 9 * i);

                    if ((this.position + 9 * i) % 8 == 7) break;
                    if ((this.position + 9 * i) < 8) break;
                    if ((this.position + 9 * i) > 55) break;
                    if (Logic.hasOccupant(this.position + 9 * i)) break;
                }
            }
        }
        return Logic.filterLegalSquares(legalSquares);
    }
}
