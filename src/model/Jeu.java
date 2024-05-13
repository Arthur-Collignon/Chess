package model;

import tools.ChessPiecesFactory;

public class Jeu extends java.lang.Object {
    public Jeu (Couleur couleur) {
        ChessPiecesFactory.newPieces(Couleur.BLANC);
        ChessPiecesFactory.newPieces(Couleur.NOIR);
    }

    public boolean isPieceHere(int x, int y) {
        //Return true if there is a piece at the given coordinates
        //TODO

        return false;
    }

    public boolean IsMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        //Return true if the move is possible
        //TODO
        return false;
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        //Move the piece from the initial coordinates to the final coordinates
        //Return true when move done
        //TODO
        return false;
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
    public java.lang.String toString() {
        return super.toString();
    }

    public static void main(java.lang.String[] args) {
        System.out.println(new Jeu(Couleur.BLANC));
    }


    public Couleur getPieceColor(int x, int y) {
        //Return the color of the piece at the given coordinates
        //TODO
        return null;
    }

    public java.lang.String getPieceType(int x, int y) {
        //Return the type of the piece at the given coordinates
        //TODO
        return null;
    }

    public Couleur getCouleur() {
        //Return the color of the player
        //TODO
        return null;
    }

    public java.util.List<PieceIHM> getPiecesIHM() {
        //Return une vue de la liste des pièces en cours ne donnant que
        // des accès en lecture sur des model.PieceIHM (type piece + couleur + liste de coordonnées)
        //TODO
        return null;
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

    public boolean isPawnPromotion(int xFinal, int yfinal) {
        //Return true if the pawn can be promoted
        //TODO
        return false;
    }

    public boolean pawnPromotion(int x, int y, java.lang.String type) {
        //Promote the pawn to the given type
        //Return true when promotion done
        //TODO
        return false;
    }

    public Coord getKingCoord() {
        //Return the coordinates of the king
        //TODO
        return null;
    }
}
