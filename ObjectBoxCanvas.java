

/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Name.java
 * Date modified: 10/12/23
 * 
 * This file is responsible for drawing the box to display clicked drawings. It
 * is also responsible for drawing the drawings in the box.
 */

import java.awt.*;

//class that creates a box drawing to display object information
public class ObjectBoxCanvas implements DrawingCanvas {

    private int xOffset;
    private int yOffset;
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
        return 0;//doesn't move
    }

    //function that draws object box then draws items in it
    public void drawObj(Graphics2D g, Dimension canvasSize, DrawingCanvas obj){
        
        //add padding to put drawings from the edge
        int length = canvasSize.width - padding;
        int width = canvasSize.height - padding;

        draw(g, new Dimension(length, width)); //draw box display screen
        
        //checks that object was clicked to proceed
        if (obj != null) {

            //store initial offset values of the drawing to be displayed
            double initial_xOffset = obj.get_xOffset();
            double initial_yOffset = obj.get_yOffset();

            //sets the drawing on top of the display box
            obj.set_offset(2, 4);

            //draws with new offset values relying on drawing implementation
            //of the type. i.e spaceship relies on Spaceshipcanvas
            obj.draw(g, new Dimension(length, width));

            //set original offsets back
            obj.set_offset(initial_xOffset, initial_yOffset);

            //drawing text to be displayed on object information screen
            g.setColor(Color.black);
            String name_info = "Name : " + obj.get_name();
            String speed_info = "Speed : " + obj.get_speed() + " M/h";
            g.drawString(name_info, 20, 80);
            g.drawString(speed_info, 20, 100);
        }
    }
    
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //add padding to rectangle
        int length = canvasSize.width - padding;
        int width = canvasSize.height - padding;
        int x = 10;
        int y = 10;

        g.drawRect(x, y, length, width);
    }
}
