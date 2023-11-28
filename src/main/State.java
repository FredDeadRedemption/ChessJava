// Frederik Højland
// fn18gu@student.aau.dk
package main;

import java.util.ArrayList;

public class State {

    public boolean whiteToMove = true; // flips on successful move

    public Piece[] pieces = new Piece[32]; // contains all pieces in game

    public State() {
        reset();
    }

    public void reset(){
        this.whiteToMove = true;
        // 0-15 Black
        this.pieces[0] = new Rook(56, "r");
        this.pieces[1] = new Knight(57, "n");
        this.pieces[2] = new Bishop(58, "b");
        this.pieces[3] = new Queen(59, "q");
        this.pieces[4] = new King(60, "k");
        this.pieces[5] = new Bishop(61, "b");
        this.pieces[6] = new Knight(62, "n");
        this.pieces[7] = new Rook(63, "r");
        this.pieces[8] = new Pawn(48, "p");
        this.pieces[9] = new Pawn(49, "p");
        this.pieces[10] = new Pawn(50, "p");
        this.pieces[11] = new Pawn(51, "p");
        this.pieces[12] = new Pawn(52, "p");
        this.pieces[13] = new Pawn(53, "p");
        this.pieces[14] = new Pawn(54, "p");
        this.pieces[15] = new Pawn(55, "p");
        // 16-32 WHITE
        this.pieces[16] = new Rook(0, "R");
        this.pieces[17] = new Knight(1, "N");
        this.pieces[18] = new Bishop(2, "B");
        this.pieces[19] = new Queen(3, "Q");
        this.pieces[20] = new King(4, "K");
        this.pieces[21] = new Bishop(5, "B");
        this.pieces[22] = new Knight(6, "N");
        this.pieces[23] = new Rook(7, "R");
        this.pieces[24] = new Pawn(8, "P");
        this.pieces[25] = new Pawn(9, "P");
        this.pieces[26] = new Pawn(10, "P");
        this.pieces[27] = new Pawn(11, "P");
        this.pieces[28] = new Pawn(12, "P");
        this.pieces[29] = new Pawn(13, "P");
        this.pieces[30] = new Pawn(14, "P");
        this.pieces[31] = new Pawn(15, "P");
    }

    // the idea is to be able to take a snapshot of the game state.
    // TODO: this is stupid should represent game state in a couple integers and make all pieces a static move-gen class
    public Piece[] deepClone() {

        Piece[] clonedState = new Piece[this.pieces.length];

        for (int i = 0; i < this.pieces.length; i++) {
            if (this.pieces[i] instanceof Rook) {
                clonedState[i] = new Rook(this.pieces[i].position, this.pieces[i].type);
            } else if (this.pieces[i] instanceof Knight) {
                clonedState[i] = new Knight(this.pieces[i].position, this.pieces[i].type);
            } else if (this.pieces[i] instanceof Bishop) {
                clonedState[i] = new Bishop(this.pieces[i].position, this.pieces[i].type);
            } else if (this.pieces[i] instanceof Queen) {
                clonedState[i] = new Queen(this.pieces[i].position, this.pieces[i].type);
            } else if (this.pieces[i] instanceof King) {
                clonedState[i] = new King(this.pieces[i].position, this.pieces[i].type);
            } else if (this.pieces[i] instanceof Pawn) {
                clonedState[i] = new Pawn(this.pieces[i].position, this.pieces[i].type);
            }
            clonedState[i].hasMoved = this.pieces[i].hasMoved;
            clonedState[i].hasBeenSlaughtered = this.pieces[i].hasBeenSlaughtered;
            clonedState[i].offsets = new ArrayList<>(this.pieces[i].offsets);
            clonedState[i].moves = new ArrayList<>(this.pieces[i].moves);
        }

        return clonedState;
    }
}
