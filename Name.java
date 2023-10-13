/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Name.java
 * Date modified: 10/12/23
 * 
 * This file is responsible for drawing names of given drawings
 */

import java.awt.*;

//class that draws names
public class Name {
    
    String name;
    int x;
    int y;
    float fontSize;

    public Name (int x, int y, String name, int fontSize) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.fontSize = fontSize;
    }

    //uses 2d graphics to draw name on map
    public void draw(Graphics2D g) {
        Font originalFont = g.getFont();
        Font newFont = originalFont.deriveFont(fontSize); 
        g.setFont(newFont); //set desired fontsize
        g.drawString(name, x, y); //relies on provided x and y coordinate
    }
}
