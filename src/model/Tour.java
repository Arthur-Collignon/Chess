package model;

public class Tour extends AbstractPiece  {
    private char name;
    private Couleur couleur;
    private Coord coord;

    public Tour(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean moveok = false;
        if (coord.x == xFinal || coord.y == yFinal) {
            moveok = true;
        }
        return moveok;
    }
}
