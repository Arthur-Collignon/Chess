package model;

import tools.ChessPiecesFactory;

import java.util.LinkedList;
import java.util.List;

public class Jeu extends java.lang.Object {
    private List<Pieces> pieces;
    private Couleur couleur;

    public Jeu (Couleur couleur) {
        ChessPiecesFactory.newPieces(Couleur.BLANC);
        ChessPiecesFactory.newPieces(Couleur.NOIR);
    }

    private Pieces findPieces(int x, int y) {
        Pieces find = null;
        for (Pieces piece : this.pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                find = piece;
                break;
            }
        }
        return find;
    }

    public boolean isPieceHere(int x, int y) {
        //Return true if there is a piece at the given coordinates
        boolean present = false;
        if (this.findPieces(x,y) != null) {
            present = true;
        }
        return present;
    }

    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        //Return true if the move is possible
        boolean move = false;
        Pieces piece = this.findPieces(xInit, yInit);
        if (piece != null) {
            move = piece.isMoveOk(xFinal, yFinal);
        }
        return move;
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        //Move the piece from the initial coordinates to the final coordinates
        //Return true when move done
        boolean moved = false;
        if (this.isMoveOk(xInit, yInit, xFinal, yFinal)) {
            Pieces piece = this.findPieces(xInit, yInit);
            moved = piece.move(xFinal, yFinal);
        }
        return moved;
    }

    public void setPossibleCapture() {
        //If a piece can be captured, set a boolean to true
        //TODO later
    }

    public boolean capture(int xCatch, int yCatch) {
        //Capture the piece at the given coordinates
        //Return true when capture done
        //TODO later
        return false;
    }

    @Override
    public String toString() {
        String lst = "";
        for (Pieces piece : this.pieces) {
            lst = lst + piece.toString() + "\n";
        }
        return lst;
    }


    public Couleur getPieceColor(int x, int y) {
        //Return the color of the piece at the given coordinates
        Couleur pieceColor = Couleur.NOIRBLANC;
        if (this.findPieces(x,y) != null) {
            pieceColor = this.getCouleur();
        }
        return pieceColor;
    }

    public java.lang.String getPieceType(int x, int y) {
        //Return the type of the piece at the given coordinates
        String pieceType = "";
        Pieces piece = this.findPieces(x, y);
        if (piece != null) {
            pieceType = piece.getClass().getSimpleName();
        }
        return pieceType;
    }

    public Couleur getCouleur() {
        //Return the color of the player
        return this.couleur;
    }

    public java.util.List<PieceIHM> getPiecesIHM() {
        //Return une vue de la liste des pièces en cours ne donnant que
        // des accès en lecture sur des model.PieceIHM (type piece + couleur + liste de coordonnées)
        PieceIHM newPieceIHM = null;
        List<PieceIHM> list = new LinkedList<PieceIHM>();

        for (Pieces piece : pieces){
            boolean existe = false;
            // si le type de piece existe déjà dans la liste de PieceIHM
            // ajout des coordonnées de la pièce dans la liste de Coord de ce type
            // si elle est toujours en jeu (x et y != -1)
            for ( PieceIHM pieceIHM : list){
                if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){
                    existe = true;
                    if (piece.getX() != -1){
                        pieceIHM.add(new Coord(piece.getX(), piece.getY()));
                    }
                }
            }
            // sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
            if (! existe) {
                if (piece.getX() != -1){
                    newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(),piece.getCouleur());
                    newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
                    list.add(newPieceIHM);
                }
            }
        }
        return list;
    }

    public void setCastling() {
        //If the king and the rook are at their initial position, set a boolean to true
        //TODO
    }

    public void undoMove() {
        //Undo the last move
        //TODO
    }

    public void undoCapture() {
        //Undo the last capture
        //TODO
    }

    public boolean isPawnPromotion(int xfinal, int yfinal) {
        //Return true if the pawn can be promoted
        boolean promo = false;
        if ((this.getCouleur() == Couleur.BLANC && yfinal == 0) || (this.getCouleur() == Couleur.NOIR && yfinal == 7)){
            promo = true;
        }
        return promo;
    }

    public boolean pawnPromotion(int x, int y, java.lang.String type) {
        //Promote the pawn to the given type
        //Return true when promotion done
        //TODO
        return false;
    }

    public Coord getKingCoord() {
        //Return the coordinates of the king
        Coord coordKing = null;
        for (Pieces piece : this.pieces) {
            if (piece instanceof Roi) {
                coordKing = new Coord(piece.getX(),piece.getY());
                break;
            }
        }
        return coordKing;
    }


    public static void main(String[] args) {
        Jeu game1 = new Jeu(Couleur.NOIR);
        System.out.println(game1);
    }

}
