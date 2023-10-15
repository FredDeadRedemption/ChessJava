import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(int position, String type) {
        super(position, type);

        this.offsets.add(7);
        this.offsets.add(-7);
        this.offsets.add(9);
        this.offsets.add(-9);
    }

    @Override
    public List<Integer> generateLegalSquares() {

        List<Integer> legalSquares = new ArrayList<>();

        // generate sliding move for all offsets
        for (int offset : this.offsets){
            legalSquares.addAll(generateSlidingMove(offset));
        }

        return Logic.filterLegalSquares(legalSquares);
    }

    private List<Integer> generateSlidingMove(int offset){

        List<Integer> legalSquares = new ArrayList<>();

        // if piece is already on edge
        if (Logic.squareOnEdge(offset, this.position)) return legalSquares;

        for(int i = 1; i < 8; i++){

            legalSquares.add(this.position + offset * i);

            if (Logic.squareOnEdge(offset, this.position + offset * i)) break; // if move hits edge
            if (Logic.hasOccupant(this.position + offset * i)) break; // if move hits occupied square

        }
        return legalSquares;
    }
}
