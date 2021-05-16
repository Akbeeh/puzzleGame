package fr.unicaen.l2.info.prog.puzzle;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

import fr.unicaen.l2.info.prog.puzzle.model.AbstractModeleEcoutable;
import fr.unicaen.l2.info.prog.puzzle.model.EcouteurModele;
import fr.unicaen.l2.info.prog.puzzle.model.Grille;
import fr.unicaen.l2.info.prog.puzzle.model.ModeleEcoutable;
import fr.unicaen.l2.info.prog.puzzle.model.Move;
import fr.unicaen.l2.info.prog.puzzle.model.Piece;

import fr.unicaen.l2.info.prog.puzzle.view_controller.AfficheurGrille;
import fr.unicaen.l2.info.prog.puzzle.view_controller.FrameGrilleGui;
import fr.unicaen.l2.info.prog.puzzle.view_controller.PanelGrilleView;

/**
 * Executable main class : launch of the game
 * 
 * @author Akbe
 *
 * @see AbstractModeleEcoutable
 * @see AfficheurGrille
 * @see EcouteurModele
 * @see FrameGrilleGui
 * @see Grille
 * @see ModeleEcoutable
 * @see Move
 * @see PanelGrilleView
 * @see Piece
 */
public class Main {

    /**
     * Executable staitc method main
     * 
     * @param args Arguments written in the console
     */
    public static void main(String[] args) {
        System.out.println("1 : Play with the GUI");
        System.out.println("2 : Play with the console");
        Scanner scan = new Scanner(System.in);
        int choix = scan.nextInt();
        if (choix == 1) {
            gameInterface();
        } else if (choix == 2) {
            gameTerminal();
        }
        scan.close();
    }

    /**
     * Static method runs when the user chooses to play with the GUI
     * 
     * @see AfficheurGrille
     * @see FrameGrilleGui
     * @see Grille
     */
    public static void gameInterface() {
        Grille gt = new Grille(3, 3);
        AfficheurGrille aff1 = new AfficheurGrille(gt);
        FrameGrilleGui fenetre = new FrameGrilleGui(gt);

        System.out.println("**************************************************");
        System.out.println("After mixing, the game starts");
        System.out.println("**************************************************");
        gt.display();
    }

    /**
     * Static method runs when the user chooses to play with the console
     * 
     * @see Grille
     * @see Scanner
     */
    public static void gameTerminal() {
        Scanner scanT = new Scanner(System.in);
        System.out.println("START OF THE GAME !!!");

        Grille g = new Grille(3, 3);
        g.shuffle();

        while (!g.isFinished()) {
            g.display();
            ArrayList<Move> moves = g.getMove();
            System.out.println(moves);
            System.out.println("Please select the desired direction (0 = first choice...)");
            int dir = scanT.nextInt();
            while (dir < 0 || moves.size() <= dir) {
                System.out.println("Error, please take a direction from the list");
                dir = scanT.nextInt();
            }
            g = g.play(moves.get(dir));
            System.out.println("------------");
        }

        g.display();
        System.out.println("You have successfully completed the game !");

        scanT.close();
    }
}