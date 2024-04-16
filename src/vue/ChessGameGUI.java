package vue;

import controler.ChessGameControlers;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ChessGameGUI extends JFrame implements Observer {
    @Override
    public void update(Observable o, Object arg) {

    }

    public ChessGameGUI(String s, ChessGameControlers chessGameControler, Dimension dim) {
    }
}
