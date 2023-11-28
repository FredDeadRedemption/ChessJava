package tests;
import main.Piece ;

public class TimeMeasurementTest {

        public void run(Piece[] pieces){

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
