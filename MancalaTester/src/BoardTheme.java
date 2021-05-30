import java.awt.*;
import java.awt.Shape;

public interface BoardTheme {
    public Shape getPitContainer();
    public Shape getMancalaContainer(Gamer g);
    //public Image getPitContainer();
//    public Image getMancalaContainer(Player p);

}
