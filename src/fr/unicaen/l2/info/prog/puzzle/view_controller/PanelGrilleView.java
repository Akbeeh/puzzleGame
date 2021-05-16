package fr.unicaen.l2.info.prog.puzzle.view_controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import fr.unicaen.l2.info.prog.puzzle.model.*;

/**
 * Class is a elementary content therefore including components of our
 * application. It inherits from the JPanel class and implements EcouteurModele
 * class
 * 
 * @author Akbe
 * 
 * @see EcouteurModele
 * @see JPanel
 */
public class PanelGrilleView extends JPanel implements EcouteurModele {

    private static final long serialVersionUID = 1L;

    public Grille grille;
    public int dim = 250;

    private Point Cursor;

    /**
     * JPanel constructor including the grid of the game
     * 
     * @param grille Grid of the game
     * 
     * @see Grille
     */
    public PanelGrilleView(Grille grille) {
        super();
        this.grille = grille;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(dim * grille.getDim(), dim * grille.getDim()));
        grille.ajoutEcouteur(this);

        this.addMouseListener(new MouseAdapter() {
            /**
             * Method captures mouse clicks. The action occurs when the mouse click is in
             * one of the movable cases
             * 
             * @param e MouseEvent type event
             * 
             * @see MouseEvent
             */
            public void mousePressed(MouseEvent e) {
                int gx = e.getY() / dim;
                int gy = e.getX() / dim;

                int[] empty = grille.getEmpty();

                if (!grille.isFinished()) {
                    if ((gy == empty[1]) && (gx == empty[0] - 1)) {
                        grille.play(new Move(Move.Direction.HAUT));
                    } else if ((gy == empty[1]) && (gx == empty[0] + 1)) {
                        grille.play(new Move(Move.Direction.BAS));
                    } else if ((gx == empty[0]) && (gy == empty[1] - 1)) {
                        grille.play(new Move(Move.Direction.GAUCHE));
                    } else if ((gx == empty[0]) && (gy == empty[1] + 1)) {
                        grille.play(new Move(Move.Direction.DROITE));
                    }
                }
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            /**
             * Method captures mouse movements. Mouve position is "saved" when it is in one
             * of the movable cases
             * 
             * @param e MouseEvent type event
             * 
             * @see KeyEvent
             */
            public void mouseMoved(MouseEvent e) {
                int gx = e.getY() / dim;
                int gy = e.getX() / dim;
                Point p = new Point();

                int[] empty = grille.getEmpty();

                if (!grille.isFinished()) {
                    if ((gy == empty[1]) && (gx == empty[0] - 1)) {
                        p.setLocation(gx, gy);
                        Cursor = p;
                    } else if ((gy == empty[1]) && (gx == empty[0] + 1)) {
                        p.setLocation(gx, gy);
                        Cursor = p;
                    } else if ((gx == empty[0]) && (gy == empty[1] - 1)) {
                        p.setLocation(gx, gy);
                        Cursor = p;
                    } else if ((gx == empty[0]) && (gy == empty[1] + 1)) {
                        p.setLocation(gx, gy);
                        Cursor = p;
                    } else {
                        Cursor = null;
                    }
                }
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            /**
             * Method captures the keys pressed on the keyboard. The action occurs when one
             * of the four arrows is pressed AND that action is possible (movable case)
             * 
             * @param e KeyEvent type event
             * 
             * @see KeyEvent
             */
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (!grille.isFinished()) {
                    if (keyCode == KeyEvent.VK_DOWN) {
                        grille.play(new Move(Move.Direction.BAS));
                    } else if (keyCode == KeyEvent.VK_UP) {
                        grille.play(new Move(Move.Direction.HAUT));
                    } else if (keyCode == KeyEvent.VK_LEFT) {
                        grille.play(new Move(Move.Direction.GAUCHE));
                    } else if (keyCode == KeyEvent.VK_RIGHT) {
                        grille.play(new Move(Move.Direction.DROITE));
                    }
                }
                repaint();
            }
        });
        this.setFocusable(true);
    }

    /**
     * Method allows to draw pieces (as numbers) as well as the distinguishing
     * features of the pieces
     * 
     * @param g Object that allow to draw
     * 
     * @see Grille
     * @see Piece
     */
    private void drawNumber(Graphics g) {
        if (!grille.isFinished()) {
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));

            for (int i = 0; i < grille.getDim(); i++) {
                for (int j = 0; j < grille.getDim(); j++) {
                    String number = String.valueOf((Object) grille.getBoard()[j][i]);
                    if (number != "null") {
                        g.drawString(number, i * dim + (dim / 2 - 8 / 3 * fm.stringWidth(number)),
                                j * dim + (dim / 2 + 2 * fm.stringWidth(number)));
                    }
                }
            }

            g.setColor(Color.WHITE);
            for (int i = 1; i < grille.getDim(); i++) {
                g.drawLine(i * dim, 0, i * dim, dim * grille.getDim());
            }

            for (int j = 1; j < grille.getDim(); j++) {
                g.drawLine(0, j * dim, dim * grille.getDim(), j * dim);
            }
        }
    }

    /**
     * Method allow to highlight an element hovered over by the mouse when it can be
     * moved
     * 
     * @param g Object that allow to draw
     * 
     * @see Grille
     */
    public void drawHover(Graphics g) {
        if (!grille.isFinished()) {
            if (Cursor != null) {
                g.setColor(Color.GRAY);
                g.fillRect(Cursor.y * dim, Cursor.x * dim, dim, dim);
            }
        }
    }

    /**
     * Method allow to draw "FINI" when the game is finished
     * 
     * @param g Object that allow to draw
     * 
     * @see Grille
     */
    private void drawFinish(Graphics g) {
        if (grille.isFinished()) {
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 70));
            g.drawString("FINI", dim + (dim / 2 - 4 * fm.stringWidth("FINI")),
                    dim + (dim / 2 + fm.stringWidth("FINI")));
        }
    }

    /**
     * Method reupdates the grid on each pieces movement Cette méthode remet à jour
     * la grille à chaque déplacement de pièces
     * 
     * @param source La grille du jeu
     * @see EcouteurModele#ModeleMisAJour(Object)
     */
    @Override
    public void ModeleMisAJour(Object source) {
        this.repaint();
    }

    /**
     * Method paints, draws elements allowing to obtain, at the ind, the whole game.
     * Once the game finished, in the center of the screen is displayed "FINI"
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHover(g);
        drawNumber(g);
        drawFinish(g);
    }

}
