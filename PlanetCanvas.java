
import java.awt.*;
import java.util.Random;


//planetCanvas Class that draws a DrawingCanvas
public class PlanetCanvas implements DrawingCanvas {
    private double xOffset = 1.5;
    private double yOffset = 1.5;
    
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        int diameter = (Math.min(canvasSize.width, 
                        canvasSize.height) / (int)(0.6 * Global.SCALE)); //planet diameter
        
        //calculating x and y coordinate of plane
        int x = (int) ((canvasSize.width - diameter) / xOffset);
        int y = (int) ((canvasSize.height - diameter) / yOffset);

        g.setColor(Color.blue);
        g.fillOval(x, y, diameter, diameter);

        String name = "Earth";
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = x + textWidth/3; //put text center
        int textY = y + diameter/2  + 3 * fm.getHeight(); //put text below drawing
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);

    }

    //updates location of planet on map when called
    public void updateLocation() {
        yOffset -= 0.05;
    }
}