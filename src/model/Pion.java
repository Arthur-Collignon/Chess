package model;

public class Pion extends AbstractPiece {
    private char name;
    private Couleur couleur;
    private Coord coord;

    public Pion(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean moveok = false;
        if (coord.x == xFinal + 1) {
            moveok = true;
        }
        return moveok;
    }
}
