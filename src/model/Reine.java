package model;

public class Reine extends AbstractPiece {
    private char name;
    private Couleur couleur;
    private Coord coord;

    public Reine(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean moveok = false;
        if (coord.x == xFinal || coord.y == yFinal) {
            moveok = true;
        }
        if (xFinal - coord.x == yFinal - coord.y && xFinal - coord.x > 0) {
            moveok = true;
        }
        return moveok;
    }
}
