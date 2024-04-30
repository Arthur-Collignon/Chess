package model;

public abstract class AbstractPiece implements Pieces {
    private char name;
    private Couleur couleur;
    private Coord coord;

    AbstractPiece(Couleur couleur, Coord coord)
    {
        this.couleur = couleur;
        this.coord = coord;
    }

    public int getX() {
        return coord.x;
    }

    public int getY() {
        return coord.y;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public boolean move(int xFinal, int yFinal) {
        boolean made = false;
        if (isMoveOk(xFinal, yFinal)) {
            coord.x = xFinal;
            coord.y = yFinal;
            made = true;
        }
        return made;
    }

    public boolean capture() {
        return false;
    }

    public java.lang.String toString() {
        return "Piece " + name + " : " + coord;
    }

    public abstract boolean isMoveOk(int xFinal, int yFinal);


}




