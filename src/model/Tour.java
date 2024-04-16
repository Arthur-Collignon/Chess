package model;

public class Tour extends AbstractPiece  {
    private char name;
    private Couleur couleur;
    private Coord coord;

    public Tour(char name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    public boolean isMoveOk(int xFinal, int yFinal) {

        return false;
    }
}
