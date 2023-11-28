package tests;
import main.Piece ;

// this test takes a game state as input and
// returns how long it took to generate all the possible moves in that position

public class TimeMeasurementTest {

        public static void run(Piece[] pieces){

            long startTime = System.currentTimeMillis(); // or System.nanoTime()
            // start
            for (Piece piece : pieces) {
                piece.generateMoves();
            }
            // end
            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;

            System.out.println("Execution Time: " + executionTime + " milliseconds");
        }
}
