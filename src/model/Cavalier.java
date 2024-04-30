package model;

public class Cavalier extends AbstractPiece {
    private char name;
    private Couleur couleur;
    private Coord coord;

    public Cavalier(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean moveok = false;
        if (coord.x == xFinal + 2 ) {
            if (coord.y == yFinal + 1 || coord.y == yFinal - 1) {
                moveok = true;
            }
        }
        if (coord.x == xFinal - 2 ) {
            if (coord.y == yFinal + 1 || coord.y == yFinal - 1) {
                moveok = true;
            }
        }
        if (coord.y == yFinal + 2 ) {
            if (coord.x == yFinal + 1 || coord.x == yFinal - 1) {
                moveok = true;
            }
        }
        if (coord.y == yFinal - 2 ) {
            if (coord.x == yFinal + 1 || coord.x == yFinal - 1) {
                moveok = true;
            }
        }
        return moveok;
    }
}
