package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Coord;
import model.Couleur;
import tools.ChessImageProvider;
import controler.ChessGameControlers;


/**
 * @author francoise.perrin -
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD -
 * Inspiration http://www.roseindia.net/java/example/java/swing/chess-application-swing.shtml
 * 
 *  IHM graphique d'un jeu d'echec 
 *  qui permet � 1 utilisateur de jouer
 *  en prenant successivement le r�le des blancs puis des noirs.
 *  
 */

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChessGameControlers chessGameControler;
	private Dimension boardSize;

	//Panneau stratifi� permettant de superposer plusieurs couches
	// visibles les unes sur les autres
	private JLayeredPane layeredPane;

	//plateau du jeu d'echec permettant de contenir tous les objets graphiques
	private JPanel chessBoardGuiContainer;

	//piece selectionnee
	private JLabel pieceToMove;

	// carr� sur laquelle est pos�e la piece � d�placer
	private JPanel pieceToMoveSquare;

	// map permettant d'associer un JPanel (carr� noir ou blanc)
	// � ses coordonn�es sur l'�chiquier
	private Map<JPanel, Coord> mapSquareCoord;

	// tableau 2D qui stocke les JPanel
	// permet de rafraichir l'affichage apr�s chaque d�placement
	// valid� par les classes m�tier
	private JPanel[][] tab2DJPanel;

	// coordonnee qui permettront de recadrer une pi�ce 
	// au milieu du carr� lors d'un deplacement  (drag)
	private int xAdjustment;
	private int yAdjustment;







	/**
	 * 
	 * Construit le plateau de l'�chiquier sous forme de damier 8*8
	 * et le rend �coutable par les �v�nements 
	 * MouseListener et MouseMotionListener.
	 *   
	 * Sont superpos�s 1 JPanel pour le plateau et
	 * autant de JPanel que de carr�s noirs ou blancs
	 * sur lesquels seront positionn�es les pi�ces aux bonnes coordonn�es.
	 * 
	 * Les images des pi�ces et leurs coordonn�es 
	 * seront fournies par des utilitaires.
	 *
	 * 
	 * @param name - nom de la fen�tre
	
	 * @param chessGameControler - 
	 * @param boardSize - taille de la fen�tre
	 */
	public ChessGameGUI(String name, ChessGameControlers chessGameControler, Dimension boardSize) {
		super(name);
		this.initFields(chessGameControler, boardSize);
		this.setLayout();
		this.setListener();
	}


	private void initFields(ChessGameControlers chessGameControler, Dimension boardSize) {

		this.chessGameControler = chessGameControler;  
		this.boardSize = boardSize;


		this.layeredPane = new JLayeredPane();
		this.chessBoardGuiContainer = new JPanel();
		this.mapSquareCoord = new HashMap<JPanel,Coord>();
		this.tab2DJPanel = new JPanel[8][8];
	}

	private void setListener() {
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
	}


	/**
	 * construit le plateau de l'�chiquier sous forme de damier 8*8  
	 * sont superpos�s 1 JPanel pour le plateau et
	 * autant de JPanel que de carr�s noirs ou blancs
	 */
	private void setLayout() {	

		JPanel square = null;
		JLabel pieceGuiLabel = null;

		// cr�ation du plateau de l'�chiquier sous forme de damier 8*8 
		setContentPane(this.layeredPane);		
		this.layeredPane.add(this.chessBoardGuiContainer, JLayeredPane.DEFAULT_LAYER);
		this.chessBoardGuiContainer.setLayout( new GridLayout(8, 8) );
		this.chessBoardGuiContainer.setBounds(0, 0, boardSize.width-10, boardSize.height-30);

		// remplissage du damier avec les carr�s et quand n�cessaire
		// les images des pi�ces
		for (int i = 0; i<8; i++){
			for (int j = 0; j<8; j++) {

				// cr�ation d'un JPanel pour le carr� blanc ou noir
				square = this.newSquare(i, j);

				// Cr�ation �ventuelle d'un JLabel pour stocker une image
				// de pi�ce
				pieceGuiLabel = this.newImage(i, j);	

				// ajout du carr� sur le damier
				this.chessBoardGuiContainer.add( square );

				// sauvegarde des coordonn�es logiques du carr�
				// sera utile lors des d�placements de pi�ces					
				this.mapSquareCoord.put(square, new Coord(j, i));
				tab2DJPanel[j][i] = square;

				// ajout de l'image de pi�ce sur le carr�
				if (pieceGuiLabel != null) {
					square.add(pieceGuiLabel);

				}
			}
		}
	}


	/**
	 * @param i
	 * @param j
	 * @return le carr� cr�� sous forme de JPanel
	 */
	private JPanel newSquare(int i, int j){
		JPanel square = new JPanel( new BorderLayout() );
		int row = i % 2;
		if (row == 0) {
			square.setBackground( j % 2 != 0 ? new Color(48, 48, 48) : new Color(242,247, 255)  );
		}
		else {
			square.setBackground( j % 2 != 0 ? new Color(242,247, 255): new Color(48, 48, 48) );
		}
		return square;
	}

	/**
	 * @param i
	 * @param j
	 * @return l'image de pi�ce cr��e sous forme de JLabel
	 */
	private JLabel newImage(int i, int j) {
		JLabel pieceGuiLabel = null;
		/*if (ChessImageProvider.isCoordOK(i, j)) {
			String type = ChessImageProvider.getType(i, j);
			Couleur couleur = ChessImageProvider.getCouleur(i, j);
			pieceGuiLabel = new JLabel(
					new ImageIcon(ChessImageProvider.getImageFile(
							type, couleur)));
		}
		*/
		return pieceGuiLabel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e){

		Point pieceToMoveLocation = null;
		JLabel sauvPieceToMove;

		Component c =  this.chessBoardGuiContainer.findComponentAt(e.getX(), e.getY());

		this.pieceToMove = null;

		// si l'utilisateur a s�lectionn� une pi�ce
		if (c instanceof JLabel) {

			this.pieceToMove = (JLabel)c;
			this.pieceToMoveSquare=(JPanel)c.getParent();

			// avant de d�placer la pi�ce, on en positionne un clone invisible
			// au m�me endroit : cela servira lors du rafraichissement de la fen�tre (update)
			sauvPieceToMove = new JLabel(this.pieceToMove.getIcon());
			sauvPieceToMove.setVisible(false);
			this.pieceToMoveSquare.add(sauvPieceToMove);

			pieceToMoveLocation = this.pieceToMoveSquare.getLocation();
			this.xAdjustment = pieceToMoveLocation.x - e.getX();
			this.yAdjustment = pieceToMoveLocation.y - e.getY();

			this.pieceToMove.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			this.pieceToMove.setSize(pieceToMove.getWidth(), pieceToMove.getHeight());
			this.layeredPane.add(pieceToMove, JLayeredPane.DRAG_LAYER);
		}
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.pieceToMove != null) {
			this.pieceToMove.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

		Coord initCoord = null, finalCoord = null ;
		Component targetedComponent = null;
		JPanel targetSquare;

		if(this.pieceToMove != null) {


			this.pieceToMove.setVisible(false);

			// r�cup�ration du composant qui se trouve � position (pixel) finale
			targetedComponent =  this.chessBoardGuiContainer.findComponentAt(e.getX(), e.getY());

			// si c'est un carr� occup�, on a r�cup�r� une image de pi�ce
			// et il faut r�cup�rer le square qui la contient
			if (targetedComponent instanceof JLabel){
				targetSquare = (JPanel) targetedComponent.getParent();
			}
			// si c'est un carr� vide
			else {
				targetSquare = (JPanel)targetedComponent;
			}			

			// r�cup�ration coordonn�es initiales et finales de la pi�ce � d�placer
			// en vue du d�placement m�tier
			initCoord = this.mapSquareCoord.get(this.pieceToMoveSquare);
			finalCoord = this.mapSquareCoord.get(targetSquare);

			// Si les coordonn�es finales sont en dehors du damier, on les force � -1
			// cela permettra � la m�thode chessGame.move de g�rer le message d'erreur
			if (finalCoord == null){
				finalCoord = new Coord(-1, -1);
			}

			// Invoque la m�thode de d�placement de l'�chiquier		
			this.chessGameControler.move(initCoord, finalCoord);

			// l'�chiquier est observ� par cette fen�tre
			// d�s lors qu'il est modifi�, la vue en est avertie
			// la m�thode update est appel�e pour rafraichir l'affichage
		}

		System.out.println(chessGameControler.getMessage());	// A commenter sauf pour v�rifier si OK


	}



	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * 
	 * Cette m�thode sert � rafraichir l'affichage apr�s un d�placement effectif
	 * dans la classe m�tier Echiquier
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		// Tableau qui contient x, y init x, y final,
		// plus 1 bool�en qui indique si le d�placement a �t� effectif
		Object[] args = (Object[]) arg1;
		boolean isMoveOk = (Boolean) args[4];


		// carr� de destination
		JPanel targetSquare;

		pieceToMoveSquare = this.tab2DJPanel[(Integer) args[0]][(Integer) args[1]];
		pieceToMove = (JLabel) pieceToMoveSquare.getComponent(0);	

		pieceToMove.setVisible(false);

		if (isMoveOk){

			targetSquare = this.tab2DJPanel[(Integer) args[2]][(Integer) args[3]];
			// s'il existe une pi�ce � prendre
			try {
				targetSquare.getComponent(0);
				//suppression de l'image de la piece sur laquelle on deplace la piece courante
				targetSquare.remove(0);
			}
			catch (Exception e) {
			}
			finally {
				// rendre effectif le d�placement de la pi�ce
				targetSquare.add( pieceToMove );	
			}
		}

		pieceToMove.setVisible(true);

		this.revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e){

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}

}
