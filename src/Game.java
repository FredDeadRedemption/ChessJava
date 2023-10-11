import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        Chessboard chessboard = new Chessboard();

        chessboard.animate();

        System.out.println(Piece.arrayOfPieces[5].type);

    }
}