package tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import main.Knight;
import main.Piece;
import main.State;

// this is a proof of concept
// this test checks the precision of the number of moves on the
// board in a specific position

public class MoveGenPrecisionTest {

    @Test
    public void run() {

        boolean success = true;

        try {
            assertEquals(44, countTotalMoves());

        } catch (AssertionError e) {
            System.out.println("Assertion error: " + e.getMessage());
            success = false;

        } finally {
            if(success){
                System.out.println("Precision Test Passed!!!");
            }
        }
    }

    private int countTotalMoves() {

        // init new state
        State state = new State();

        // spawn white knight on E4
        state.pieces[31] = new Knight(28, "K");

        // count all possible moves
        int numOfMoves = 0;

        for (Piece piece : state.pieces) {
            piece.generateMoves();
            numOfMoves += piece.moves.size();
        }

        return numOfMoves;
    }
}
