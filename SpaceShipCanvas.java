

import java.awt.*;
import java.util.Random;

//SpaceShipCanvas Class that draws a spaceship
public class SpaceShipCanvas implements DrawingCanvas {

    private double xOffset = 3;
    private double yOffset = 3;
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        int baseSize = Math.min(canvasSize.width, canvasSize.height);

        //length of spaceship landing base
        int spaceshipLength = baseSize / Global.SCALE; 
        
        //height for the main body for the spaceship (taller)
        int spaceshipHeight = spaceshipLength / 2;

        //height of the side compartments for a spaceship(shorter sides)
        int smallTriangleHeight = spaceshipHeight / 3; 

        int centerX = (int) (canvasSize.height / yOffset);
        int centerY = (int) (canvasSize.width / xOffset);
        int x_divisor = 2;

        int[] yPoints = {
            centerX + spaceshipHeight / x_divisor,//base level
            //top of left compartment
            centerX + spaceshipHeight / x_divisor - smallTriangleHeight,

            centerX - spaceshipHeight / x_divisor,//height of ship

            //top of right compartment
            centerX + spaceshipHeight / x_divisor - smallTriangleHeight,
            
            centerX + spaceshipHeight / x_divisor, //base level
            centerX + spaceshipHeight / x_divisor, //bottom of right
            centerX + spaceshipHeight / x_divisor //bottom of left
        };

        int[] xPoints = {
            centerY - spaceshipLength / x_divisor, //most left vertex
            centerY - spaceshipLength / 4, //base of left side
            centerY, //top of main compartment
            centerY + spaceshipLength / 4, //base of right side
            centerY + spaceshipLength / x_divisor, //most right vertex
            centerY + spaceshipLength / 6, //bottom of right side
            centerY - spaceshipLength / 6 //bottom of left side
        };

        g.fillPolygon(xPoints, yPoints, 7);

        String name = "SpaceShip";
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerY - spaceshipLength / x_divisor - textWidth/6; //put text center
        int textY =  centerX - spaceshipHeight / x_divisor + (int) (1.5 * fm.getHeight()); //put text below drawing
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);


    }

    public void updateLocation() {
        yOffset -= 0.08;
    }
}