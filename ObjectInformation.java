/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: ObjectInformation.java
 * Date modified: 09/29/23
 * 
 * This file contains ObjectInformation class that is responsible of 
 * displaying object clicked information
 */


import java.awt.*;
import javax.swing.*;


//class that creates a box drawing to display object information
class ObjectBoxCanvas implements DrawingCanvas {
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //add padding to rectangle
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

    /* 
     * Paints the drawings initiated in the class
    */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic_2d = (Graphics2D) g; //2d graphics for drawings

        objRect.draw(graphic_2d, getSize());
    }
}





