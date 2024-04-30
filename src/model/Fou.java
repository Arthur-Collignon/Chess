package model;

public class Fou extends AbstractPiece {
    private char name;
    private Couleur couleur;
    private Coord coord;

    public Fou(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean moveok = false;
        if (xFinal - coord.x == yFinal - coord.y && xFinal - coord.x > 0) {
            moveok = true;
        }
        return moveok;
    }
}
