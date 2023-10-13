
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: CircleCanvas.java
 * Date modified: 10/05/23
 * 
 * The file handles the drawing for the map
 */

import java.awt.*;

//class that creates a circle drawing using the drawing canvas interface
public class CircleCanvas implements DrawingCanvas {
    
    private int diameter;
    private int x;
    private int y;
    private double xOffset = 2;
    private double yOffset = 2;
    private int padding = 15;

    public void set_offset(double xOffset, double yOffset){
    }
    public double get_xOffset(){
        return this.xOffset;
    }
    public double get_yOffset(){
        return this.yOffset;
    }
    public String get_name(){
        return "";
    }
    public double get_speed(){
        return yOffset;
    }

    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        //add padding from edge of container
        diameter = Math.min(canvasSize.width, canvasSize.height) - padding;

        //centers the circle in the drawing panel
        x = (int)((canvasSize.width - diameter) / xOffset);
        y = (int)((canvasSize.height - diameter) / yOffset);

        g.setColor(Color.black);
        g.drawOval(x, y, diameter, diameter);
    }

    //get the x,y coordinate of the map and tne diameter information
    public int[] get_map_info () {
        int map_info[] = {x, y, diameter}; //store details in array to return

        return map_info;
    }
}
