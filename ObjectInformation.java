/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MyMap.java
 * Date modified: 09/29/23
 * 
 * This file contains MyMap class. It is inform of a circle. The map contians
 * other items drawn in it. 
 */


import java.awt.*;
import javax.swing.*;


//class that creates a circle drawing using the drawing canvas interface
class ObjectBoxCanvas implements DrawingCanvas {
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //centers the circle in the drawing panel
        int length = canvasSize.width - Global.PADDING;
        int width = canvasSize.height - Global.PADDING;
        int x = 10;
        int y = 10;

        g.drawRect(x, y, length, width);
    }
}


//My map class that is incharge of handling elements in the map
class ObjectInformation extends JPanel {

    private ObjectBoxCanvas objRect;

    public ObjectInformation() {
        //create drawings
        objRect = new ObjectBoxCanvas();
    }

    /* PointerUpdateListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic_2d = (Graphics2D) g; //2d graphics for drawings

        objRect.draw(graphic_2d, getSize());
    }
}





