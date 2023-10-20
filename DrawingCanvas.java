
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

    void draw(Graphics2D g, Dimension canvasSize);
    double get_xOffset();
    double get_yOffset();
    String get_name();
    int get_speed();
    void start();
    void stop();
    void forward(int forward);
    void rewind();
    void reset();
    void move_item(String direction);
    boolean isWithinMap(int y, int diameter);
}