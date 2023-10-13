public class Logic {

    static Piece getPieceFromSquare(int square){
        for (Piece p : Game.arrayOfPieces) {
            if (p.position == square) {return p;}
        }
        return null;
    }

    public int[] generateLegalMovesFor(Piece p){
        int[] legalsquares = new int[64];
        switch (p.type) {
            case "k", "K" -> legalsquares = this.generateKingMoves(p);
            case "q", "Q" -> legalsquares = this.generateQueenMoves(p);
            case "b", "B" -> legalsquares = this.generateBishopMoves(p);
            case "n", "N" -> legalsquares = this.generateKnightMoves(p);
            case "r", "R" -> legalsquares = this.generateRookMoves(p);
            case "p", "P" -> legalsquares = this.generatePawnMoves(p);
        }
        return legalsquares;
    }

    private int[] generateKingMoves(Piece p){
        System.out.println(p);
        return new int[]{0, 0};
    }

    private int[] generateQueenMoves(Piece p){
        System.out.println(p);
        return new int[]{0, 0};
    }
    private int[] generateBishopMoves(Piece p){
        System.out.println(p);
        return new int[]{0, 0};
    }
    private int[] generateKnightMoves(Piece p){
        System.out.println(p);
        return new int[]{0, 0};
    }
    private int[] generateRookMoves(Piece p){
        System.out.println(p);
        return new int[]{0, 0};
    }
    private int[] generatePawnMoves(Piece p){
        System.out.println(p);
        return new int[]{0, 0};
    }
}
