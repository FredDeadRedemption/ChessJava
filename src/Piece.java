public class Piece {
    int position; // 0-63
    String type; // P="white pawn" p="black pawn" N="white knight" k="black king"...
    boolean hasMoved;
    boolean hasBeenSlaughtered;
    public Piece(int position, String type){
        this.position = position;
        this.type = type;
        this.hasMoved = false;
        this.hasBeenSlaughtered = false;
    }

}
