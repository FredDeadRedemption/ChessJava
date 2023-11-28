// Frederik Højland
// fn18gu@student.aau.dk
package main;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(int position, String type) {
        super(position, type);
    }

    public void generateMoves() {

        List<Integer> moves = new ArrayList<>();

        // right
        if (Util.RIGHT_EDGE[this.position] != 1) {
            moves.add(this.position + 9);
            moves.add(this.position + 1);
            moves.add(this.position - 7);
        }
        // left
        if (Util.LEFT_EDGE[this.position] != 1) {
            moves.add(this.position + 7);
            moves.add(this.position - 1);
            moves.add(this.position - 9);
        }
        // up
        if (Util.TOP_EDGE[this.position] != 1) moves.add(this.position + 8);
        // down
        if (Util.BOTTOM_EDGE[this.position] != 1) moves.add(this.position - 8);

        // TODO: + check if squares between king and rook are contested
        // castling
        if(!this.hasMoved){
            // castle short white
            if (this.isWhite() && !Game.state.pieces[23].hasMoved && !Game.state.pieces[23].hasBeenSlaughtered && !Util.hasOccupant(5) && !Util.hasOccupant(6)){
                moves.add(6);
            }
            // castle long white
            if (this.isWhite() && !Game.state.pieces[16].hasMoved && !Game.state.pieces[16].hasBeenSlaughtered && !Util.hasOccupant(1) && !Util.hasOccupant(2) && !Util.hasOccupant(3)){
                moves.add(2);
            }
            // castle short black
            if (!isWhite() && !Game.state.pieces[7].hasMoved && !Game.state.pieces[7].hasBeenSlaughtered && !Util.hasOccupant(61) && !Util.hasOccupant(62)){
                moves.add(62);
            }
            // castle long black
            if (!isWhite() && !Game.state.pieces[0].hasMoved && !Game.state.pieces[0].hasBeenSlaughtered && !Util.hasOccupant(57) && !Util.hasOccupant(58)){
                moves.add(58);
            }
        }

        this.moves = Util.filterMoves(moves, this);
    }

    @Override
    public void move(int targetSquare){

        // this moves the rooks after castling
        if(!this.hasMoved){
            switch (targetSquare) {
                case 6 -> Game.state.pieces[23].move(5); // move light-square white rook
                case 2 -> Game.state.pieces[16].move(3); // move dark-square white rook
                case 62 -> Game.state.pieces[7].move(61); // move dark-square black rook
                case 58 -> Game.state.pieces[0].move(59); // move light-square black rook
            }
        }

        // if enemy, kill it
        if (Util.hasEvilOccupant(targetSquare, this)){
            Piece p = Util.getPieceFromSquare(targetSquare);
            assert p != null;
            p.kill();
        }
        // make move
        this.position = targetSquare;
        this.hasMoved = true;
    }
}
