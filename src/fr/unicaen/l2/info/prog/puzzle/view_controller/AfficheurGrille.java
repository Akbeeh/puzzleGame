package fr.unicaen.l2.info.prog.puzzle.view_controller;

import fr.unicaen.l2.info.prog.puzzle.model.EcouteurModele;
import fr.unicaen.l2.info.prog.puzzle.model.Grille;

// import fr.unicaen.l2.info.prog.puzzle.model.*;

/**
 * Class displays the grid of the game. It implements EcouteurModele class
 * 
 * @author Akbe
 * 
 * @see EcouteurModele
 * @see Grille
 */
public class AfficheurGrille implements EcouteurModele {

    public Grille modele;

    /**
     * Constructor of the grid display
     * 
     * @param g Grid of the game
     * 
     * @see Grille
     */
    public AfficheurGrille(Grille g) {
        this.modele = g;
        modele.ajoutEcouteur(this);
    }

    /**
     * Method reupdates the grid after a movement piece
     * 
     * @param source Grid of the game
     * 
     * @see EcouteurModele#ModeleMisAJour(Object)
     * @see Grille
     */
    @Override
    public void ModeleMisAJour(Object source) {
        if (modele.isFinished() == false) {
            System.out.println("Mise à jour !");
            System.out.println("Grille : ");
            modele.display();
        } else {
            System.out.println("Fini !!!!");
        }
    }

}