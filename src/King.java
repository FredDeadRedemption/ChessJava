import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(int position, String type) {
        super(position, type);
    }

    @Override
    public List<Integer> generateLegalSquares() {
        
        List<Integer> legalSquares = new ArrayList<>();


        return Logic.filterLegalSquares(legalSquares);
    }
}
