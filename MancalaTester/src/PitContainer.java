import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PitContainer extends JComponent{
    private Gamer theGamer;
    private int stones;
    private int position;
    private BoardTheme bStyle;
    private Image stoneImg;
    /**
     * Creates a pit on the mancala board
     * @param theGamer - the pit possessed by player
     * @param stones - the amount of marbles in a pit
     * @param position - the position of the pit
     * @param bStyle - the board style of the mancala board
     */
    public PitContainer(Gamer theGamer, int stones, int position, BoardTheme bStyle) {
        this.theGamer = theGamer;
        this.stones = stones;
        this.position = position;
        this.bStyle = bStyle;
        try {
            this.stoneImg = ImageIO.read(new File("images/stone.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Old method name getMarbles()
    public int getStones() {
        return this.stones;
    }

    // Old method name getIndex()
    public int getPitContainerPosition() {
        return this.position;
    }

    // Old method name getStyle()
    public BoardTheme getBoardTheme() {
        return this.bStyle;
    }

    // Old method name setMarbles()
    public void setStones(int newStoneAmount) {
        this.stones = newStoneAmount;
    }

    // Old method name isEmpty()
    public boolean pitContainerEmpty() {
        return stones == 0? true: false;
    }

    // Old method name drawPit()
    public Shape createPitContainer(BoardTheme bStyle) {
        return bStyle.getPitContainer();
    }

    public Gamer getGamer() {
        return this.theGamer;
    }
    //  public Shape createPitContainer(BoardTheme bStyle) {
    //	return bStyle.getPitContainer();
    //}

    //public Image createPitContainer(BoardTheme bStyle) {
    //return bStyle.getPitContainer();
    //}

    // Work in progress
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // draw the pits based on board style
        g2.draw(this.createPitContainer(bStyle));
        int pitWidth = this.createPitContainer(bStyle).getBounds().width;
        int pitHeight = this.createPitContainer(bStyle).getBounds().height;
        int xCoordinate = 0;
        int yCoordinate = 0;
        // draw the stones
        for(int i = 0; i < getStones(); i++){
            if(yCoordinate < pitHeight){
                g2.drawImage(this.stoneImg, pitWidth/3,yCoordinate, null);
                yCoordinate += 10;
            }else{
                g2.drawImage(this.stoneImg, xCoordinate,pitHeight/3, null);
                xCoordinate += 10;
            }
        }
    }
}