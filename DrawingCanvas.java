/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: DrawingCanvas.java
 * Date modified: 09/29/23
 * 
 * This file contains DrawingCanvas class. creates a drawing as called by a 
 * different class
 */

import java.awt.*;
import javax.swing.*;

//drawign canvas class to help draw different shapes
public class DrawingCanvas extends JPanel {

    public enum Shape {
        RECTANGLE, CIRCLE; /*shapes needed to be drawn so far*/
    }

    private Shape shape; /*represents different shapes in Shape framework */
    private int x_cord, y_cord; //x and y coordinates for drawing shape
    private int horizontalPadding = 15;
    private int verticalPadding = 15;

    //constructor
    //parameters: takes a shape, x coordinate, and y coordinate
    public DrawingCanvas(Shape shape, int x_cord, int y_cord){
        /*set shape and dimension of the object to be drawn */
        this.shape = shape;
        this.x_cord = x_cord;
        this.y_cord = y_cord;
    }

    //function to get a preferred size to use for component holding the 
    //drawing
    //NOTE: it overidess the default getPreferredSize() function
    //No parameters
    public Dimension getPreferredSize() {
        Container parent = this.getParent(); /*get parent container */

        if(parent != null) {
            int width = parent.getWidth() - (2 * horizontalPadding);
            int height = parent.getHeight() - (2 * verticalPadding);
            return new Dimension(width, height); //new preferred width
        }

        //if parent is null, revert to default size
        return super.getPreferredSize();
    }

    // Using paintCompnet function to paint the drawing
    //overides default paintComponent function
    //takes Grpahics as a paremeter
    protected void paintComponent (Graphics g) { 
	    super.paintComponent(g); 
        //get dimensions of the container and add some padding
        int width = getWidth() - (2 * horizontalPadding);
        int height = getHeight() - (2 * verticalPadding);

        switch(shape){ 
            case RECTANGLE:
                g.drawRect(horizontalPadding, verticalPadding, width, height);
                break;
            case CIRCLE:
                int diameter = Math.min(width, height);
                int x = (getWidth() - diameter) /2;
                int y = (getHeight() - diameter) /2;
                g.drawOval(x, y, diameter, diameter);
                break;
        }
        
    }
}
