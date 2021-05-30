import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 Creates a classical mancala pit
 */
public class ClassicalPitTheme implements BoardTheme{

    // add Color?
    public Shape getPitContainer() {
        // temp width/height
        // scale to porportion of frame?
        return new Ellipse2D.Double(0, 0, 120, 120);
    }

    // maybe???
    //public Image getImage(){

    //}

    public Shape getMancalaContainer(Gamer theGamer) {
        // approach: image? or simple oval rectangle
        // x, y ,width, height
        return new RoundRectangle2D.Double(0, 90, 100, 300, 200, 100); // temp width/height
        // temp width/height
    }
}
