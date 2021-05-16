package fr.unicaen.l2.info.prog.puzzle.model;

/**
 * Class represents the movements of pieces in the 2D grid
 * 
 * @author Akbe
 * 
 * @see Grille
 * @see Piece
 */
public class Move {

    private Direction direction;

    /**
     * Enumeration defines the list of possible directions of type Direction
     */
    public enum Direction {
        HAUT, BAS, GAUCHE, DROITE
    }

    /**
     * Constructor of piece movement
     * 
     * @param direction Direction
     * 
     * @see Piece
     */
    public Move(Direction direction) {
        this.direction = direction;
    }

    /**
     * Method (getter) returns the direction
     * 
     * @return Direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Method redefines the exit of the movement representation
     * 
     * @return Direction of type String
     */
    @Override
    public String toString() {
        String var = "";
        if (this.direction == Direction.HAUT) {
            var = "HAUT";
        } else if (this.direction == Direction.BAS) {
            var = "BAS";
        } else if (this.direction == Direction.GAUCHE) {
            var = "GAUCHE";
        } else if (this.direction == Direction.DROITE) {
            var = "DROITE";
        }
        return var;
    }

    /**
     * Method determines if two directions are equals
     * 
     * @param m Movement evaluated with the current one
     * 
     * @return Boolean true if equals, else false
     */
    public boolean equals(Move m) {
        return m.toString() == this.toString();
    }

}
