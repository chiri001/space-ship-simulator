

/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: ScreenPostion.java
 * Date modified: 10/12/23
 * 
 * This file checks the pixels of the clicked section of the screen
 */

public class SpaceDebri {

    private double xOffset;
    private double yOffset;

    //constructor
    //takes offset values and sets it for a single debri
    public SpaceDebri(double offset_x, double offset_y) {
        this.xOffset = offset_x;
        this.yOffset = offset_y;
    }

    public void set_offsets(double offset_x, double offset_y) {
        this.xOffset = offset_x;
        this.yOffset = offset_y;
    }

    //getter
    //returns x_offfset
    public double get_offset_x() {
        return xOffset;
    }

    //getter
    //returns y_offset;
    public double get_offset_y() {
        return yOffset;
    }
}