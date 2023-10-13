public class Piece {
    int position; // 0-63 bitboard
    String type; // P="white pawn" p="black pawn" N="white knight" k="black king"...
    boolean hasMoved; // for castle & double pawn moves
    boolean hasBeenSlaughtered; // for castle
    public Piece(int position, String type){
        this.position = position;
        this.type = type;
        this.hasMoved = false;
        this.hasBeenSlaughtered = false;
    }

    public Boolean isWhite(){
        return this.type.matches("[kqbnrp]");
    }

    public void promote() {
        if(this.type.matches("P")) {this.type = "Q";}
        if(this.type.matches("p")) {this.type = "q";}
    }

    public void kill(){
        this.position = 70;
        this.hasBeenSlaughtered = true;
    }

}
