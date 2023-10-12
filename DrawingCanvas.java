
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: DrawingCanvas.java
 * Date modified: 10/06/23
 * 
 * This file contains DrawingCanvas interface. The interface facilitates 
 * drawing of objects in the map.
 */

import java.awt.*;

// setting up a drawing canvas interface for the map
public interface DrawingCanvas {
    /* draw 
     * parameters include a 2d graphics and dimensions of the drawing panel
     * drawing interface for the file
     * returns nothing
    */
    void draw(Graphics2D g, Dimension canvasSize);
    void set_offset(double xOffset, double yOffset);
    double get_xOffset();
    double get_yOffset();
    String get_name();
    double get_speed();
}