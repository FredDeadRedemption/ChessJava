public class TimeMeasurementTest {

        public static void run(){

            long startTime = System.currentTimeMillis(); // or System.nanoTime()
            // start
            for(int i = 0; i < Game.state.pieces.length; i++){
                Game.state.pieces[i].generateMoves();
            }
            // end
            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;

            System.out.println("Execution Time: " + executionTime + " milliseconds");
        }
}
