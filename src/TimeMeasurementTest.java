public class TimeMeasurementTest {

        public static void run(){

            long startTime = System.currentTimeMillis();
            ////////////////////////////////////////////
            for(int i = 0; i < Game.state.length; i++){
                Game.state[i].generateMoves();
            }
            ////////////////////////////////////////////
            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;

            System.out.println("Execution Time: " + executionTime + " milliseconds");
        }
}
