package fr.unicaen.l2.info.prog.puzzle.model;

import fr.unicaen.l2.info.prog.puzzle.view_controller.AfficheurGrille;
import fr.unicaen.l2.info.prog.puzzle.view_controller.FrameGrilleGui;
import fr.unicaen.l2.info.prog.puzzle.view_controller.PanelGrilleView;

/**
 * Interface as part of MVC model. Here in the observe.
 * 
 * @author Akbe
 * 
 * @see AbstractModeleEcoutable
 * @see PanelGrilleView
 */
public interface EcouteurModele {

    /**
     * Method will be able to update the ecouteurs in the ecouteur's list created at
     * the same time as the 2D grid. It also will be able to update the "drawing",
     * refresh the screen of the game.
     * 
     * @param source Source of type Object
     * 
     * @see Grille#caseChangement
     * @see PanelGrilleView#ModeleMisAJour(Object)
     */
    public void ModeleMisAJour(Object source);

}