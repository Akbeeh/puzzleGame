package fr.unicaen.l2.info.prog.puzzle.view_controller;

import java.awt.Container;

import javax.swing.JFrame;

import fr.unicaen.l2.info.prog.puzzle.model.Grille;
// import fr.unicaen.l2.info.prog.puzzle.model.*;

/**
 * Class represents an application composed of element decorations (in
 * particular JPanel). It inherits from JFrame class
 * 
 * @author Akbe
 * 
 * @see Grille
 * @see JFrame
 */
public class FrameGrilleGui extends JFrame {

    private static final long serialVersionUID = 1L;
    public Grille g;
    public PanelGrilleView panel;

    /**
     * JFrame constructor without parameters which includes the JPanel itself
     * including the grid
     * 
     * @see Grille
     */
    public FrameGrilleGui() {
        this(new Grille(3, 3));
    }

    /**
     * JFrame constructor without parameters which includes the JPanel itself
     * including the grid
     * 
     * @param g Grille du jeu
     * @see Grille
     */
    public FrameGrilleGui(Grille g) {
        super();
        this.g = g;
        g.shuffle();
        this.panel = new PanelGrilleView(this.g);

        // Get the content of the object
        Container cont = this.getContentPane();

        // Addition of the panel in the JFrame type object
        cont.add(this.panel);

        // Set the title of the application
        this.setTitle("Jeu de Puzzle à glissières (Taquin)");

        // Total shutdown of the application when it is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Window formatting
        this.pack();

        // Display in the center of the screen
        this.setLocationRelativeTo(null);

        // Window size modification prohibited
        this.setResizable(false);

        // Window display permission [TRUE|FALSE]
        this.setVisible(true);
    }

}
