import javax.swing.*;
import java.awt.*;

public class Pit extends JComponent {

    Gamer player;
    BoardTheme boardStyle;
    int marbles, index;

    /**
     * Pit class constructor - creates a pit with the given parameters
     *  @param n      - the number of marbles to initialize the pit with
     * @param index  - the location of the mancala
     * @param player - the Player this mancala belongs to
     * @param board  - a concrete implementation of BoardStyle determining
     */
    public Pit(int n, int index, Gamer player, BoardTheme board) {
        marbles = n;
        this.index = index;
        this.player = player;
        boardStyle = board;
    }

    /**
     * Gets the number of marbles in pit
     *
     * @return Marbles
     */
    public int getMarbles() {
        return marbles;
    }

    /**
     * Gets the index of each pit
     *
     * @return the location of each pit
     */
    public int getIndex() {
        return index;
    }

    /**
     * Return player
     *
     * @return the pit's player
     */
    public Gamer getPlayer() {
        return player;
    }

    public BoardTheme getBoardStyle() {
        return boardStyle;
    }

    /**
     * Sets the number of marbles in the pit
     *
     * @param i number of marbles to be set to
     */
    public void setMarbles(int i) {
        marbles = i;
    }

    /**
     * Checks whether pit is empty or not
     *
     * @return Return whether pit is empty or not
     */
    public boolean isEmpty() {
        return marbles == 0;
    }

    /**
     * Draws the pit
     *
     * @param board - the board style determining the shape
     * @return the Shape based on the board style
     */
    public Shape draw(BoardTheme board) {
        return board.getPitContainer();
    }

    /**
     * Paints the component
     *
     * @param g graphics object used to draw shape
     */
    public void paintComponent(Graphics g) {
        int x = 0;
        int y = 0;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(this.draw(boardStyle));
        int height = this.draw(boardStyle).getBounds().height;
        int width = this.draw(boardStyle).getBounds().width;
        int row = height / 2 - 5;
        int col = width / 2 - 5;
        for (int i = 0; i < getMarbles(); i++) {
            if (y >= height) {
                g2.drawOval(x, row, 10, 10);
                x = x + 10;
            } else {
                g2.drawOval(col, y, 10, 10);
                y = y + 10;
            }
        }
    }
}