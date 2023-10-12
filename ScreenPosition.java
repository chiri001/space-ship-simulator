import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

public class ScreenPosition {

    public Color getPixelColor(int x, int y) {
        
        Robot robot;

        try {
            robot = new Robot();
            return robot.getPixelColor(x, y);
        } catch(AWTException e) {
            e.printStackTrace();
        }

        return Color.WHITE; //defualt error color
    }
}
