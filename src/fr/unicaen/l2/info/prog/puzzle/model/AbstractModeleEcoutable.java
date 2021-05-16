package fr.unicaen.l2.info.prog.puzzle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class as part of model MVC. Here in the observe pattern. It
 * implements ModeleEcoutable class.
 * 
 * @author Akbe
 * 
 * @see EcouteurModele
 * @see Grille
 * @see ModeleEcoutable
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {

    protected List<EcouteurModele> ecouteurs;

    /**
     * Method add the ecouteur of the ecouteur's list created at the same time as
     * the 2D grid
     * 
     * @param ec Ecouteur of type EcouteurModele
     * 
     * @see Grille
     * @see EcouteurModele
     * @see ModeleEcoutable
     */
    public void ajoutEcouteur(EcouteurModele ec) {
        ecouteurs.add(ec);
    }

    /**
     * Method remove the ecouteur of the ecouteur's list created at the same time as
     * the 2D grid
     * 
     * @param ec Ecouteur of type EcouteurModele
     * 
     * @see Grille
     * @see EcouteurModele
     * @see ModeleEcoutable
     */
    public void retraitEcouteur(EcouteurModele ec) {
        ecouteurs.remove(ec);
    }

    /**
     * Abstract method will be able to redefine caseChangement() method of the grid
     * 
     * @see Grille#caseChangement
     */
    public abstract void caseChangement();

}