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


//ObjectInformation class incharge of handling elements in the display box
class ObjectInformation extends JPanel {

    private ObjectBoxCanvas objRect;
    private DrawingCanvas clickedObj = null;

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

        objRect.drawObj(graphic_2d, getSize(), clickedObj);
    }

    //sets a DrawingCanvas object when called
    public void set_object(DrawingCanvas object) {
        this.clickedObj = object;
        this.repaint();
    }
}





