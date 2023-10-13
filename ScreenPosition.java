
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: ScreenPostion.java
 * Date modified: 10/12/23
 * 
 * This file checks the pixels of the clicked section of the screen
 */

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

public class ScreenPosition {

    //returns pixel color at coordinate on screen
    public Color getPixelColor(int x, int y) {
        
        Robot robot;

        try {
            robot = new Robot();
            return robot.getPixelColor(x, y); //returns pixel color at x, y
        } catch(AWTException e) {
            e.printStackTrace(); //error print
        }

        return Color.WHITE; //defualt error color to return
    }
}
