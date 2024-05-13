package model;

public class Echiquier {
    private Jeu jeuBlanc;
    private Jeu jeuNoir;
    public Jeu jeuCourant;
    public Jeu jeuNonCourant;
    private String message;

    public Echiquier() {
        this.jeuBlanc = new Jeu(Couleur.BLANC);
        this.jeuNoir = new Jeu(Couleur.NOIR);
        this.jeuCourant = this.jeuBlanc;
        this.jeuNonCourant = this.jeuNoir;
    }

    @Override
    public String toString() {
        String str_echi = "";
        str_echi += "Jeu blanc :\n"+this.jeuBlanc.toString()+"\n";
        str_echi += "Jeu noir :\n"+this.jeuNoir.toString()+"\n";
        return str_echi;
    }

    public Object getPiecesIHM() {
        //TODO
        return null;
    }

    public String getMessage() {
        return this.message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        boolean canMove = false;
        if ((!(this.jeuCourant.isPieceHere(xInit,yInit)))||(xFinal>7)||(xFinal<0)||(yFinal>7)||(yFinal<0)||((xFinal==xInit) && (yFinal==yInit))&&(this.jeuCourant.isMoveOk(xInit,yInit,xFinal,yFinal))) {
            canMove = false; //TODO Faire pièces intermédiaire et capture et roque
        } else {
            canMove = true;
        }
        return canMove;
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        boolean moved = false;
        if (this.isMoveOk(xInit, yInit, xFinal, yFinal)) {
            moved = this.jeuCourant.move(xInit, yInit, xFinal, yFinal);
        }
        return moved;
    }

    public void switchJoueur() {
        Jeu temp = this.jeuCourant;
        this.jeuCourant = this.jeuNonCourant;
        this.jeuNonCourant = temp;
    }

    public boolean isEnd() {
        //TODO
        return false;
    }

    public Couleur getColorCurrentPlayer() {
        return this.jeuCourant.getCouleur();
    }

    public Couleur getPieceColor(int x, int y) {
        if (this.jeuBlanc.getPieceColor(x, y) != Couleur.NOIRBLANC) {
            return Couleur.BLANC;
        }
        if (this.jeuNoir.getPieceColor(x, y) != Couleur.NOIRBLANC) {
            return Couleur.NOIR;
        }
        return Couleur.NOIRBLANC;
    }

    public static void main(String[] args) {
        Echiquier echi = new Echiquier();
		/*echi.switchJoueur();
		System.out.println(echi);
		echi.move(0, 0, 0, 6);
		System.out.println(echi);
		echi.move(0,6,6,6);
		System.out.println(echi);*/

    }
}
