package main;

import main.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    public Pawn(int position, String type) {
        super(position, type);
    }

    public void generateMoves(){

        List<Integer> moves = new ArrayList<>();
        int pawnAttackLeft;
        int pawnAttackRight;
        int pawnMoveForward;
        int fileLeft;
        int fileRight;

        // determine offsets for black / white
        if (Objects.equals(this.type, "P")){
            pawnAttackLeft = 7;
            pawnAttackRight = 9;
            pawnMoveForward = 8;
            fileLeft = 0;
            fileRight = 7;
        } else {
            pawnAttackLeft = -7;
            pawnAttackRight = -9;
            pawnMoveForward = -8;
            fileLeft = 7;
            fileRight = 0;
        }

        // moving forward twice
        if (!this.hasMoved && !Util.hasOccupant(this.position + pawnMoveForward * 2) && !Util.hasOccupant(this.position + pawnMoveForward)) {
            moves.add(this.position + pawnMoveForward * 2);
        }
        // moving forward once
        if (!Util.hasOccupant(this.position + pawnMoveForward)) {
            moves.add(this.position + pawnMoveForward);
        }
        // attacking left
        if (Util.hasEvilOccupant(this.position + pawnAttackLeft, this) && this.position % 8 != fileLeft) {
            moves.add(this.position + pawnAttackLeft);
        }
        // attacking right
        if (Util.hasEvilOccupant(this.position + pawnAttackRight, this) && this.position % 8 != fileRight) {
            moves.add(this.position + pawnAttackRight);
        }

        this.moves = Util.filterMoves(moves, this);
    }

    @Override
    public void move(int targetSquare){

        // if enemy, kill it
        if (Util.hasEvilOccupant(targetSquare, this)){
            Piece p = Util.getPieceFromSquare(targetSquare);
            assert p != null;
            p.kill();
        }
        // make move
        this.position = targetSquare;
        this.hasMoved = true;
        // promote
        if(Util.TOP_EDGE[this.position] == 1 && this.isWhite()){
            // if piece is white and on the top edge of board replace with new queen
            Game.state.pieces[getIndexInState()] = new Queen(this.position, "Q");
        } else if(Util.BOTTOM_EDGE[this.position] == 1 && !this.isWhite()){
            // same for black
            Game.state.pieces[getIndexInState()] = new Queen(this.position, "q");
        }
    }

    private int getIndexInState() {
        for (int i = 0; i < Game.state.pieces.length; i++) {
            if (this.equals(Game.state.pieces[i])) {
                return i;
            }
        }
        return -1;
    }
}
