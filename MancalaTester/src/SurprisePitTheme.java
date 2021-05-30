import java.awt.Image;
import java.awt.Shape;
import java.io.File;

import javax.imageio.ImageIO;

public class SurprisePitTheme implements BoardTheme{
    private Image surprisePit;
    private Image surpriseMancala;
    // NOTE if images dont work out
    // same Shapes as ClassicalTheme
    // but filled with color
    // Simple shape implementation
    /**public Shape getPit() {

     }

     public Shape mancala(Player thePlayer) {

     }*/

/*
  // Image implementation
	public Image getPitContainer() {
		this.surprisePit = ImageIO.read(new File("images/pit_surprise.png"));
    return this.surprisePit;
	}

  // old method name getMancala()
	public Image getMancalaContainer(Player thePlayer) {
		this.surpriseMancala = ImageIO.read(new File("images/mancala_surprise.png"));
    return this.surpriseMancala;
	}*/

    @Override
    public Shape getPitContainer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Shape getMancalaContainer(Gamer theGamer) {
        // TODO Auto-generated method stub
        return null;
    }
}
