package fr.unicaen.l2.info.prog.puzzle.model;

/**
 * Interface as part of MVC model. Here in the observe.
 * 
 * @author Akbe
 * 
 * @see AbstractModeleEcoutable
 */
public interface ModeleEcoutable {

    /**
     * Method add the ecouteur of the ecouteur's list created at the same time as
     * the 2D grid
     * 
     * @param ec Ecouteur
     * 
     * @see AbstractModeleEcoutable#ajoutEcouteur(EcouteurModele)
     * @see Grille
     */
    public void ajoutEcouteur(EcouteurModele ec);

    /**
     * Method remove the ecouteur of the ecouteur's list created at the same time as
     * the 2D grid
     * 
     * @param ec Ecouteur
     * 
     * @see AbstractModeleEcoutable#retraitEcouteur(EcouteurModele)
     * @see Grille
     */
    public void retraitEcouteur(EcouteurModele ec);

}