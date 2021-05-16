package fr.unicaen.l2.info.prog.puzzle.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents the grid of the game where are placed the pieces. It
 * inherits from AbstractModeleEcoutable class and implements Cloneable
 * 
 * @author Akbe
 * 
 * @see AbstractModeleEcoutable
 * @see Cloneable
 * @see Move
 * @see Piece
 */
public class Grille extends AbstractModeleEcoutable implements Cloneable {

    private int n = 3;
    private int m = 3;
    private Piece[][] board;
    private Piece[][] boardFinal;
    private ArrayList<Piece> pieces;

    /**
     * Constructor of the 2D grid 3*3 containing n*m-1 pieces
     * 
     * @param n Number of lines
     * @param m Number of columns
     * 
     * @see Piece
     */
    public Grille(int n, int m) {
        this.n = n;
        this.m = m;
        this.board = new Piece[n][m];
        this.boardFinal = new Piece[n][m];
        this.pieces = new ArrayList<>();
        int val = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Piece p = new Piece(val);
                board[i][j] = p;
                boardFinal[i][j] = p;
                pieces.add(p);
                val++;
            }
        }
        pieces.set(pieces.size() - 1, null);
        board[n - 1][m - 1] = null;
        boardFinal[n - 1][m - 1] = null;
        this.ecouteurs = new ArrayList<>();
    }

    /**
     * Method (getter) returns the grid dimension
     * 
     * @return Number of lines / columns
     */
    public int getDim() {
        return this.n;
    }

    /**
     * Method (getter) retuens the list of pieces
     * 
     * @return List of pieces
     * 
     * @see Piece
     */
    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }

    /**
     * Method (getter) returns the 2D grid
     * 
     * @return 2D grid
     */
    public Piece[][] getBoard() {
        return this.board;
    }

    /**
     * Method (getter) retuens the position of the null case of the 2D grid
     * 
     * @return Null case position
     */
    public int[] getEmpty() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == null) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    /**
     * Method (getter) retuens the list of movable pieces depending on the location
     * of the null case
     * 
     * @return List of movable pieces
     * 
     * @see Move
     */
    public ArrayList<Move> getMove() {
        ArrayList<Move> directions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == null) {
                    if ((0 <= i + 1) && (i + 1 < n) && (board[i + 1][j] != null)) {
                        directions.add(new Move(Move.Direction.BAS));
                    }
                    if ((0 <= i - 1) && (i - 1 < n) && (board[i - 1][j] != null)) {
                        directions.add(new Move(Move.Direction.HAUT));
                    }
                    if ((0 <= j - 1) && (j - 1 < m) && (board[i][j - 1] != null)) {
                        directions.add(new Move(Move.Direction.GAUCHE));
                    }
                    if ((0 <= j + 1) && (j + 1 < m) && (board[i][j + 1] != null)) {
                        directions.add(new Move(Move.Direction.DROITE));
                    }
                }
            }
        }
        return directions;
    }

    /**
     * Method (getter) returns the grid after playing the action
     * 
     * @param move Action / movement of the piece
     * 
     * @return Grid after the play
     * 
     * @see Move
     * @see Piece
     */
    public Grille play(Move move) {
        try {
            Grille afterPlay = (Grille) this.clone();
            int x = afterPlay.getEmpty()[0];
            int y = afterPlay.getEmpty()[1];
            if (afterPlay.getBoard()[x][y] == null) {
                for (Move mov : afterPlay.getMove()) {
                    if (mov.equals(move)) {
                        if (move.getDirection() == Move.Direction.HAUT) {
                            afterPlay.getBoard()[x][y] = afterPlay.getBoard()[x - 1][y];
                            afterPlay.getBoard()[x - 1][y] = null;
                        } else if (move.getDirection() == Move.Direction.BAS) {
                            afterPlay.getBoard()[x][y] = afterPlay.getBoard()[x + 1][y];
                            afterPlay.getBoard()[x + 1][y] = null;
                        } else if (move.getDirection() == Move.Direction.GAUCHE) {
                            afterPlay.getBoard()[x][y] = afterPlay.getBoard()[x][y - 1];
                            afterPlay.getBoard()[x][y - 1] = null;
                        } else if (move.getDirection() == Move.Direction.DROITE) {
                            afterPlay.getBoard()[x][y] = afterPlay.getBoard()[x][y + 1];
                            afterPlay.getBoard()[x][y + 1] = null;
                        } else {
                            System.out.println("Erreur direction");
                        }
                    }
                }
            }
            this.caseChangement();
            return afterPlay;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Method (getter) returns si the game is finished : if the current grid is
     * equal to the initial one
     * 
     * @return Boolean true if equals, else false
     * 
     * @see Piece
     */
    public boolean isFinished() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!String.valueOf(this.getBoard()[i][j]).equals(String.valueOf(boardFinal[i][j]))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method displays the grid in its current state
     */
    public void display() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    /**
     * Method shuffles pieces in the grid depending on a random number of movements
     * to avoid unresolved configurations
     */
    public void shuffle() {
        int cpt = (int) (Math.random() * 1000) + 1;
        int var;
        for (int x = 0; x < cpt; x++) {
            ArrayList<Move> moves = this.getMove();
            var = (int) (Math.random() * moves.size());
            this.play(moves.get(var));
        }
    }

    /**
     * Method reupdates the ecouteurs when a piece is moved
     * 
     * @see EcouteurModele
     */
    @Override
    public void caseChangement() {
        for (EcouteurModele ecm : this.ecouteurs) {
            ecm.ModeleMisAJour(this);
        }
    }

}