import java.awt.*;

public class Mancala extends Pit {

    /**
     * Constructor
     *
     * @param n      - Marble initializer
     * @param index  - Location of Mancala
     * @param player - player to which the Mancala belongs to
     * @param board  - Decides the shape of pits
     */
    public Mancala(int n, int index, Gamer player, BoardTheme board) {
        super(n, index, player, board);
    }

    /**
     * Gets the index of each pit
     *
     * @return the location of each pit
     */
    public int getIndex() {
        return super.getIndex();
    }

    /**
     * Get the player
     *
     * @return Player
     */
    public Gamer getGamer() {
        return super.getPlayer();
    }

    /**
     * Get the number of marbles in the current pit
     *
     * @return marbles
     */
    public int getMarbles() {
        return super.getMarbles();
    }

    /**
     * Changes marbles in current pit
     *
     * @param i number of marbles to be set to
     */
    public void setMarbles(int i) {
        super.setMarbles(i);
    }

    /**
     * Check if the pit is empty or not
     *
     * @return returns whether pit is empty or not
     */
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * Get the shape of the pit to be drawn
     *
     * @param board - boardStyle
     * @return Shape based on board style
     */
    public Shape draw(BoardTheme board) {
        return board.getMancalaContainer(getGamer());
    }

    /**
     * Get the player the pit belongs to
     *
     * @param g graphics object used to draw shape
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(this.draw(getBoardStyle()));
    }
}