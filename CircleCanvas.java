
import java.awt.*;

//class that creates a circle drawing using the drawing canvas interface
public class CircleCanvas implements DrawingCanvas {
    
    private int diameter;
    private int x;
    private int y;
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        //add padding from edge of container
        diameter = Math.min(canvasSize.width, canvasSize.height) - Global.PADDING;

        //centers the circle in the drawing panel
        int divisor = 2;
        x = (canvasSize.width - diameter) / divisor;
        y = (canvasSize.height - diameter) / divisor;

        g.drawOval(x, y, diameter, diameter);
    }

    public int[] get_map_info () {
        int map_info[] = {x, y, diameter};

        return map_info;
    }
}
