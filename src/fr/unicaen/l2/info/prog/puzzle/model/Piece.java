package fr.unicaen.l2.info.prog.puzzle.model;

/**
 * Class represents the puzzle game pieces of the puzzle
 * 
 * @author Akbe
 * 
 * @see Grille
 */
public class Piece {

    private int val;

    /**
     * Constructor of piece
     * 
     * @param val Piece value
     */
    public Piece(int val) {
        this.val = val;
    }

    /**
     * Method (getter) returns the piece value
     * 
     * @return Piece value
     */
    public int getValue() {
        return this.val;
    }

    /**
     * Method redefines the exit of the movement representation
     * 
     * @return Value of the piece
     */
    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    /**
     * Method determines if two values are equals
     * 
     * @param p Piece evaluated with the current one
     * 
     * @return Boolean true if equals, else false
     */
    public boolean equals(Piece p) {
        return p.toString() == this.toString();
    }
}
