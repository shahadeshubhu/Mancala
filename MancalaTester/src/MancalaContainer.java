import java.awt.*;

public class MancalaContainer extends PitContainer {

    /**
     * Constructor
     *
     * @param stones      - Marble initializer
     * @param position  - Location of Mancala
     * @param theGamer - player to which the Mancala belongs to
     * @param bStyle  - Decides the shape of pits
     */
    public MancalaContainer(Gamer theGamer, int stones, int position, BoardTheme bStyle) {
        super(theGamer, stones, position, bStyle);
    }

    /**
     * Gets the index of each pit
     *
     * @return the location of each pit
     */
    public int getPitContainerPosition() {
        return super.getPitContainerPosition();
    }

    /**
     * Get the player
     *
     * @return Player
     */
    public Gamer getGamer() {
        return super.getGamer();
    }

    /**
     * Get the number of marbles in the current pit
     *
     * @return marbles
     */
    public int getStones() {
        return super.getStones();
    }

    /**
     * Changes marbles in current pit
     *
     * @param i number of marbles to be set to
     */
    public void setStones(int i) {
        super.setStones(i);
    }

    /**
     * Check if the pit is empty or not
     *
     * @return returns whether pit is empty or not
     */
    public boolean pitContainerEmpty() {
        return super.pitContainerEmpty();
    }

    /**
     * Get the shape of the pit to be drawn
     *
     * @param board - boardStyle
     * @return Shape based on board style
     */
    public Shape createMancalaContainer(BoardTheme board) {
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
        g2.draw(this.createMancalaContainer(getBoardTheme()));
    }
}